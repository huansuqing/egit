package cn.e3.service;

import java.util.List;

import com.e3.utils.TreeNode;

public interface ItemCatService {

	//树列表展示
	public List<TreeNode> findByParentIdShowList(Long parentId);
	
}
