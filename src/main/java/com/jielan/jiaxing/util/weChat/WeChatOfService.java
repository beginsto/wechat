package com.jielan.jiaxing.util.weChat;

import com.alibaba.fastjson.JSONObject;
import com.jielan.jiaxing.util.GetAndPostSend;

/**
 * Created by Administrator on 2016/7/5.
 */
public class WeChatOfService {

    private static String appID = "wx68c88f3dca13e997";
    private static String appsecret = "0433804230b8bd009d79d72b2e9c8899";

    public static String getWeChatKey()  throws Exception
    {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appsecret;
        JSONObject JSon = JSONObject.parseObject(GetAndPostSend.sendGet(url));
        return JSon.getString("access_token");
    }

    public static void main(String[] args) throws Exception{
        String key = getWeChatKey();
        System.out.print(key);
    }
}
