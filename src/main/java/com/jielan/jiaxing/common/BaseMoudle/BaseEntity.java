package com.jielan.jiaxing.common.BaseMoudle;

import java.io.Serializable;
/**
 * Created by Administrator on 2016/6/29.
 */
public abstract class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /*
     * id
     */
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
