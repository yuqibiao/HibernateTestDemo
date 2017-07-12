package com.yyyu.hibernate.test;

import com.yyyu.hibernate.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 功能：Criteria使用测试
 *
 * 一般用于单表查询
 *
 * @author yu
 * @date 2017/7/12.
 */
public class CriteriaTest {

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
    public void test1(){//---where
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("u_id", 1));
        User user = (User) criteria.uniqueResult();
        System.out.println(user);
    }

    @Test
    public void test2(){//---in
        Object[] ids = new Object[]{1,4,7};
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.in("u_id",ids ));
        List<User> userList = criteria.list();
        for (User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void test3(){//---分页
        Criteria criteria = session.createCriteria(User.class);
        criteria.setFirstResult(2);
        criteria.setMaxResults(3);
        List<User> userList = criteria.list();
        for (User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void test4(){//---聚合函数的使用(Projections中封装一些mysql函数)
        Criteria criteria = session.createCriteria(User.class);
        //--设置查询聚合函数
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        System.out.println("count=="+count);
    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
