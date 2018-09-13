package com.taotao.controller;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{cid}")
		@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long cid){
		TaotaoResult result=itemParamService.getItemParamByCid(cid);
		return result;
	}
	@RequestMapping("/list")
		@ResponseBody
	public EasyUIDataGridResult queryPageItemParam(Integer page, Integer rows){
		EasyUIDataGridResult result=itemParamService.pageSelectItemParam(page,rows);
		return result;
	}

}
