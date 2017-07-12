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
 * 功能：Hibernate中对象的三种状态
 *
 * 1.瞬时状态（transient）：对象没有id且不在session缓存中（new出来的对象、session.delete(o)）。
 *
 * 2.持久状态（persistent）：对象有id且对象在session缓存中(session.get、session.save()、session.saveOrUpdate)，
 * 该状态下对象的改变会同步到数据库中。
 *
 *
 * 3.游离状态（detached）：对象有id但是对象不在session缓存中（session.close）
 *
 * @author yu
 * @date 2017/7/12.
 */
public class ObjectStatusTest {

    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @Before
    public void init(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void test(){
        /*此时的user对象每天有id没有在session缓存中为瞬时状态*/
        User user = new User();
        user.setUsername("对象状态测试");
        user.setSex("1");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setAddress("广东深圳");
        /*调用save方法后user在session缓存中，有id为持久状态*/
        session.save(user);
        /*对持久状态的改变会反映在数据库中(发出了update user 的sql)*/
        user.setSex("0");
        /*调用commit方法后user不在session缓存中，但是有id 为游离状态*/
    }

    @After
    public void destroy(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
