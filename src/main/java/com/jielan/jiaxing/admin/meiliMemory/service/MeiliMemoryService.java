package com.jielan.jiaxing.admin.meiliMemory.service;

import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemoryDetail;
import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemorySms;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface MeiliMemoryService {
    //录入验证码
    int insertData(MeiliMemorySms record);
    //验证用户是否能上传，并返回用户上传次数
    Map<String, Object> isPass(MeiliMemoryDetail record);
    //查询用户验证码记录
    List<MeiliMemorySms> findCodeByPhone(String phone);
    //录入上传信息
    int insertData(MeiliMemoryDetail record);
}
