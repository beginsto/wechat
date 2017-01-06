package com.jielan.jiaxing.admin.wechat.service;

import com.jielan.jiaxing.admin.wechat.moudle.WeChatUser;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface WeChatService {

    int insertData(WeChatUser record);

    WeChatUser findByOpenId(String openid);

    int updateRecord(WeChatUser record);

    WeChatUser updateInfo(WeChatUser record,String openid);
}
