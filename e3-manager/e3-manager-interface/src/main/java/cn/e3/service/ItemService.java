package cn.e3.service;

import com.e3.utils.PageResult;

import cn.e3.pojo.TbItem;

public interface ItemService {

	public TbItem findItemById(Long id);
	
	//分页查询数据库中的数据   传递的是页码和每页显示的条数
	public PageResult findItemByPage(Integer total,Integer rows);
}
