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
    public  void test1(){
        User user = session.get(User.class, 1);
        /*默认情况下只有调用getOrderList才会发出查询user_order表的请求*/
        Set<UserOrder> orderList = user.getOrderList();
        for (UserOrder userOrder : orderList) {
            System.out.println("userOrder   "+userOrder);
        }
    }

    @Test
    public  void test2(){//级联cascade

        User user = new User();
        user.setUsername("一对多");
        user.setSex("1");
        user.setBirthday(new Date(System.currentTimeMillis()));

        UserOrder userOrder1 = new UserOrder();
        userOrder1.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder1.setTip("订单1");

        UserOrder userOrder2 = new UserOrder();
        userOrder2.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder2.setTip("订单2");

        user.getOrderList().add(userOrder1);
        user.getOrderList().add(userOrder2);

        userOrder1.setUser(user);
        userOrder2.setUser(user);

        /*-- 在user表映射文件中配置cascade之后，save user时会级联save user_order */
       /* session.save(userOrder1);
        session.save(userOrder2);*/
        session.save(user);
    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}
