package com.jielan.jiaxing.admin.meiliMemory.dao;

import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemorySms;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeiliMemorySmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeiliMemorySms record);

    int insertSelective(MeiliMemorySms record);

    MeiliMemorySms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeiliMemorySms record);

    int updateByPrimaryKey(MeiliMemorySms record);

    List<MeiliMemorySms> findCodeByPhone(String phone);
}