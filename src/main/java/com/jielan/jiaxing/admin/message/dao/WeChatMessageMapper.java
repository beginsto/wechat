package com.jielan.jiaxing.admin.message.dao;

import com.jielan.jiaxing.admin.message.moudle.WeChatMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface WeChatMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeChatMessage record);

    int insertSelective(WeChatMessage record);

    WeChatMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeChatMessage record);

    int updateByPrimaryKey(WeChatMessage record);

    List<WeChatMessage> findRecordsByParams(Map<String, Object> params);
}