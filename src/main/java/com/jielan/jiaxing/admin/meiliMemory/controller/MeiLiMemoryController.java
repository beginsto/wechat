package com.jielan.jiaxing.admin.meiliMemory.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemoryDetail;
import com.jielan.jiaxing.admin.meiliMemory.moudle.MeiliMemorySms;
import com.jielan.jiaxing.admin.meiliMemory.service.MeiliMemoryService;
import com.jielan.jiaxing.util.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2016/12/23.
 */
@Controller
@RequestMapping("meili-memory")
public class MeiLiMemoryController {

    @Resource
    private MeiliMemoryService meiliMemoryService;

    @RequestMapping(value = "index")
    public String toIndex(HttpServletRequest req){
        return "meili-memory/index";
    }

    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String phone = request.getParameter("phone");
        Random rand = new Random();
        int m = rand.nextInt(899999) + 100000;
        try {
            String result = SmsUtil.sendSms(phone, "尊敬的用户，您当前的验证码为：" + m + "(梅里记忆，10分钟内有效)！");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getString("retDesc").equals("提交成功")) {
                map.put("result", "success");
                map.put("code", m);
                map.put("message", "验证码已下发！");
                MeiliMemorySms record = new MeiliMemorySms();
                record.setCode(""+m);
                record.setCreateTime(new Date());
                record.setPhone(phone);
                meiliMemoryService.insertData(record);
            } else {
                map.put("result", "failed");
                map.put("message", "短信服务异常，请稍后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "failed");
            map.put("message", "短信服务异常，请稍后再试！");
        }
        /*map.put("result", "success");
        map.put("code", m);
        map.put("message", "验证码已下发！");*/
        System.out.println(m);
        return map;
    }

    @RequestMapping(value = "upLoad")
    @ResponseBody
    public Object doUpLoad(MeiliMemoryDetail record, MultipartFile file, HttpServletRequest req){
        Map<String, Object> map = new HashMap<>();
        String code = req.getParameter("code")==null?"":req.getParameter("code");
        Date now = new Date();
        try {
            int temp = 0;
            if(record.getPhone() == null || record.getPhone().equals("") || record.getPhone().length()!=11){
                map.put("result","phone-error");
                temp = 1;
            }
            if(record.getDescriptor() == null || record.getDescriptor().equals("")){
                map.put("result","describe-empty");
                temp = 1;
            }
            if(file.isEmpty()){
                map.put("result","file-empty");
                temp = 1;
            }
            if(code.equals("")){
                map.put("result","code-empty");
                temp = 1;
            }
            if (temp == 0){
                map = meiliMemoryService.isPass(record);
                if(map.get("rs").equals("pass")){
                    List<MeiliMemorySms> records = meiliMemoryService.findCodeByPhone(record.getPhone());
                    if(records.size()>0){
                        MeiliMemorySms sms = records.get(0);
                        if (sms.getCode().equals(code)){
                            if(sms.getCreateTime().getTime()+1000*60*10 < now.getTime()){
                                System.out.println(sms.getCreateTime().getTime()+1000*60*10);
                                System.out.println(now.getTime());
                                map.put("result","code-overtime");
                            }else{
                                String myFileName = file.getOriginalFilename();
                                String  fileName = record.getPhone() +"_"+ String.valueOf(map.get("times")) +  "." + myFileName.substring(myFileName.lastIndexOf(".")+1);
                                //上传到oss
                                OSSClient ossClient = new OSSClient(
                                        "qyy.jielanwx.com", "wHaJouFNAPqjPh0A",
                                        "y5ExAWtJz2Y57hHLIn0qBaMqufp5Wb");
                                InputStream inputStream = file.getInputStream();
                                // 创建上传Object的Metadata
                                ObjectMetadata meta = new ObjectMetadata();
                                // 必须设置ContentLength
                                meta.setContentLength(inputStream.available());
                                meta.setContentType("image/jpeg");
                                // 上传Object.
                                PutObjectResult result = ossClient.putObject("jielan", "jielanwx/jx/meili-memory/upload/"+fileName, inputStream, meta);
                                record.setCreateTime(now);
                                record.setPicName(fileName);
                                record.setVersion(Integer.parseInt(String.valueOf(map.get("times")))+1);
                                if( meiliMemoryService.insertData(record)>0){
                                    map.put("result","success");
                                }else{
                                    map.put("result","failed");
                                }
                            }
                        }else{
                            map.put("result","code-error");
                        }
                    } else {
                        map.put("result","code-error");
                    }
                }else{
                    map.put("result","over");
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            map.put("result","ex");
        }

        return map;
    }

    @RequestMapping(value = "rule")
    public Object toRule(){
        return "meili-memory/rule";
    }
}
