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
 * 功能：Hibernate中事务处理测试
 *
 * 业务开始之前打开事务,业务执行之后提交事务. 执行过程中出现异常.回滚事务.
 *
 * @author yu
 * @date 2017/7/12.
 */
public class TransactionTest {

    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void init(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        /*
        * getCurrentSession 保证同一线程（理解为一次业务逻辑可以是不同的业务方法）中使用的session相同
        * 使用getCurrentSession 必须配置current_session_context_class属性
        * session 会自动关闭，不需要手动去关闭，否则会报错。
        * */
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void test(){

        Transaction transaction = session.beginTransaction();
        add(1,2);
        division(1,0);
        transaction.commit();

    }

    public int add(int i , int j){
        User user = session.get(User.class, 11);
        session.delete(user);
        return i+j;
    }

    public float division(int i , int j ){
        User user = session.get(User.class, 13);
        session.delete(user);
        return i/j;
    }

    @After
    public void destroy(){
      /*  session.close();*/
        sessionFactory.close();
    }

}
