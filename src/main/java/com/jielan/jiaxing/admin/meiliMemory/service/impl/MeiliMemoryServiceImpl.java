package com.jielan.jiaxing.admin.meiliMemory.service.impl;

import com.jielan.jiaxing.admin.meiliMemory.dao.MeiliMemoryDetailMapper;
import com.jielan.jiaxing.admin.meiliMemory.dao.MeiliMemorySmsMapper;
import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemoryDetail;
import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemorySms;
import com.jielan.jiaxing.admin.meiliMemory.service.MeiliMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Service(value = "meiliMemoryService")
public class MeiliMemoryServiceImpl implements MeiliMemoryService{
    @Autowired
    private MeiliMemoryDetailMapper meiliMemoryDetailMapper;

    @Autowired
    private MeiliMemorySmsMapper meiliMemorySmsMapper;

    @Override
    public int insertData(MeiliMemorySms record){
        return meiliMemorySmsMapper.insertSelective(record);
    }

    @Override
    public Map<String, Object> isPass(MeiliMemoryDetail record){
        Map<String, Object> map = new HashMap<>();
        List<MeiliMemoryDetail> list = meiliMemoryDetailMapper.findDetailRecordByPhone(record.getPhone());
        if (list.size()<2){
            map.put("rs","pass");
            map.put("times",list.size());
        }else{
            map.put("rs","end");
        }
        return map;
    }

    @Override
    public List<MeiliMemorySms> findCodeByPhone(String phone){
        return meiliMemorySmsMapper.findCodeByPhone(phone);
    }

    @Override
    public int insertData(MeiliMemoryDetail record){
        return meiliMemoryDetailMapper.insertSelective(record);
    }
}
