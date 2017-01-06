package com.jielan.jiaxing.admin.millionFlow.moudle;

import java.util.Date;

public class MillionFlowDetail {
    private Integer id;

    private Integer pid;

    private String idate;

    private Integer invitationNum;

    private Integer successNum;

    private Date lastTime;

    private Date createTime;

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

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate == null ? null : idate.trim();
    }

    public Integer getInvitationNum() {
        return invitationNum;
    }

    public void setInvitationNum(Integer invitationNum) {
        this.invitationNum = invitationNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}