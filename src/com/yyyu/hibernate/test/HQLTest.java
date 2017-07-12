package com.yyyu.hibernate.test;

import com.yyyu.hibernate.pojo.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 功能：HQL 语句测试
 * <p>
 * 因为Hibernate的HQL语言是用于面向对象实现查询功能的,然而在插入操作中是不会牵涉任何查询动作的,
 * 所以HQL不能用于insert语句的插入操作,而select、update、delete语句都可以有可能的查询实现。
 *
 * @author yu
 * @date 2017/7/12.
 */
public class HQLTest {

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
    public void testAdd() {
        /*-----hql中没有insert操作，使用传统的session.save()得方式------*/
    }

    @Test
    public void testDelete() {
        /*?占位符*/
        String hql = "delete from User where u_id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, 15);
        query.executeUpdate();
    }

    @Test
    public void testModify() {
        /*命名占位符*/
        String hql = "update User set username=:username where u_id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("username", "hql修改");
        query.setParameter("id", 14);
        query.executeUpdate();
    }

    @Test
    public void testQuery1() {
        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println("user ==" + user);
        }
    }

    @Test
    public void testQuery2() {//---根据id唯一查询
        String hql = "from User where u_id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", 1);
        User user = (User) query.uniqueResult();
        System.out.println("user ==" + user);
    }

    @Test
    public void testQuery3() {//---分页查询
        String hql = "from User";
        Query query = session.createQuery(hql);
        query.setFirstResult(2);
        query.setMaxResults(3);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println("user ==" + user);
        }
    }

    @Test
    public void testQuery4(){//动态拼接hql
        int[] ids = new int[]{1,2,4,7};
        StringBuffer hqlSb = new StringBuffer();
        hqlSb.append("from User where id in (");
        for (int i = 0; i < ids.length; i++) {
            if(i==ids.length-1){
                hqlSb.append("?");
            }else{
                hqlSb.append("?,");
            }
        }
        hqlSb.append(")");
        Query query = session.createQuery(hqlSb.toString());
        for (int i = 0; i <ids.length ; i++) {
            query.setParameter(i , ids[i]);
        }
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println("user ==" + user);
        }

    }

    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
