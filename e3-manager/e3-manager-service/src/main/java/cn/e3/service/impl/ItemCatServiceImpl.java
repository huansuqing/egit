package cn.e3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3.utils.TreeNode;

import cn.e3.mapper.TbItemCatMapper;
import cn.e3.pojo.TbItemCat;
import cn.e3.pojo.TbItemCatExample;
import cn.e3.pojo.TbItemCatExample.Criteria;
import cn.e3.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	
	public List<TreeNode> findByParentIdShowList(Long parentId) {
		
		//创建list集合用于存储查询后的对象
		ArrayList<TreeNode> nodeList = new ArrayList<>();
		
		//创建查询条件的封装对象
		TbItemCatExample example = new TbItemCatExample();
		
		//利用example创建条件封装的对象
		Criteria criteria = example.createCriteria();
		
		criteria.andParentIdEqualTo(parentId);
		
		//利用example对象进行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		
		//获取对象后进行遍历  封装合适的参数到treeNode中
		for (TbItemCat tbItemCat : list) {
			
			TreeNode node = new TreeNode();
			node.setId(tbItemCat.getId());
			
			node.setText(tbItemCat.getName());
			//状态的话要进行判断 是父节点的话就是close状态
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			
			nodeList.add(node);
		}
		return nodeList;
		//取发布服务
	}

}
