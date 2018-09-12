package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNode> geteasyUITreeNode(String parentId) {
		// TODO Auto-generated method stub
		//根据parentid查找子元素
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		tbItemCatExample.or().andParentIdEqualTo(Long.parseLong(parentId));
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		//tree node集合
		List<EasyUITreeNode> treeNodes=new ArrayList<>();
		//增强for循环给easyui tree node 赋值
	
		for (TbItemCat tbItemCat : tbItemCatList) {
			EasyUITreeNode treenode=new EasyUITreeNode() ;
				treenode.setId(tbItemCat.getId());
				treenode.setState(tbItemCat.getIsParent()?"closed":"open");
				treenode.setText(tbItemCat.getName());
				treeNodes.add(treenode);
		}
		
		
		return treeNodes;
	}

}
