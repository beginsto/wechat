package com.jielan.jiaxing.util;

import com.alibaba.fastjson.JSONObject;
import com.jielan.jiaxing.util.weChat.WXJXDYUtils;
import com.jielan.jiaxing.util.weChat.WXJXUtils;

/**
 * Created by Administrator on 2016/7/6.
 */
public class WeChatOfDY {
    public static String key = null;

    public static boolean AddUse(String fromUserName) throws Exception {
        String getSubscribe = getUser(fromUserName);
        System.out.println(getSubscribe);
        if (getSubscribe.equals("1")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(getUserInfo("oS8a0jqSINpPyDDbe27RB6D31XJU"));
    }

    public static String getUser(String fromUserName)
            throws Exception
    {
        JSONObject jsonuser1 = WXJXDYUtils.getWeixinUser(fromUserName, key);
        String getSubscribe = String.valueOf(jsonuser1.get("subscribe"));
        if ((String.valueOf(jsonuser1.get("errcode")).equals("42001")) || (String.valueOf(jsonuser1.get("errcode")).equals("40001")) || (String.valueOf(jsonuser1.get("errcode")).equals("45009"))) {
            key = WXJXDYUtils.getWeixinKey();
            jsonuser1 = WXJXDYUtils.getWeixinUser(fromUserName, key);
            getSubscribe = String.valueOf(jsonuser1.get("subscribe"));
        }
        return getSubscribe;
    }

    public static JSONObject getUserInfo(String fromUserName)
            throws Exception
    {
        JSONObject jsonuser1 = WXJXDYUtils.getWeixinUser(fromUserName, key);
        if ((String.valueOf(jsonuser1.get("errcode")).equals("42001")) || (String.valueOf(jsonuser1.get("errcode")).equals("40001")) || (String.valueOf(jsonuser1.get("errcode")).equals("45009"))) {
            key = WXJXDYUtils.getWeixinKey();
            jsonuser1 = WXJXDYUtils.getWeixinUser(fromUserName, key);
        }

        return jsonuser1;
    }
}
