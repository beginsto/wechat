package com.jielan.jiaxing.admin.message.service;

import com.jielan.jiaxing.admin.message.moudle.WeChatMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
public interface WeChatMessageService {

    int insertData(WeChatMessage record);

    List<WeChatMessage> findRecordsByParams(Map<String, Object> params);
}
