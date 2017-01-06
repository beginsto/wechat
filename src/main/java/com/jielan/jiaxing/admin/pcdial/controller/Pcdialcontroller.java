package com.jielan.jiaxing.admin.pcdial.controller;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDialAward;
import com.jielan.jiaxing.util.SmsUtil;
import com.jielan.jiaxing.util.WeChatOfService;

import com.jielan.jiaxing.admin.pcdial.moudle.PCDial;
import com.jielan.jiaxing.admin.pcdial.service.PCDialAwardService;
import com.jielan.jiaxing.admin.pcdial.service.PCDialService;
import com.jielan.jiaxing.common.BaseController.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * Created by Administrator on 2016/8/2.
 */
@Controller
@RequestMapping("/pcdial")
public class Pcdialcontroller extends BaseController{
    @Resource
    private PCDialService pcDialService;

    @Resource
    private PCDialAwardService pcDialAwardService;

    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, Model model){
        try{
            String openid = request.getParameter("openid");
            if(openid == null || openid.equals("") || openid.equals("null")){
                return "error/paramerror";
            }else{
                List<PCDial> pcDial = pcDialService.findByopenid(openid);
                PCDial user = new PCDial();
                if(pcDial.size() == 0){
                    user.setUserid(openid);
                    user.setCreattime(new Date());
                    pcDialService.inserData(user);
                }else{
                    user = pcDial.get(0);
                }
                long m = System.currentTimeMillis();
                List<PCDial> list = pcDialService.findLatestUser();
                long n = System.currentTimeMillis();
                System.out.println(n-m);
                model.addAttribute("user",user);
                model.addAttribute("list",list);
                return "pcdial/index";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error/error-500";
        }
    }

    @RequestMapping(value = "detail")
    public Object toDetail(HttpServletRequest req, Model model){
        String[] areas = {"南湖","秀洲","嘉善","平湖","海盐","海宁","桐乡","经开","港区","出入境管理"};
        String[] area = {"area_1","area_2","area_3","area_4","area_5","area_6","area_7","area_8","area_9","area_10"};
        String[] count = {"count_1","count_2","count_3","count_4","count_5","count_6","count_7","count_8","count_9","count_10"};
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        now = new Date(now.getTime() - 3600*1000*24);
        String yesterday = sf.format(now);
        Map<String, Object> map = new HashMap<>();
        map.put("creattime","'"+yesterday+"'");
        for(int i = 0; i < areas.length;i++){
            map.put("area",areas[i]);
            model.addAttribute(area[i],pcDialService.findCount(map));
            model.addAttribute(count[i],pcDialService.findCountAll(areas[i]));
        }
        return "pcdial/detail";
    }

    @RequestMapping("/rule")
    public String toRule(){
        return "pcdial/rule";
    }

    @RequestMapping("/award")
    public String toAward(HttpServletRequest request,Model model){
        String openid = request.getParameter("openid") == null ? "" : request.getParameter("openid");
        List<PCDial> pcDial = pcDialService.findByopenid(openid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PCDial> user = new ArrayList<PCDial>();
        for(int i =0; i <pcDial.size();i++){
            if(pcDial.get(i).getAward() != null && !pcDial.get(i).getAward().equals("谢谢参与")){
                pcDial.get(i).setDtime(formatter.format(pcDial.get(i).getRaffletime()));
                user.add(pcDial.get(i));
            }
        }
        model.addAttribute("user",user);
        return "pcdial/award";
    }

    @RequestMapping("/getNum")
    @ResponseBody
    public Object getNum(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String phone = request.getParameter("tel");
        int rand = (int)(Math.random()*8999)+1000;
        String msg = "尊敬的用户，您当前的验证为：";
        SmsUtil.sendSms(phone,msg + rand);
        System.out.println("send to user :'"+phone+"';"+msg+rand);
        map.put("result","success");
        map.put("message","短信已下发，请查收");
        map.put("data",rand);
        return map;
    }

    @RequestMapping("/tologin")
    @ResponseBody
    //验证用户是否登记过
    public Object toLogin(HttpServletRequest request) throws Exception{
       // String[] str = {"南湖","秀洲","嘉善","平湖","海盐","海宁","桐乡","经开","港区","出入境管理局"};
        Map<String, Object> map = new HashMap<String, Object>();
        String openid = request.getParameter("openid");
        //String tel = request.getParameter("tel");
        //String area = request.getParameter("area");
        List<PCDial> pcDial = pcDialService.findByopenid(openid);
        if(pcDial == null){
            map.put("result","failed");
        }else{
            //pcDial.get(0).setPhonenum(tel);
            //pcDial.get(0).setArea(str[Integer.parseInt(area)]);
            //pcDialService.updateDataById(pcDial.get(0));
            if(pcDial.get(0).getArea() == null){
                map.put("result","no-log");
            }else{
                map.put("result","success");
            }
        }
        return map;
    }

    @RequestMapping("doLog")
    @ResponseBody
    public Object doLog(HttpServletRequest request,Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] str = {"南湖","秀洲","嘉善","平湖","海盐","海宁","桐乡","经开","港区","出入境管理局"};
            String openid = request.getParameter("openid");
            String area = request.getParameter("area");
            String wayType = request.getParameter("wayType");
            List<PCDial> pcDial = pcDialService.findByopenid(openid);
            if(pcDial == null){
                map.put("result","failed");
            }else{
                if (pcDial.get(0).getArea() == null || pcDial.get(0).getArea().equals("")){
                    pcDial.get(0).setArea(str[Integer.parseInt(area)]);
                    pcDial.get(0).setWaytype(wayType);
                    pcDialService.updateDataById(pcDial.get(0));
                    map.put("result","success");
                }else {
                    map.put("result","repeat");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result","ex");
        }
        return map;
    }

    @RequestMapping("/raffle")
    @ResponseBody
    public Object raffle(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String openid = request.getParameter("openid");
            String phone = request.getParameter("phone");
            List<PCDial> pcDial = pcDialService.findByopenid(openid);//查询所有奖品
            int flag = 0;
            int pid = 0;
            String raffletime = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dtime = sdf.format(new Date());
            for(int i = 0; i < pcDial.size();i++){
                if(pcDial.get(i).getAward() != null) {
                    raffletime = sdf.format(pcDial.get(i).getRaffletime());
                    if (raffletime.equals(dtime)) {
                        flag = 1;
                    }
                }else{
                    pid = pcDial.get(i).getId();
                }
            }
            //System.out.println(pcDial.getAward());
            if(pcDial.size() == 1){//当月未抽过奖
                PCDial record = new PCDial();
                if(pcDial.get(0).getAward() == null){
                    if (pcDial.get(0).getArea() != null){
                        Map<String, String> awardMap = getAward();
                        List<PCDialAward> pcDialAward = pcDialAwardService.queryListAll();
                        int id = Integer.parseInt(awardMap.get("rs"));
                        PCDialAward award = pcDialAward.get(id-1);
                        if(award.getNum() > 0){//奖品还有剩余
                            award.setNum(award.getNum() - 1);
                            award.setVersion(award.getVersion() + 1);
                            pcDialAwardService.updateById(award);
                            record.setId(pid);
                            record.setAward(awardMap.get("award"));
                            record.setPhonenum(phone);
                            record.setRaffletime(new Date());
                            pcDialService.updateDataById(record);
                            map.put("result","success");
                            map.put("data","");
                            map.put("message",awardMap.get("rs"));
                        }else{//该奖品已经被抽完
                            award = pcDialAward.get(4);
                            award.setNum(award.getNum() - 1);
                            award.setVersion(award.getVersion() + 1);
                            pcDialAwardService.updateById(award);//更新奖品数量
                            record.setId(pid);
                            record.setAward(award.getAward());
                            record.setRaffletime(new Date());
                            record.setPhonenum(phone);
                            pcDialService.updateDataById(record);//记录用户所得奖品
                            map.put("result","success");
                            map.put("data","");
                            map.put("message",award.getId());

                        }
                    }else{
                        map.put("result","no-log");
                        map.put("message","登记信息之后才能参与抽奖哦~");
                    }
                }else{
                    map.put("result","failed");
                    map.put("data","");
                    map.put("message","亲，您已经抽过奖了哦~");
                }
                /*if((pcDial.size() == 1 && pcDial.get(0).getAward() != null) || pcDial.size()>1 ){//用户参与的第一个月抽奖必中，往后月份必不中
                    record.setUserid(openid);
                    record.setAward("谢谢参与");
                    record.setCreattime(new Date());
                    record.setRaffletime(new Date());
                    pcDialService.inserData(record);
                    map.put("result","success");
                    map.put("data","");
                    map.put("message","9");
                }else{

                    Map<String, String> awardMap = getAward();
                    List<PCDialAward> pcDialAward = pcDialAwardService.queryListAll();
                    int id = Integer.parseInt(awardMap.get("rs"));
                    PCDialAward award = pcDialAward.get(id-1);
                    if(award.getNum() > 0){//奖品还有剩余
                        award.setNum(award.getNum() - 1);
                        award.setVersion(award.getVersion() + 1);
                        pcDialAwardService.updateById(award);
                        record.setId(pid);
                        record.setAward(awardMap.get("award"));
                        record.setRaffletime(new Date());
                        pcDialService.updateDataById(record);
                        map.put("result","success");
                        map.put("data","");
                        map.put("message",awardMap.get("rs"));
                    }else{//该奖品已经被抽完
                        award = pcDialAward.get(4);
                        award.setNum(award.getNum() - 1);
                        award.setVersion(award.getVersion() + 1);
                        pcDialAwardService.updateById(award);//更新奖品数量
                        record.setId(pid);
                        record.setAward(award.getAward());
                        record.setRaffletime(new Date());
                        pcDialService.updateDataById(record);//记录用户所得奖品
                        map.put("result","success");
                        map.put("data","");
                        map.put("message",award.getId());

                    }
                }*/
            }else{//当月已经抽过奖（一人只能一次）
                map.put("result","failed");
                map.put("data","");
                map.put("message","亲，您已经抽过奖了哦~");
            }
        }catch(Exception e){
            logger.info("公安大转盘抽奖异常：" + e);
            map.put("result","ex");
            map.put("data","");
            map.put("message","");
        }
        return map;
    }

    private static Map<String, String> getAward(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("award","50M流量券");
        map.put("rs","5");
        Random rand = new Random();
        int m = rand.nextInt(10000);
        if(m>=1001 && m<=1300){
            map.put("award","5元话费");
            map.put("rs","1");
        }else if(m>=2001 && m<=2200){
            map.put("award","10元话费");
            map.put("rs","2");
        }
        /*else if(m>=3001 && m<=3150){
            map.put("award","20元话费");
            map.put("rs","3");
        }else if(m>=4001 && m <=4050){
            map.put("award","50元话费");
            map.put("rs","4");
        }*/
        else if(m>=5001 && m<=7000){
            map.put("award","100M流量券");
            map.put("rs","6");
        }else if(m>=7001 && m<=8100){
            map.put("award","200M流量券");
            map.put("rs","7");
        }else if(m>=9001 && m<=9200){
            map.put("award","500M流量券");
            map.put("rs","8");
        }
        return map;
    }
}
