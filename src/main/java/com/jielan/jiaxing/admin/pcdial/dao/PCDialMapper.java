package com.jielan.jiaxing.admin.pcdial.dao;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDial;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PCDialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PCDial record);

    int insertSelective(PCDial record);

    PCDial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PCDial record);

    int updateByPrimaryKey(PCDial record);

    List<PCDial> selectByopenid(String userid);

    List<PCDial> findLatestUser();

    int findCount(Map<String, Object> params);

    int findCountAll(String area);
}