package com.jielan.jiaxing.admin.meiliMemory.dao;

import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemoryDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeiliMemoryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeiliMemoryDetail record);

    int insertSelective(MeiliMemoryDetail record);

    MeiliMemoryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeiliMemoryDetail record);

    int updateByPrimaryKey(MeiliMemoryDetail record);

    List<MeiliMemoryDetail> findDetailRecordByPhone(String phone);
}