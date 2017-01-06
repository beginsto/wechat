package com.jielan.jiaxing.admin.pcdial.dao;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDialAward;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PCDialAwardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PCDialAward record);

    int insertSelective(PCDialAward record);

    PCDialAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PCDialAward record);

    int updateByPrimaryKey(PCDialAward record);

    PCDialAward selectById(int id);

    List<PCDialAward> queryListAll();
}