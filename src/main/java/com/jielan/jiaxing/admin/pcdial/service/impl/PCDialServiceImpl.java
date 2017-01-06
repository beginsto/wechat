package com.jielan.jiaxing.admin.pcdial.service.impl;

import java.util.List;
import java.util.Map;

import com.jielan.jiaxing.admin.pcdial.dao.PCDialMapper;
import com.jielan.jiaxing.admin.pcdial.moudle.PCDial;
import com.jielan.jiaxing.admin.pcdial.service.PCDialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Administrator on 2016/8/2.
 */
@Service("pcDial")
public class PCDialServiceImpl implements PCDialService{

    @Autowired
    private PCDialMapper pcDialMapper;

    @Override
    public int inserData(PCDial record){
        return this.pcDialMapper.insertSelective(record);
    }

    @Override
    public int updateDataById(PCDial record){
        return this.pcDialMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<PCDial> findByopenid(String openid){
        return this.pcDialMapper.selectByopenid(openid);
    }

    @Override
    public List<PCDial> findLatestUser(){
       return this.pcDialMapper.findLatestUser();
    }

    @Override
    public int findCount(Map<String, Object> params){
        return pcDialMapper.findCount(params);
    }

    @Override
    public int findCountAll(String area){
        return pcDialMapper.findCountAll(area);
    }
}
