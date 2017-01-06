package com.jielan.jiaxing.admin.message.service.impl;

import com.jielan.jiaxing.admin.message.dao.WeChatMessageMapper;
import com.jielan.jiaxing.admin.message.moudle.WeChatMessage;
import com.jielan.jiaxing.admin.message.service.WeChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
@Service(value = "weChatMessageService")
public class WeChatMessageServiceImpl implements WeChatMessageService{

    @Autowired
    private WeChatMessageMapper weChatMessageMapper;

    @Override
    public int insertData(WeChatMessage record){
        return weChatMessageMapper.insert(record);
    }

    @Override
    public List<WeChatMessage> findRecordsByParams(Map<String, Object> params){
        return weChatMessageMapper.findRecordsByParams(params);
    }
}
