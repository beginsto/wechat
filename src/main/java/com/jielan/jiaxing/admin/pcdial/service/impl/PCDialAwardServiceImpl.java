package com.jielan.jiaxing.admin.pcdial.service.impl;

import com.jielan.jiaxing.admin.pcdial.dao.PCDialAwardMapper;
import com.jielan.jiaxing.admin.pcdial.moudle.PCDialAward;
import com.jielan.jiaxing.admin.pcdial.service.PCDialAwardService;
import com.jielan.jiaxing.common.BaseService.impl.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
@Service("pcDialAwardService")
public class PCDialAwardServiceImpl extends AbstractService<PCDialAward,Long> implements PCDialAwardService{

    @Autowired
    private PCDialAwardMapper pcDialAwardMapper;

    @Override
    public PCDialAward findById(int id){
        return this.pcDialAwardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(PCDialAward record){
        return this.pcDialAwardMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PCDialAward> queryListAll() {
        return this.pcDialAwardMapper.queryListAll();
    }

}
