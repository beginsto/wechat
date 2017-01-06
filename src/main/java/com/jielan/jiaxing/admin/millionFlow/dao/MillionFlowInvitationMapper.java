package com.jielan.jiaxing.admin.millionFlow.dao;

import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowInvitation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MillionFlowInvitationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionFlowInvitation record);

    int insertSelective(MillionFlowInvitation record);

    MillionFlowInvitation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionFlowInvitation record);

    int updateByPrimaryKey(MillionFlowInvitation record);

    List<MillionFlowInvitation> findRecordsByParams(Map<String, Object> params);

    MillionFlowInvitation isInsertInvitationData(Map<String, Object> params);

    List<MillionFlowInvitation> findInvitationsByParams(Map<String, Object> params);
}