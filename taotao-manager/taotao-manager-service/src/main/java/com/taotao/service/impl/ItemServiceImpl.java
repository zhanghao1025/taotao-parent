package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

/**
 * item商品service实现
 * 
 * @author zhongyuxinxi-02
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(Long id) {
		// 直接查询
		// TbItem item = itemMapper.selectByPrimaryKey(id);
		// 拼装查询条件
		TbItemExample example = new TbItemExample();
		example.or().andIdEqualTo(id);
		List<TbItem> itemList = itemMapper.selectByExample(example);
		// 判断是否为空
		TbItem item = null;
		if (!itemList.isEmpty()) {
			item = itemList.get(0);
		}

		return item;
	}

	@Override
	public EasyUIDataGridResult getItemlist(Integer page, Integer rows) {
		//设置查询页数，查询的行数
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> itemList = itemMapper.selectByExample(example);
		//新建分页对象。包含分页所需信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		//创建easyui返回对象
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(pageInfo.getTotal(),itemList);
		
		return easyUIDataGridResult;
	}

	@Override
	public TaotaoResult saveItem(TbItem tbItem, String desc) {
		//对属性进行补全
		tbItem.setId(IDUtils.genItemId());
		//给定商品状态，1正常，2下架，3删除
		tbItem.setStatus((byte)1);
		
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		
		int insert = itemMapper.insert(tbItem);
		//create tbitemdesc object
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setCreated(date);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setUpdated(date);
		itemDescMapper.insert(tbItemDesc);
		
		
		
		return TaotaoResult.ok();
	}
	
}
