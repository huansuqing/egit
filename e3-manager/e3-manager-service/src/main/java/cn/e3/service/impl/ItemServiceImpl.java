package cn.e3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.container.page.PageHandler;
import com.e3.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	
	public TbItem findItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}


	@Override
	public PageResult findItemByPage(Integer page, Integer rows) {
		
//		设置分页信息
		PageHelper.startPage(page, rows);
		
		//创建itemExample对象  都是根据这个对象进行查询的  这个对象可以封装前台传来的条件  依据该对象进行查询数据
		TbItemExample example = new TbItemExample();
		
		//依据example进行查询
		//该list中封装了很多属性 包括分页的所有的信息  比如  数据的总条数 分页的页数 每页的条数等等  取值的时候注意
		List<TbItem> list = itemMapper.selectByExample(example);
		
		
		//使用pageinfo来获取分页的信息  获取指定的list中数据的分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		//获取到的是总条数
		Integer total = (int) pageInfo.getTotal();
		
		//创建pageResult对象
		PageResult pageResult = new PageResult();
		//将数据封装到pageResult中进行返回
		pageResult.setTotal(total);
		pageResult.setRows(list);
		return pageResult;
	}

}
