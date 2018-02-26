package cn.e3.service;

import com.e3.utils.E3mallResult;
import com.e3.utils.PageResult;

import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemDesc;

public interface ItemService {

	public TbItem findItemById(Long id);
	
	//分页查询数据库中的数据   传递的是页码和每页显示的条数
	public PageResult findItemByPage(Integer total,Integer rows);
	
	//商品添加的方法
	/**
	 * 上传的参数有俩个标的数据  所以要对俩张表进行保存
	 */
	public E3mallResult addItem(TbItem item,TbItemDesc tiemDest);
	
	public TbItemDesc findItemDescById(Long itemId);
}
