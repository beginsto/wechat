package com.jielan.jiaxing.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.jielan.jiaxing.util.hzdb.LocalJDBCTemplate;

import javax.ejb.Local;

/**
 * Created by Administrator on 2016/7/6.
 */
public class GetWeChatUserInfo {

    /**
     * @param openid
     * @return 手机号码
     * @throws Exception
     */
    public static String getPhoneByopenid(String openid) throws Exception {
        String result = "";
        List<Object[]> list = LocalJDBCTemplate.queryForList("select phone from wechat_wcuser where openid = ?", new Object[]{openid});
        if (list.size() > 0)
            result = String.valueOf(list.get(0)[0]);
        return result;
    }

    public static int updateBindPhone(Map<String, Object> map) throws Exception{
        return LocalJDBCTemplate.updateData("update wechat_wcuser set phone = ? ,latesttime = sysdate, bouldtime = sysdate where openid = ?",new Object[]{map.get("phone"),map.get("openid")});
    }

    /**
     *
     * @param openid
     * @return 用户头像，昵称等信息
     * @throws Exception
     */
    public static String getUserInfoByopenidOfDY(String openid) throws Exception {
        JSONObject json = WeChatOfDY.getUserInfo(openid);
        return json.toJSONString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getPhoneByopenid(("oS8a0jnwJmXvOF9yWa-IY-byGeWw")));
        System.out.println(getUserInfoByopenidOfDY("oS8a0jnwJmXvOF9yWa-IY-byGeWw"));
    }
}
