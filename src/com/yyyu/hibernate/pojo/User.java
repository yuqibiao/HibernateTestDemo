package com.yyyu.hibernate.pojo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 功能：对应user表
 *
 * @author yu
 * @date 2017/7/12.
 */
public class User {

    private int u_id;
    private String username;// 用户姓名
    private String sex;// 性别
    private Date birthday;// 生日
    private String address;// 地址

    private Set<UserOrder> orderList;

    public User() {
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<UserOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<UserOrder> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "u_id="+ u_id +"  username="+username+"  sex="+sex+"  birthday="+birthday+"  address="+address;
    }
}
