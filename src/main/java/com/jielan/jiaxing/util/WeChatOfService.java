package com.jielan.jiaxing.util;

import com.alibaba.fastjson.JSONObject;
/**
 * Created by Administrator on 2016/7/5.
 */
public class WeChatOfService {
    public static String access_token = null;//网页授权的access_token，与基础支持的access_token不同
    public static String refresh_token = null;//当access_token超时后，可以使用refresh_token进行刷新，
    private static String APPID = "wx68c88f3dca13e997";
    private static String SECRET = "0433804230b8bd009d79d72b2e9c8899";

    /**
     *
     * @param code//用户临时标识
     * @return 公众号openid（服务号）
     * @throws Exception
     */
    public static String getopenidByCode(String code) throws Exception{
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx68c88f3dca13e997&secret=0433804230b8bd009d79d72b2e9c8899&code=" +
                code + "&grant_type=authorization_code";
        String json = GetAndPostSend.sendGet(url);
        JSONObject user = JSONObject.parseObject(json);
        String openid = String.valueOf(user.get("openid"));
        System.out.print(user);
        return openid;
    }

    /**
     *
     * @param code
     * @return  用户头像、openid等信息或者错误信息
     * @throws Exception
     */
    public static String getUserInfoByCode(String code) throws  Exception{
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
        String result = GetAndPostSend.sendGet(url);
        JSONObject json = JSONObject.parseObject(result);
        if(String.valueOf(json.get("errcode")).equals("40029")){//code无效，
            return result;
        }else{
            access_token = String.valueOf(json.get("access_token"));
            refresh_token = String.valueOf(json.get("refresh_token"));
            String openid = String.valueOf(json.get("openid"));
            url = "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;//验证access_token是否有效
            result = GetAndPostSend.sendGet(url);
            json = JSONObject.parseObject(result);
            if(String.valueOf(json.get("errcode")).equals("40003")){ //access_token无效重新获取
                url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+refresh_token;
                result = GetAndPostSend.sendGet(url);
                json = JSONObject.parseObject(result);
                if(String.valueOf(json.get("errcode")).equals("40030")){//access_token无效
                    return result;
                }else{
                    access_token = String.valueOf(json.get("access_token"));
                    refresh_token = String.valueOf(json.get("refresh_token"));
                    url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
                    result = GetAndPostSend.sendGet(url);
                    return result;
                }
            }else{
                url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
                result = GetAndPostSend.sendGet(url);
                return result;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String code = "001499ca60e484fd20c31fc58f88247o";
        System.out.println(getopenidByCode(code));
        System.out.println(getUserInfoByCode(code));
    }




}
