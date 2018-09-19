package com.taotao.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamChildMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamChild;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongyuxinxi-02 on 2018-09-12.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Autowired
    private TbItemParamChildMapper tbItemParamChildMapper;
    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        //根据id查询是否有模板
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        if (!tbItemParams.isEmpty()){
            return TaotaoResult.ok(tbItemParams.get(0));
        }
        return TaotaoResult.ok();


    }

    @Override
    public EasyUIDataGridResult pageSelectItemParam(Integer page, Integer rows) {
        //使用pageHelp
        PageHelper.startPage(page,rows);
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        //查询所有数据
        List<TbItemParamChild> tbItemParams = tbItemParamChildMapper.queryParamChildList();

        //create pageinfo object
        PageInfo<TbItemParamChild> pageInfo = new PageInfo<>(tbItemParams);
        //create return object
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(pageInfo.getTotal(),tbItemParams);


        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult saveparamData(Long cid, String paramData) {
        return null;
    }

    @Override
    public TbItemParamChild getItemParamChildByCid(Long itemCatId) {
        return null;
    }
}
