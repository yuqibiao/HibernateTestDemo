package com.yyyu.hibernate.pojo;

import java.util.Date;

/**
 * 功能对应user_order表
 *
 * @author yu
 * @date 2017/7/12.
 */
public class UserOrder {

    private int o_id;
    private User user;
    private Date createTime;
    private String tip;

    public UserOrder() {
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
