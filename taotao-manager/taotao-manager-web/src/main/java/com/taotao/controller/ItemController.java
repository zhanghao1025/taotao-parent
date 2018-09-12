package com.taotao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 
 * @author zhongyuxinxi-02
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable(value="itemId") Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList (Integer page ,Integer rows) {
		EasyUIDataGridResult result = itemService.getItemlist(page,rows);
		return result;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem tbItem ,String desc) {
		TaotaoResult taotaoResult=itemService.saveItem(tbItem,desc);
		
		return taotaoResult;
	}
	

}
