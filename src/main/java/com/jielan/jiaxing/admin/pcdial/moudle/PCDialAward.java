package com.jielan.jiaxing.admin.pcdial.moudle;

public class PCDialAward {
    private Integer id;

    private String award;

    private Integer num;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}