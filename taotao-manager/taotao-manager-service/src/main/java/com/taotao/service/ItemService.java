package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 *
 * @author zhongyuxinxi-02
 *
 */
public interface ItemService {

	public TbItem getItemById(Long id);

	public EasyUIDataGridResult getItemlist(Integer page, Integer rows);

	public TaotaoResult saveItem(TbItem tbItem, String desc,String paramDate);

	String showItemParam(Long itemId);
}
