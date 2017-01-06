package com.jielan.jiaxing.admin.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jielan.jiaxing.admin.wechat.dao.WeChatUserMapper;
import com.jielan.jiaxing.admin.wechat.moudle.WeChatUser;
import com.jielan.jiaxing.admin.wechat.service.WeChatService;
import com.jielan.jiaxing.util.CharacterEncoding;
import com.jielan.jiaxing.util.GetWeChatUserInfo;
import com.jielan.jiaxing.util.WeChatOfDY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/3.
 */
@Service(value = "weChatService")
public class WeChatServiceImpl implements WeChatService{

    @Autowired
    private WeChatUserMapper weChatUserMapper;

    @Override
    public int insertData(WeChatUser record){
        return weChatUserMapper.insert(record);
    }

    @Override
    public WeChatUser findByOpenId(String openid){
        return weChatUserMapper.findByOpenId(openid);
    }

    @Override
    public int updateRecord(WeChatUser record){
        return weChatUserMapper.updateByPrimaryKey(record);
    }
    @Override
    public WeChatUser updateInfo(WeChatUser record, String openid){
        try {
            if (record == null){
                WeChatUser user = new WeChatUser();
                user.setOpenid(openid);
                user.setPhone(GetWeChatUserInfo.getPhoneByopenid(openid));//查询用户绑定手机号码
                JSONObject json = WeChatOfDY.getUserInfo(openid);//获取用户微信昵称、头像等信息
                user.setNickname(CharacterEncoding.stringToUnicode(String.valueOf(json.get("nickname"))));
                user.setHeadimgurl(String.valueOf(json.get("headimgurl")));
                user.setUnionid(String.valueOf(json.get("unionid")));
                user.setCreateTime(new Date());
                insertData(user);
                record = findByOpenId(openid);
            }else{
                String phone = GetWeChatUserInfo.getPhoneByopenid(openid);
                JSONObject json = WeChatOfDY.getUserInfo(openid);
                int temp = 0;
                if(record.getPhone() == null || !record.getPhone().equals(phone)){
                    record.setPhone(phone);
                    temp = 1;
                }
                if(record.getNickname() == null || !CharacterEncoding.stringToUnicode(String.valueOf(json.get("nickname"))).equals(record.getNickname())){
                    record.setNickname(CharacterEncoding.stringToUnicode(String.valueOf(json.get("nickname"))));
                    temp = 1;
                }
                if(record.getHeadimgurl() == null || !String.valueOf(json.get("headimgurl")).equals(record.getHeadimgurl())){
                    record.setHeadimgurl(String.valueOf(json.get("headimgurl")));
                    temp = 1;
                }
                if(temp == 1){
                    updateRecord(record);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return record;
    }

}
