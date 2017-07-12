package com.yyyu.hibernate.test;

import com.yyyu.hibernate.pojo.User;
import com.yyyu.hibernate.pojo.UserOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 功能：多对一 一对多测试
 *
 * @author yu
 * @date 2017/7/12.
 */
public class Many2OneTest {

    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Before
    public void init() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public  void test(){
        User user = session.get(User.class, 1);
        /*默认情况下只有调用getOrderList才会发出查询user_order表的请求*/
        Set<UserOrder> orderList = user.getOrderList();
        for (UserOrder userOrder : orderList) {
            System.out.println("userOrder   "+userOrder);
        }
    }

    @Test
    public  void test1(){
        UserOrder userOrder = new UserOrder();
        userOrder.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder.setTip("12313");
        session.save(userOrder);
    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}
