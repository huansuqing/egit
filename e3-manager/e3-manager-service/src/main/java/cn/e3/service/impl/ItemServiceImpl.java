package cn.e3.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.e3.utils.E3mallResult;
import com.e3.utils.IDUtils;
import com.e3.utils.JsonUtils;
import com.e3.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.jedis.JedisDao;
import cn.e3.jedis.impl.JedisDaoImpl;
import cn.e3.mapper.TbItemDescMapper;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemDesc;
import cn.e3.pojo.TbItemExample;
import cn.e3.service.ItemService;
import redis.clients.jedis.JedisCluster;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	//注入redisCluster
	@Autowired
	private JedisDao jedisDao;
	
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	
	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;
	
	
	@Autowired
	private JmsTemplate jmsTemplate;
	// 消息队列的注入需要根据id进行注入,否则根据类进行注入的时候会出问题
	@Resource(name = "itemAddTopic")
	private Destination destination;

	public TbItem findItemById(Long id) {
		// 查询之前先查缓存中是否有数据
		try {
			//存的是字符串 对key进行了分类
			
			String json = jedisDao.get(ITEM_INFO+":"+id+":BASE");
			//判断是否存在
			if(StringUtils.isNotBlank(json)){
				//存在  转换成商品对象直接进行返回
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				
				return tbItem;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		// 查询后存入缓存
		try {
			//存入缓存
			jedisDao.set(ITEM_INFO+":"+id+":BASE", JsonUtils.objectToJson(tbItem));
			//设置有效时间(指定key值)
			jedisDao.expire(ITEM_INFO+":"+id+":BASE", ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbItem;

	}

	@Override
	public PageResult findItemByPage(Integer page, Integer rows) {

		// 设置分页信息
		PageHelper.startPage(page, rows);

		// 创建itemExample对象 都是根据这个对象进行查询的 这个对象可以封装前台传来的条件 依据该对象进行查询数据
		TbItemExample example = new TbItemExample();

		// 依据example进行查询
		// 该list中封装了很多属性 包括分页的所有的信息 比如 数据的总条数 分页的页数 每页的条数等等 取值的时候注意
		List<TbItem> list = itemMapper.selectByExample(example);

		// 使用pageinfo来获取分页的信息 获取指定的list中数据的分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);

		// 获取到的是总条数
		Long total = pageInfo.getTotal();

		// 创建pageResult对象
		PageResult pageResult = new PageResult();
		// 将数据封装到pageResult中进行返回
		pageResult.setTotal(total);
		pageResult.setRows(list);
		return pageResult;
	}

	@Override
	public E3mallResult addItem(TbItem item, TbItemDesc itemDesc) {
		// 设置商品的id 状态 以及添加的日日期

		final long itemId = IDUtils.genItemId();

		item.setId(itemId);

		item.setStatus((byte) 1);

		Date date = new Date();

		item.setCreated(date);

		item.setUpdated(date);
		// 保存
		itemMapper.insertSelective(item);

		// 为tbitemdesc设置响应的值

		itemDesc.setItemId(itemId);

		itemDesc.setCreated(date);

		itemDesc.setUpdated(date);

		// 将数据保存在数据库中
		itemDescMapper.insertSelective(itemDesc);
		// 返回结果之前 需要发送消息给activemq
		// 发送一个商品添加的消息
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 创建一个textMessage对象 发送id给消息队列中---注意需要的是字符串 此时的id是需要final修饰的  
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});

		E3mallResult result = E3mallResult.ok();

		return result;
		// 取编写controller
	}

	@Override
	public TbItemDesc findItemDescById(Long itemId) {
		
		// 查询之前先查缓存中是否有数据
				try {
					//存的是字符串 对key进行了分类
					
					String json = jedisDao.get(ITEM_INFO+":"+itemId+":DESC");
					//判断是否存在
					if(StringUtils.isNotBlank(json)){
						//存在  转换成商品对象直接进行返回
						TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
						
						return tbItemDesc;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
		
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		try {
			//存入缓存
			jedisDao.set(ITEM_INFO+":"+itemId+":DESC", JsonUtils.objectToJson(tbItemDesc));
			//设置有效时间(指定key值)
			jedisDao.expire(ITEM_INFO+":"+itemId+":DESC", ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tbItemDesc;
	}

}
