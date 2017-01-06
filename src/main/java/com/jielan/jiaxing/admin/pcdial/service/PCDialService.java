package com.jielan.jiaxing.admin.pcdial.service;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDial;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface PCDialService {

    public List<PCDial> findByopenid(String openid);

    public int inserData(PCDial record);

    public int updateDataById(PCDial record);

    public List<PCDial> findLatestUser();

    int findCount(Map<String, Object> params);

    int findCountAll(String area);
}
