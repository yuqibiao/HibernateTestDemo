package com.yyyu.hibernate.test;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 功能：hibernate中使用原生的sql语句
 *
 * 用于复杂的业务逻辑
 *
 * @author yu
 * @date 2017/7/12.
 */
public class SQLTest {

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
    public void test1(){
        String sql = "select t_user.username , user_order.* from t_user,user_order where t_user.u_id = user_order.user_id and t_user.u_id = :user_id";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("user_id" , 1);
        List<Object[]> list = sqlQuery.list();
        for (Object[] objects:list) {
            System.out.println("username="+objects[0]+"   order_id="+objects[1] +" create_time="+objects[3]);
        }
    }

    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
