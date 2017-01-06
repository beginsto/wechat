package com.jielan.jiaxing.admin.pcdial.moudle;

import java.util.Date;

public class PCDial {
    private Integer id;

    private String userid;

    private String phonenum;

    private String award;

    private String area;

    private String waytype;

    private Date creattime;

    private Date raffletime;

    private String dtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public String getArea(){
        return area;
    }

    public void setArea(String area){
        this.area = area;
    }

    public String getWaytype(){
        return waytype;
    }

    public void setWaytype(String waytype){
        this.waytype = waytype;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Date getRaffletime() {
        return raffletime;
    }

    public void setRaffletime(Date raffletime) {
        this.raffletime = raffletime;
    }

    public String getDtime(){
        return dtime;
    }

    public void setDtime(String dtime){
        this.dtime = dtime;
    }
}