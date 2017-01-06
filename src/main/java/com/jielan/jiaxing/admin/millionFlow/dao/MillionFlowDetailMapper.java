package com.jielan.jiaxing.admin.millionFlow.dao;

import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowDetail;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface MillionFlowDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionFlowDetail record);

    int insertSelective(MillionFlowDetail record);

    MillionFlowDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionFlowDetail record);

    int updateByPrimaryKey(MillionFlowDetail record);

    MillionFlowDetail findRecordByParams(Map<String, Object> params);

    int findNumberOfPartake();
}