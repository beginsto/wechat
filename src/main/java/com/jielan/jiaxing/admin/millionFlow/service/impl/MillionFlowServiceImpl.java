package com.jielan.jiaxing.admin.millionFlow.service.impl;

import com.jielan.jiaxing.admin.millionFlow.dao.MillionFlowBindMapper;
import com.jielan.jiaxing.admin.millionFlow.dao.MillionFlowDetailMapper;
import com.jielan.jiaxing.admin.millionFlow.dao.MillionFlowInvitationMapper;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowBind;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowDetail;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowInvitation;
import com.jielan.jiaxing.admin.millionFlow.service.MillionFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.OPTIONS;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
@Service(value = "millionFlowService")
public class MillionFlowServiceImpl implements MillionFlowService{

    @Autowired
    private MillionFlowBindMapper millionFlowBindMapper;

    @Autowired
    private MillionFlowDetailMapper millionFlowDetailMapper;

    @Autowired
    private MillionFlowInvitationMapper millionFlowInvitationMapper;

    @Override
    public int insertData(MillionFlowInvitation record){
        return millionFlowInvitationMapper.insert(record);
    }

    @Override
    public int insertData(MillionFlowDetail record){
        return millionFlowDetailMapper.insert(record);
    }

    @Override
    public int insertData(MillionFlowBind record){
        return millionFlowBindMapper.insert(record);
    }

    @Override
    public MillionFlowDetail findRecordByParams(Map<String, Object> params){
        return millionFlowDetailMapper.findRecordByParams(params);
    }

    @Override
    public List<MillionFlowInvitation> findRecordsByParams(Map<String, Object> params){
        return  millionFlowInvitationMapper.findRecordsByParams(params);
    }

    @Override
    public int updateData(MillionFlowDetail record){
        return millionFlowDetailMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateData(MillionFlowInvitation record){
        return millionFlowInvitationMapper.updateByPrimaryKey(record);
    }

    @Override
    public int findNumberOfPartake(){
        return millionFlowDetailMapper.findNumberOfPartake();
    }

    @Override
    public MillionFlowInvitation isInsertInvitationData(Map<String, Object> params){
        return millionFlowInvitationMapper.isInsertInvitationData(params);
    }

    @Override
    public List<MillionFlowInvitation> findInvitationsByParams(Map<String, Object> params){
        return  millionFlowInvitationMapper.findInvitationsByParams(params);
    }

}
