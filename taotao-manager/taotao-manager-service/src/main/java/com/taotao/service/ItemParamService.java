package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParamChild;
import org.springframework.stereotype.Service;

/**
 * Created by zhongyuxinxi-02 on 2018-09-12.
 */
public interface ItemParamService {

    TaotaoResult getItemParamByCid(Long cid);

    EasyUIDataGridResult pageSelectItemParam(Integer page, Integer rows);

    TaotaoResult saveparamData(Long cid, String paramData);


    TbItemParamChild getItemParamChildByCid(Long itemCatId);
}

