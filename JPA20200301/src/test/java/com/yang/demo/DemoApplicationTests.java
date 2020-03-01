package com.yang.demo;

import com.yang.demo.dao.UserDao;
import com.yang.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    public void testJpaRepository() {
        // 查找所有
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            // User{id=7, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            // User{id=8, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
        }
        // 获取所有，根据id降序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        ArrayList<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        Sort sort = Sort.by(orders);
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ order by user0_.id desc
        List<User> userSorts = userDao.findAll(sort);
        for (User userSort : userSorts) {
            System.out.println(userSort);
            //User{id=8, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=7, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
        }
        // 根据ID查询
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ where user0_.id in (? , ?)
        List<User> allById = userDao.findAllById(ids);
        for (User userById : allById) {
            System.out.println(userById);
            // none
        }
        // 保存所有
        User user2 = new User();
        user2.setUsername("xiao ming");
        user2.setCreateTime(new Date());
        user2.setSex("男");
        User user3 = new User();
        user3.setUsername("小红");
        user3.setCreateTime(new Date());
        user3.setSex("女");
        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user3);
        // Hibernate: insert into user (create_time, u_sex, username) values (?, ?, ?)
        // Hibernate: insert into user (create_time, u_sex, username) values (?, ?, ?)
        List<User> users1 = userDao.saveAll(userList);
        for (User user1 : users1) {
            System.out.println(user1);
            //User{id=9, username='xiao ming', sex='男', createTime=Sun Mar 01 10:58:08 CST 2020, noUse='null'}
            //User{id=10, username='小红', sex='女', createTime=Sun Mar 01 10:58:08 CST 2020, noUse='null'}
        }

        // 根据传入对象删除,无返回对象
        // userDao.deleteInBatch(users1);

        // 根据ID查询,如果查询不到就会报错
        // User one = userDao.getOne(10L);
        // System.out.println(one);

        // 查询，根据对象的示例
        // 设置实例
        User user = new User();
        // 设置查询条件
        user.setUsername("xiao ming");
        // 定义example
        Example<User> example = Example.of(user);
        // select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ where user0_.username=?
        List<User> all = userDao.findAll(example);
        for (User user4 : all) {
            System.out.println(user4);
            // User{id=7, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            // User{id=9, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
        }
    }

    @Test
    public void testPagingAndSort() {
        // 这个翻页需要设定排序规则，注意字段需要与entity的类一致
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        // sort需要传入的是集合
        Sort sort = Sort.by(orders);

        // 查询需求
        // 第2页
        int pageIndex = 2;
        // 每页大小
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
        Page<User> all = userDao.findAll(pageable);
        System.out.println("总记录: " + all.getTotalElements());  // 4
        System.out.println("总页数: " + all.getTotalPages());  // 2
        System.out.println("当前第几页： : " + all.getNumber());  // 1 从0开始
        System.out.println("当前页条数： : " + all.getNumberOfElements());  // 2
        for (User user : all) {
            System.out.println(user);
            // User{id=9, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            // User{id=10, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
        }
    }

    @Test
    public void testCurdRepository() {
        // 保存
        User user = new User();
        user.setUsername("小明1");
        user.setSex("男");
        user.setCreateTime(new Date());
        // Hibernate: insert into user (create_time, u_sex, username) values (?, ?, ?)
        User user1 = userDao.save(user);
        // User{id=12, username='小明1', sex='男', createTime=Sun Mar 01 12:01:35 CST 2020, noUse='null'}
        System.out.println(user1);
        // 保存所有
        user1.setUsername("小明2");
        User user2 = new User();
        user2.setUsername("小明3");
        user2.setSex("男");
        user2.setCreateTime(new Date());
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user1); // 连续添加两个user1
        List<User> userList1 = userDao.saveAll(userList);
        // 从输出结果可以看出，逐条保存，如果有ID就只是更新，不是保存
        for (User user3 : userList1) {
            System.out.println(user3);
            // User{id=12, username='小明2', sex='男', createTime=Sun Mar 01 12:01:35 CST 2020, noUse='null'}
            //User{id=12, username='小明2', sex='男', createTime=Sun Mar 01 12:01:35 CST 2020, noUse='null'}
        }
        // 根据ID查询
        // Hibernate: select user0_.id as id1_0_0_, user0_.create_time as create_t2_0_0_, user0_.u_sex as u_sex3_0_0_, user0_.username as username4_0_0_
        // from user user0_ where user0_.id=?
        Optional<User> user4 = userDao.findById(100L);
        // Optional.empty
        System.out.println(user4);
        // 根据Id判断是否存在
        // Hibernate: select count(*) as col_0_0_ from user user0_ where user0_.id=?
        System.out.println(userDao.existsById(100L)); // false
        // 查询所有
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_
        List<User> users = userDao.findAll();
        for (User user3 : users) {
            System.out.println(user3);
            //User{id=7, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=8, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=9, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=10, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=11, username='小明2', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=12, username='小明2', sex='男', createTime=2020-02-29, noUse='null'}
        }
        // 查询总数
        // Hibernate: select count(*) as col_0_0_ from user user0_
        System.out.println("总数：" + userDao.count());  // 6
        // 根据传入的ID删除
        // 查找id为7的
        Optional<User> user3 = userDao.findById(8L);
        System.out.println(user3);
        // 数据不存在会报错
        userDao.deleteById(8L);
        System.out.println(userDao.existsById(8L));
        // 根据传入对象删除
        // getOne是懒加载，如果直接删除这个对象会报错，需要使用，因此使用findById
        // User user5 = userDao.getOne(9L);
        // findById 返回结果是Optional<T>，调用get即刻返回对象
        // Hibernate: select user0_.id as id1_0_0_, user0_.create_time as create_t2_0_0_, user0_.u_sex as u_sex3_0_0_, user0_.username as username4_0_0_
        // from user user0_ where user0_.id=?
        User user5 = userDao.findById(9L).get();
        System.out.println(user5);
        // Hibernate: delete from user where id=?
        userDao.delete(user5);
        System.out.println(userDao.existsById(9L));
        // 删除所有
        // 看一下表中数据
        System.out.println(userDao.count()); // 6
        //Hibernate: delete from user where id=?
        //Hibernate: delete from user where id=?
        //Hibernate: delete from user where id=?
        //Hibernate: delete from user where id=?
        //Hibernate: delete from user where id=?
        //Hibernate: delete from user where id=?
        userDao.deleteAll();
        // 再看一下表中数据
        System.out.println(userDao.count());  // 0
    }

    @Test
    public void testQueryByExampleExecutor() {
        // 测试数据
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
            // User{id=18, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=19, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=20, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=21, username='小宁', sex='男', createTime=2020-02-29, noUse='null'}
            //User{id=22, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=23, username='小美', sex='女', createTime=2020-02-29, noUse='null'}
            //User{id=24, username='小梅', sex='女', createTime=2020-02-29, noUse='null'}
        }
        // 设置查询条件
        User user = new User();
        user.setId(18L);
        Example<User> of = Example.of(user);
        // 根据传入条件查询一个,如果不是唯一的会报错
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ where user0_.id=18
        Optional<User> one = userDao.findOne(of);
        // Optional[User{id=18, username='xiao ming', sex='男', createTime=2020-02-29, noUse='null'}]
        System.out.println(one);
        // 根据传入条件查询所有
        // 设置查询条件
        User user1 = new User();
        user1.setUsername("小红");
        Example<User> of1 = Example.of(user1);
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ where user0_.username=?
        List<User> all1 = userDao.findAll(of1);
        for (User user2 : all1) {
            System.out.println(user2);
            // User{id=19, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
            // User{id=22, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
        }
        // 根据条件技术
        // Hibernate: select count(user0_.id) as col_0_0_ from user user0_ where user0_.id=18
        System.out.println(userDao.count(of)); //1
        // Hibernate: select count(user0_.id) as col_0_0_ from user user0_ where user0_.username=?
        System.out.println(userDao.count(of1)); //2
        // 根据查询条件判断是否存在
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_ from user user0_ where user0_.id=18
        System.out.println(userDao.exists(of));  // true
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_ from user user0_ where user0_.username=?
        System.out.println(userDao.exists(of1));  // true

        // 查询需求
        // 第2页
        int pageIndex = 1;
        // 每页大小
        int pageSize = 1;
        // 排序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        ArrayList<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
        // 根据查询条件以及分页规则查询
        // Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.u_sex as u_sex3_0_, user0_.username as username4_0_
        // from user user0_ where user0_.username=? order by user0_.id desc limit ?
        Page<User> all2 = userDao.findAll(of1, pageable);
        System.out.println("总记录: " + all2.getTotalElements());  // 2
        System.out.println("总页数: " + all2.getTotalPages());  // 2
        System.out.println("当前第几页： : " + all2.getNumber());  // 0 从0开始
        System.out.println("当前页条数： : " + all2.getNumberOfElements());  // 1
        for (User user2 : all2) {
            System.out.println(user2);
            // User{id=22, username='小红', sex='女', createTime=2020-02-29, noUse='null'}
        }
    }
}
