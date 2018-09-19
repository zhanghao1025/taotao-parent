package com.taotao.controller;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParamChild;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 根据商品id判断是否存在商品规格参数
	 * @param cid
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long cid){
		TaotaoResult result=itemParamService.getItemParamByCid(cid);
		return result;
	}

	/**
	 * 查询商品规格列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult queryPageItemParam(Integer page, Integer rows){
		EasyUIDataGridResult result=itemParamService.pageSelectItemParam(page,rows);
		return result;
	}

	/**
	 * 保存产品规格参数
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable Long cid, String paramData){
		TaotaoResult result=itemParamService.saveparamData(cid,paramData);
		return result;
	}
	@RequestMapping("/update/{itemCatId}")
	@ResponseBody
	public ModelAndView toupdate(@PathVariable Long itemCatId, Model model, HttpServletRequest request){
		Map<String, Object> hashMap = new HashMap<>();
		TbItemParamChild tbItemParamChild =itemParamService.getItemParamChildByCid(itemCatId);
		hashMap.put("tipd",tbItemParamChild);
		return new ModelAndView("item-param-update",hashMap);
	}

}

