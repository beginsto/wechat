package com.jielan.jiaxing.admin.millionFlow.service;

import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowBind;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowDetail;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowInvitation;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface MillionFlowService {

    int insertData(MillionFlowInvitation record);

    int insertData(MillionFlowDetail record);

    int insertData(MillionFlowBind record);

    MillionFlowDetail findRecordByParams(Map<String, Object> params);

    List<MillionFlowInvitation> findRecordsByParams(Map<String, Object> params);

    int updateData(MillionFlowDetail record);

    int updateData(MillionFlowInvitation record);

    int findNumberOfPartake();

    MillionFlowInvitation isInsertInvitationData(Map<String, Object> params);

    List<MillionFlowInvitation> findInvitationsByParams(Map<String, Object> params);
}
