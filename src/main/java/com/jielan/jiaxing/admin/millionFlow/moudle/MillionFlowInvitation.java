package com.jielan.jiaxing.admin.millionFlow.moudle;

import java.util.Date;

public class MillionFlowInvitation {
    private Integer id;

    private Integer pid;

    private Integer isbind;

    private String openid;

    private String idate;

    private String unionid;

    private String sname;

    private String headimgurl;

    private Date createTime;

    private Date bindTime;

    private String sBindTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIsbind() {
        return isbind;
    }

    public void setIsbind(Integer isbind) {
        this.isbind = isbind;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate == null ? null : idate.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getsBindTime(){
        return sBindTime;
    }

    public void setsBindTime(String sBindTime){
        this.sBindTime = sBindTime;
    }
}