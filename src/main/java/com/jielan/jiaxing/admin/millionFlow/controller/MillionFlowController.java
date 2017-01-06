package com.jielan.jiaxing.admin.millionFlow.controller;

import com.alibaba.fastjson.JSONObject;
import com.jielan.jiaxing.admin.message.moudle.WeChatMessage;
import com.jielan.jiaxing.admin.message.service.WeChatMessageService;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowDetail;
import com.jielan.jiaxing.admin.millionFlow.moudle.MillionFlowInvitation;
import com.jielan.jiaxing.admin.millionFlow.service.MillionFlowService;
import com.jielan.jiaxing.admin.wechat.moudle.WeChatUser;
import com.jielan.jiaxing.admin.wechat.service.WeChatService;
import com.jielan.jiaxing.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/12/29.
 */
@Controller
@RequestMapping(value = "million-flow")
public class MillionFlowController {

    @Resource
    private WeChatService weChatService;

    @Resource
    private MillionFlowService millionFlowService;

    @Resource
    private WeChatMessageService weChatMessageService;

    @RequestMapping(value = "index")
    public Object toIndex(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
        String login_sign = (String)req.getSession().getAttribute("login_sign");
        try{
            //用户基本信息
            WeChatUser user = weChatService.findByOpenId(openid);
            if (login_sign == null || !login_sign.equals("million-flow")){
                user = weChatService.updateInfo(user, openid);
            }
            model.addAttribute("user",user);
            model.addAttribute("openid",openid);
            //用户游戏信息
            Date now = new Date();
            Map<String, Object> map = new HashMap<>();
            map.put("pid",user.getId());
            map.put("idate", function.DateToStringShort(now).substring(0,7));
            MillionFlowDetail record = millionFlowService.findRecordByParams(map);
            if (record == null){
                MillionFlowDetail millionFlowDetail = new MillionFlowDetail();
                millionFlowDetail.setPid(user.getId());
                millionFlowDetail.setIdate(function.DateToStringShort(now).substring(0,7));
                millionFlowDetail.setInvitationNum(0);
                millionFlowDetail.setSuccessNum(0);
                millionFlowDetail.setCreateTime(now);
                millionFlowService.insertData(millionFlowDetail);
                record = millionFlowDetail;
            }
            model.addAttribute("info",record);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "million-flow/index";
    }

    @RequestMapping(value = "share")
    public Object toShare(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
        try {
            WeChatUser user = weChatService.findByOpenId(openid);
            user.setNickname(CharacterEncoding.unicodeToString(user.getNickname()));
            model.addAttribute("user",user);
            int count = millionFlowService.findNumberOfPartake();
            model.addAttribute("count",count);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "million-flow/share";
    }

    @RequestMapping(value = "recommend")
    public Object toRecommend(HttpServletRequest req, Model model){
        String pid = req.getParameter("pid")==null?"":req.getParameter("pid");
        try {
            Map<String, Object> map = new HashMap<>();
            Date now = new Date();
            map.put("idate",function.DateToStringShort(now).substring(0,7));
            map.put("pid",pid);
            map.put("isbind",1);
            List<MillionFlowInvitation> list = millionFlowService.findRecordsByParams(map);
            for(MillionFlowInvitation reocrd : list){
                reocrd.setSname(CharacterEncoding.unicodeToString(reocrd.getSname()));
                reocrd.setsBindTime(function.DateToString(reocrd.getBindTime()));
            }
            model.addAttribute("list",list);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "million-flow/recommend";
    }

    @RequestMapping(value = "open")
    public Object toOpen(HttpServletRequest req, Model model){
        String pid = req.getParameter("pid")==null?"":req.getParameter("pid");
        String code = req.getParameter("code")==null?"":req.getParameter("code");
        try {
            /**********************获取用户头像等信息**********************/
            String openid=(String)req.getSession().getAttribute("openid");
            String unionid = (String)req.getSession().getAttribute("unionid");
            String headimgurl = (String)req.getSession().getAttribute("headimgurl");
            String sname = (String)req.getSession().getAttribute("sname");
            if(openid == null || openid.equals("") || openid.equals("null")){
                String result = WeChatOfService.getUserInfoByCode(code);
                JSONObject json = JSONObject.parseObject(result);
                openid = String.valueOf(json.get("openid"));
                headimgurl = String.valueOf(json.get("headimgurl"));
                unionid = String.valueOf(json.get("unionid"));
                sname = String.valueOf(json.get("nickname"));
                req.getSession().setAttribute("openid",openid);
                req.getSession().setAttribute("unionid",unionid);
                req.getSession().setAttribute("headimgurl",headimgurl);
                req.getSession().setAttribute("sname",sname);
            }
            //
            Map<String, Object> map = new HashMap<>();
            Date now = new Date();
            String sdate = function.DateToStringShort(now).substring(0,7);
            map.put("pid",pid);
            map.put("idate",sdate);
            map.put("openid",openid);
            MillionFlowInvitation temp = millionFlowService.isInsertInvitationData(map);
            if(temp == null && !openid.equals("null")){
                MillionFlowInvitation record = new MillionFlowInvitation();
                record.setPid(Integer.parseInt(pid));
                record.setIsbind(0);
                record.setIdate(sdate);
                record.setCreateTime(now);
                record.setUnionid(unionid);
                record.setHeadimgurl(headimgurl);
                record.setOpenid(openid);
                record.setSname(CharacterEncoding.stringToUnicode(sname));
                millionFlowService.insertData(record);
                //更新邀请人信息
                MillionFlowDetail millionFlowDetail = millionFlowService.findRecordByParams(map);
                millionFlowDetail.setInvitationNum(millionFlowDetail.getInvitationNum()+1);
                millionFlowDetail.setLastTime(new Date());
                millionFlowService.updateData(millionFlowDetail);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "million-flow/open";
    }

    @RequestMapping(value = "sms")
    @ResponseBody
    public Object sendSms(HttpServletRequest req){
        Map<String, Object> map = new HashMap<>();
        String phone = req.getParameter("phone")==null?"":req.getParameter("phone");
        try{
            Random rand = new Random();
            int m = rand.nextInt(899999) + 100000;
            String result = SmsUtil.sendSms(phone, "尊敬的用户，您当前的验证码为：" + m + "【百万流量送不停】！（5分钟内有效）");
            JSONObject json = JSONObject.parseObject(result);
            WeChatMessage message = new WeChatMessage();
            message.setPhone(phone);
            message.setCode(""+m);
            message.setWebsite("million-flow");
            message.setCreateTime(new Date());
            if(weChatMessageService.insertData(message)>0){
                if (json.getString("retDesc").equals("提交成功"))
                    map.put("result", "success");
                else
                    map.put("result", "failed");
                map.put("result", "success");
            }else{
                map.put("result", "failed");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            map.put("result","ex");
        }
        return map;
    }

    @RequestMapping(value = "bind")
    @ResponseBody
    public Object doBind(HttpServletRequest req){
        Map<String, Object> map = new HashMap<>();
        String phone = req.getParameter("phone")==null?"":req.getParameter("phone");
        String code = req.getParameter("code")==null?"":req.getParameter("code");
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
        map.put("phone",phone);
        map.put("code",code);
        map.put("website","million-flow");
        map.put("openid",openid);
        try {
            List<WeChatMessage> list = weChatMessageService.findRecordsByParams(map);
            if(list.size() > 0){
                WeChatMessage record = list.get(0);
                long starttime = record.getCreateTime().getTime();
                long endtime = new Date().getTime();
                if((starttime + 60*1000*5) < endtime){
                    map.put("result","time-over");
                }else{
                    WeChatUser user = weChatService.findByOpenId(openid);
                    user.setPhone(phone);
                    weChatService.updateRecord(user);//更新本地库用户信息
                    GetWeChatUserInfo.updateBindPhone(map);//更新公众号库用户信息
                    //更新邀请用户信息
                    Date now = new Date();
                    map.put("unionid",user.getUnionid());
                    map.put("isbind",0);
                    map.put("idate",function.DateToStringShort(now).substring(0,7));
                    //查询用户被邀请信息
                    List<MillionFlowInvitation> invitations = millionFlowService.findInvitationsByParams(map);
                    if(invitations.size() > 0){
                        MillionFlowInvitation inv = invitations.get(0);//本月最早邀请记录
                        inv.setIsbind(1);
                        inv.setBindTime(now);
                        millionFlowService.updateData(inv);//更新邀请成功
                        map.put("pid",inv.getPid());//获取邀请人pid
                        MillionFlowDetail detail = millionFlowService.findRecordByParams(map);//更新邀请人信息
                        detail.setSuccessNum(detail.getSuccessNum() + 1);
                        detail.setLastTime(now);
                        millionFlowService.updateData(detail);
                    }
                    map.put("result","success");
                }
            }else{
                map.put("result","code-error");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            map.put("result","ex");
        }
        return map;
    }
}
