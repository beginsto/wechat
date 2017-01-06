package com.jielan.jiaxing.util;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.Constants;
import javax.xml.rpc.encoding.XMLType;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ParameterMode;
import javax.xml.namespace.QName;
import java.net.URL;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
/**
 * Created by Administrator on 2016/11/3.
 */
public class function {
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 日期转换
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String DateToString(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 日期转换
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String DateToStringShort(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 获取星期几
     * @param date
     * @return ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ]
     */

    public static String getWeekDay(Date date){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取星期几
     * @param date
     * @return int：[0,6]
     */
    public static int getIWeekDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return week;
    }

    /**
     * 获取接口数据
      * @param num
     * @return String
     * @throws ServiceException
     */
    public static String getUserInfo(String num) throws ServiceException{
        String endpoint = "http://211.140.103.190:8004/Service.asmx?wsdl";
        Service service = new Service();
        Call call = (Call)service.createCall();
        String result = "";

        try{
            call.setTargetEndpointAddress(new URL(endpoint));
            call.setOperationName(new QName("http://tempuri.org/", "TimeMachineQry"));
            call.addParameter(new QName("http://tempuri.org/", "billId"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setUseSOAPAction(true);
            call.setReturnType(Constants.XSD_STRING);
            call.setSOAPActionURI("http://tempuri.org/TimeMachineQry");
            result = (String)call.invoke(new Object[] { num });
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    /**
     *
     * @param num
     * @return
     * @throws ServiceException
     */
    public static String  ElectronicsChannelQry (String num) throws ServiceException{
        String endpoint = "http://211.140.103.190:8004/Service.asmx?wsdl";
        Service service = new Service();
        Call call = (Call)service.createCall();
        String result = "";

        try{
            call.setTargetEndpointAddress(new URL(endpoint));
            call.setOperationName(new QName("http://tempuri.org/", "ElectronicsChannelQry"));
            call.addParameter(new QName("http://tempuri.org/", "billId"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setUseSOAPAction(true);
            call.setReturnType(Constants.XSD_STRING);
            call.setSOAPActionURI("http://tempuri.org/ElectronicsChannelQry");
            result = (String)call.invoke(new Object[] { num });
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) throws ServiceException {
        System.out.println(getUserInfo("15857316799"));
    }

}
