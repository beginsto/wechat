package com.jielan.jiaxing.admin.wechat.dao;

import com.jielan.jiaxing.admin.wechat.moudle.WeChatUser;
import org.springframework.stereotype.Component;

@Component
public interface WeChatUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeChatUser record);

    int insertSelective(WeChatUser record);

    WeChatUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeChatUser record);

    int updateByPrimaryKey(WeChatUser record);

    WeChatUser findByOpenId(String openid);
}