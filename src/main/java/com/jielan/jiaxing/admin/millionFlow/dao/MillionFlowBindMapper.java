package com.jielan.jiaxing.admin.millionFlow.dao;

import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowBind;
import org.springframework.stereotype.Component;

@Component
public interface MillionFlowBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionFlowBind record);

    int insertSelective(MillionFlowBind record);

    MillionFlowBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionFlowBind record);

    int updateByPrimaryKey(MillionFlowBind record);
}