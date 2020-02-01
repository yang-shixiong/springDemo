package com.yang.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.domain.User;
import com.yang.mapper.UserMapper;
import com.yang.mapper.UserMapper2;
import com.yang.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyTest {

    @Test
    public void testWhere() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用这个封装的where标签，password传入为空，可以看出，执行的sql语句就会不带后面的password
        List<User> userList = userMapper.getUsers("yang", null);  // ==>  Preparing: select * from `user` WHERE username=?
        // 如果全部为空，那么就会忽略所有的if字段
        List<User> userList2 = userMapper.getUsers(null, null);  // ==>  ==>  Preparing: select * from `user`
        // 第一个if条件我们的是带有if的，而sql语句中不存在，因此可以确认，where确实是把第一个语句中的开头的and删除了。
        List<User> users = userMapper.getUsers("shi", "5678");  // ==>  Preparing: select * from `user` WHERE username=? and password=?
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testTrim() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用trim标签的映射，在if判断中第一个前置有and，最后一个语句后置and，利用trim的删除，删除掉了
        List<User> users = userMapper.getUserList("shi", "5678");  // ==>  Preparing: select * from `user` where username=? and password=?
        for (User user : users) {
            System.out.println(user);  // User{id=2, username='shi', password='5678'}
        }
        // 两个传入都是空值，可以看出不符合if，不执行
        List<User> users2 = userMapper.getUserList("", "");  // ==>  Preparing: select * from `user`
        sqlSession.close();
    }

    @Test
    public void testChoose() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 我们可以看出这个两个参数均满足之前所说的条件，但是最终只是执行了最上面的满足条件的语句
        List<User> users = userMapper.getUserChoose("shi", "5678");  // ==>  Preparing: select * from `user` WHERE username=?
        for (User user : users) {
            System.out.println(user);
        }
        // 两个都不满足，就会执行otherwise
        List<User> users2 = userMapper.getUserList("", "");  // ==>  Preparing: select * from `user`
        sqlSession.close();
    }

    @Test
    public void testForEach() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 使用数组或者列表都可以，主要看java中的映射是如何定义的，可以看出，foreach会帮助我们循环传入的数据并格式化为sql需求的样子
        List<User> users = userMapper.getUserByIds(new Integer[]{11, 12, 13, 14, 15});  // ==>  Preparing: select * from `user` where id in ( ? , ? , ? , ? , ? )  ==> Parameters: 11(Integer), 12(Integer), 13(Integer), 14(Integer), 15(Integer)
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
        // User{id=11, username='mark', password='1111'}
        // User{id=12, username='mark', password='1111'}
        // User{id=13, username='mark', password='1111'}
        // User{id=14, username='mark', password='1111'}
        // User{id=15, username='mark', password='1111'}
    }

    @Test
    public void update() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("yang");
        user.setPassword("1234");
        user.setId(2);
        // 通过sql语句可以看出，虽然名称设置的是yang，但是我们在bind标签中拦截了username并且在最后加了一个bind，最终他们会添加上。
        userMapper.updateUser(user);  // ==>  Preparing: update `user` SET username=?, password=? where id=?   ==> Parameters: yangbind(String), 1234(String), 2(Integer)
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUser() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 可以看出使用这个之后就会把之前的定义的sql进行拼接,并且通过property进行传值，sql语句块也接受到了，只查询username
        User user2 = userMapper.getUserById(2);  // ==>  Preparing: select `username` from `user` where id=?
        System.out.println(user2);  // User{id=null, username='yangbind', password='null'}
        sqlSession.close();
    }

    /**
     * 测试一级缓存
     * 从这个测试缓存可以看出两次查询相同的mapper对象，
     * 只查询了一次数据库，并且两个对象是相同的，这个是以及缓存
     */
    @Test
    public void testCache(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        User user1 = userMapper.getUserById(2);
        System.out.println(user1);  // User{id=2, username='yangbind', password='1234'}
        User user2 = userMapper.getUserById(2);
        System.out.println(user2);  // User{id=2, username='yangbind', password='1234'}
        System.out.println(user1 == user2);  // true
        sqlSession.close();
        /*
        Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@66ea810]
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        Cache Hit Ratio [com.yang.mapper.UserMapper2]: 0.0
        User{id=2, username='yangbind', password='1234'}
        true
         */
    }

    /**
     * 通过打印结果可以看出，如果中间执行了一次数据库改变操作，那么就是是一级缓存失效
     * 并且同时我们也可以发现，二级缓存也没有作用，这是因为，只有关闭sqlSEssion之后，一级缓存才会将内容写入二级缓存
     */
    @Test
    public void testCache2(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        User user1 = userMapper.getUserById(2);
        System.out.println(user1);  // User{id=2, username='yangbind', password='1234'}
        User user = new User();
        user.setUsername("yang");
        user.setPassword("13456");
        userMapper.insertUser(user);
        User user2 = userMapper.getUserById(2);
        System.out.println(user2);  // User{id=2, username='yangbind', password='1234'}
        System.out.println(user1 == user2);  // false
        sqlSession.close();
        /*
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        ==>  Preparing: insert into `user`(username, password) values (?, ?)
        ==> Parameters: yang(String), 13456(String)
        <==    Updates: 1
        Cache Hit Ratio [com.yang.mapper.UserMapper2]: 0.0
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        false
         */
    }

    /**
     * 这个是关闭了一级缓存，这个我们没有关闭sqlSession，使用同一个sqlSession，
     * 执行结果显示是查询了两次数据库
     */
    @Test
    public void testSecond(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        User user1 = userMapper.getUserById(2);
        System.out.println(user1);  // User{id=2, username='yangbind', password='1234'}
        User user2 = userMapper.getUserById(2);
        System.out.println(user2);  // User{id=2, username='yangbind', password='1234'}
        System.out.println(user1 == user2);  // false
        sqlSession.close();
        /*
        Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@508dec2b]
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        Cache Hit Ratio [com.yang.mapper.UserMapper2]: 0.0
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        false
         */
    }

    /**
     * 这个是关闭了一级缓存，这个我们关闭sqlSession，使用同一个sqlSession，
     * 执行结果显示是查询了一次数据库，并且两个对象都是一样的
     */
    @Test
    public void testSecond2(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        User user1 = userMapper.getUserById(2);
        System.out.println(user1);  // User{id=2, username='yangbind', password='1234'}
        sqlSession.close();
        SqlSession sqlSession2 = MyBatisUtils.openSession();
        UserMapper2 userMapper2 = sqlSession2.getMapper(UserMapper2.class);
        User user2 = userMapper2.getUserById(2);
        System.out.println(user2);  // User{id=2, username='yangbind', password='1234'}
        System.out.println(user1 == user2);  // true
        sqlSession2.close();
        /*
        Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@798162bc]
        ==>  Preparing: select * from `user` where id=?
        ==> Parameters: 2(Integer)
        <==    Columns: id, username, password
        <==        Row: 2, yangbind, 1234
        <==      Total: 1
        User{id=2, username='yangbind', password='1234'}
        Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@798162bc]
        Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@798162bc]
        Returned connection 2038522556 to pool.
        Cache Hit Ratio [com.yang.mapper.UserMapper2]: 0.5
        User{id=2, username='yangbind', password='1234'}
        true
         */
    }

    @Test
    public void testPage() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        // 这个就是指定使用分页器，在查询之前声明，否则不起作用，第一个参数是第几页，第二个参数是一页几条
        Page<User> page = PageHelper.startPage(1,2);
        List<User> users = userMapper.getUsers();
        for (User user : users) {
            System.out.println(user);
            /*
            User{id=2, username='yangbind', password='1234'}
            User{id=3, username='xiong', password='9012'}
             */
        }
        System.out.println(page.getPageNum());  // 1  获取当前页吗
        System.out.println(page.getPageSize());  // 2 获取当前页的条数
        System.out.println(page.getPages());  // 6  获取总页数
        System.out.println(page.getTotal());  // 11 获取总条数
    }

    @Test
    public void testPage2() {
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        // 这个就是指定使用分页器，在查询之前声明，否则不起作用，第一个参数是第几页，第二个参数是一页几条
        Page<User> page = PageHelper.startPage(1,2);
        List<User> users = userMapper.getUsers();
        // 将查询的结果做进一步封装，可以获取是否有下一页以及是否有上一页，并且可以返回页码，第二个参数是指定页码
        PageInfo<User> pageInfo = new PageInfo<>(users, 2);
        for (User user : users) {
            System.out.println(user);
            /*
            User{id=2, username='yangbind', password='1234'}
            User{id=3, username='xiong', password='9012'}
             */
        }
        for (User user : pageInfo.getList()) {
            System.out.println(user);
            /*
            User{id=2, username='yangbind', password='1234'}
            User{id=3, username='xiong', password='9012'}
             */
        }
        System.out.println(pageInfo.getPageNum());  // 1  获取当前页吗
        System.out.println(pageInfo.getPageSize());  // 2 获取当前页的条数
        System.out.println(pageInfo.getPages());  // 6  获取总页数
        System.out.println(pageInfo.getTotal());  // 11 获取总条数
        System.out.println(pageInfo.isHasPreviousPage());  // false 获取是否有上一页
        System.out.println(pageInfo.isHasNextPage());  // true 获取是否有下一页
        System.out.println(Arrays.toString(pageInfo.getNavigatepageNums()));  // [1, 2] 获取展示的页码
    }

}
