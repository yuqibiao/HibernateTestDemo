package com.yyyu.hibernate.pojo;

import java.util.Date;

/**
 * 功能对应user_order表
 *
 * @author yu
 * @date 2017/7/12.
 */
public class UserOrder {

    private int id;
    private User user;
    private Date createTime;
    private String tip;

    public UserOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
