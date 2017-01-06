package com.jielan.jiaxing.util.weChat;

/**
 * Created by Administrator on 2016/7/6.
 */
import com.alibaba.fastjson.JSONObject;
import com.jielan.jiaxing.util.GetAndPostSend;

public class WXJXDYUtils
{
    private static String appID = "wx999266da568d0ddf";
    private static String appsecret = "4854eb985047628552caca0a33ad1ebd";

    public static String getWeixinKey()
            throws Exception
    {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appsecret;
        JSONObject JSon = getGetJson(url);
        return JSon.getString("access_token");
    }

    public static JSONObject getWeixinUser(String fromUserName, String key)
    {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + key + "&openid=" + fromUserName + "&lang=zh_CN";
        JSONObject JSon = getGetJson(url);
        return JSon;
    }

    public static void main(String[] args) throws Exception
    {
        String aa = getWeixinKey();
        System.out.print(aa);
    }

    public static JSONObject getGetJson(String url)
    {
        return JSONObject.parseObject(GetAndPostSend.sendGet(url));
    }
}
