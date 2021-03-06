package com.yyyu.hibernate.test;

import com.yyyu.hibernate.pojo.Course;
import com.yyyu.hibernate.pojo.Student;
import com.yyyu.hibernate.pojo.User;
import com.yyyu.hibernate.pojo.UserOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 功能：多对多测试
 *
 * @author yu
 * @date 2017/7/12.
 */
public class Many2ManyTest {

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
        Student student = new Student();
        student.setsName("yu");
        student.setsAge(24);

        Course course1 = new Course();
        course1.setcName("大学语文");
        Course course2 = new Course();
        course2.setcName("高等数学");

        student.getCourses().add(course1);
        student.getCourses().add(course2);

        course1.getStudents().add(student);
        course2.getStudents().add(student);

        session.save(student);

    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}
