package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
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

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
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
	public String showItemParam(Long itemId) {
		// 根据商品id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem>list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.isEmpty()) {
			return"";
		}
		//取规格参数
		TbItemParamItem itemParamItem = list.get(0);
		//取json数据
		String paramData = itemParamItem.getParamData();
		//转换成java对象
		List<Map>mapList = JsonUtils.jsonToList(paramData, Map.class);
		//遍历list生成html
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			List<Map>mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("			<td>"+map2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");

		return sb.toString();

	}

	@Override
	public TaotaoResult saveItem(TbItem tbItem, String desc, String paramDate) {
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
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setItemId(tbItem.getId());
		tbItemParamItem.setParamData(paramDate);
		tbItemParamItem.setUpdated(date);
		tbItemParamItemMapper.insert(tbItemParamItem);


		return TaotaoResult.ok();
	}
	
}
