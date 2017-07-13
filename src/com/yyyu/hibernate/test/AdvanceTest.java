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
import java.util.Set;

/**
 * 功能：Hibernate进阶知识
 *
 * 1.cascade：级联，就是对一个对象进行操作的时候，会把他相关联的对象也一并进行相应的操作。
 *
 * all:                             所有情况下均进行关联操作，即save-update和delete。
 * none:                        所有情况下均不进行关联操作。这是默认值。
 * save-update:          在执行save/update/saveOrUpdate时进行关联操作。
 * delete:                      在执行delete 时进行关联操作。
 * all-delete-orphan: 当一个节点在对象图中成为孤儿节点时，删除该节点
 *
 * 2.inverse：如果一方的映射文件中设置为true，说明在映射关系(一对多，多对多等)中让对方来维护关系。
 * 如果为false，就自己来维护关系。默认值是false。当inverse=true时它所对于的cascade失效。
 *
 * 3.lazy：lazy属性默认为true，设置为false后将不管使用没有都会加载。
 * get没有缓存策略 此时不管user是否使用了只要get调用就会立刻查询数据库。
 * load默认采用类级别的加载策略。
 *
 * 4.fetch：fetch 默认是select 另外发送一条select语句抓取当前对象关联实体或集合。
 * 当配置为join时会通过select语句使用外连接来加载其关联实体或集合此时lazy会失效。
 * fetch为select是会多一条查询语句，但是可以设置l懒加载。
 *
 *
 * 参考：
 * cascade和inverse：
 * http://www.cnblogs.com/whgk/p/6135591.html
 *fetch：
 * http://dongruan00.iteye.com/blog/1835058
 *
 * @author yu
 * @date 2017/7/13.
 */
public class AdvanceTest {

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
    public  void test(){//全部默认的情况下===user中cascade = none inverse = false

        User user = new User();
        user.setUsername("用户111");
        user.setSex("1");
        user.setBirthday(new Date(System.currentTimeMillis()));
        UserOrder userOrder1 = new UserOrder();
        userOrder1.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder1.setTip("订单test111");
        UserOrder userOrder2 = new UserOrder();
        userOrder2.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder2.setTip("订单test22");

        user.getOrderList().add(userOrder1);
        user.getOrderList().add(userOrder2);

        userOrder1.setUser(user);
        userOrder2.setUser(user);

        session.save(user);
        session.save(userOrder1);
        session.save(userOrder2);

    }


    @Test
    public  void testCascade(){//--user中cascade = save-update inverse = false(默认)

        User user = new User();
        user.setUsername("用户111");
        user.setSex("1");
        user.setBirthday(new Date(System.currentTimeMillis()));
        UserOrder userOrder1 = new UserOrder();
        userOrder1.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder1.setTip("订单111");

        user.getOrderList().add(userOrder1);
        userOrder1.setUser(user);

         /*-- 在user表映射文件中配置cascade之后，save user时会级联save user_order */
        //session.save(userOrder1);
        session.save(user);
    }

    @Test
    public  void testInverse(){//---inverse=true cascade失效

        User user = new User();
        user.setUsername("用户111");
        user.setSex("1");
        user.setBirthday(new Date(System.currentTimeMillis()));
        UserOrder userOrder1 = new UserOrder();
        userOrder1.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder1.setTip("订单inverse111");
        UserOrder userOrder2 = new UserOrder();
        userOrder2.setCreateTime(new Date(System.currentTimeMillis()));
        userOrder2.setTip("订单inverse222");

        /*--在user表中设置inverse为true后表示user不对主从关系进行维护，下面的代码可以省去--*/
      /*  user.getOrderList().add(userOrder1);
        user.getOrderList().add(userOrder2);*/

        userOrder1.setUser(user);
        userOrder2.setUser(user);

         /*-- 当user映射文件中inverse配置为true（不维护）即使cascade=save-update下面save不能省去（级联失效）-- */
        session.save(userOrder1);
        session.save(userOrder2);

        session.save(user);
    }

    @Test
    public  void testLazy(){//lazy属性默认为true，设置为false后将不管使用没有都会加载

    /*    //get没有缓存策略 此时不管user是否使用了只要get调用就会立刻查询数据库
        User user = session.get(User.class , 1);
        Set<UserOrder> orderList = user.getOrderList();
        //默认情况下lazy=true，只有在使用的时候才会从数据库中查询订单数据
        for (UserOrder userOrder : orderList) {
            System.out.println("userOrder=="+userOrder);
        }*/

        System.out.println("--------------------------并不华丽的分割线------------------");

        /*此时不查询数据库*/
        User user1 = session.load(User.class , 1);
        /*当user1使用的时候才查询*/
        Set<UserOrder> orderList1 = user1.getOrderList();
        for (UserOrder userOrder1 : orderList1) {
            System.out.println("userOrder=="+userOrder1);
        }
    }

    @Test
    public void testFetch(){//f
        User user1 = session.load(User.class , 1);
        /*当user1使用的时候才查询*/
        Set<UserOrder> orderList1 = user1.getOrderList();
        for (UserOrder userOrder1 : orderList1) {
            System.out.println("userOrder=="+userOrder1);
        }
    }


    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
