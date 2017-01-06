package com.jielan.jiaxing.admin.pcdial.service;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDialAward;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface PCDialAwardService {
    public PCDialAward findById(int id);

    public int updateById(PCDialAward record);

    public List<PCDialAward> queryListAll();
}
