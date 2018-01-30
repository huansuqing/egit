package cn.e3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.e3.pojo.TbItem;
import cn.e3.service.ItemService;

@RestController//一挑2
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	/*@RequestMapping("item/{itemId}")
	public TbItem findById(@PathVariable Long itemId){
		System.out.println("输出");
		TbItem item = itemService.findItemById(itemId);
		return item;
	}*/
	/**
	 * 需求:根据商品id查询商品数据
	 * 参数:Long itemId
	 * 返回值:json格式TbItem
	 */
	@RequestMapping("item/{itemId}")
	public TbItem findItemById(@PathVariable Long itemId){
		System.out.println("service");
		//调用service服务方法
		TbItem item = itemService.findItemById(itemId);
		return item;
	}
	
	
}
