package com.yyyu.hibernate.test;

import com.yyyu.hibernate.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * 功能：测试
 *
 * @author yu
 * @date 2017/7/12.
 */

public class HibernateAPITest {

    private Session session;
    private SessionFactory sessionFactory;

    @Before
    public void init(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }


    @Test
    public void testAdd(){
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUsername("hibernate");
        user.setSex("0");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setAddress("广东深圳");
        session.save(user);
        transaction.commit();
    }

    @Test
    public void testDelete(){
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 10);
        session.delete(user);
        transaction.commit();
    }

    @Test
    public void testModify(){
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class , 11);
        /*因为此时的user为持久态它的变化会反映到数据库中*/
        user.setUsername("修改后的名字");
        transaction.commit();
    }

    @Test
    public void testQuery1(){
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 1);
        System.out.println("user==="+user);
        transaction.commit();
    }

    @After
    public void destroy(){
        session.close();
        sessionFactory.close();
    }

}
