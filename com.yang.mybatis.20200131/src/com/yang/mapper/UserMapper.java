package com.yang.mapper;

import com.yang.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /*
    mapper接口编写规则:
    1.namespace必须和Mapper接口类路径一致
    2.id必须和Mapper接口方法名一致
    3.parameterType必须和接口方法参数类型一致
    4.resultType必须和接口方法返回值类型一致
    **/

    // 通过指定的ID来获取user对象
    User getUserById(Integer id);

    // 获取全部用户
    List<User> getUsers();

    // 新建用户
    void insertUser(User user);

    // 删除用户,可以通过指定param的value来确定xml文件中使用的参数名称
    void deleteUser(@Param("id") Integer id);

    // 将类按照map格式返回
    Map<String, Object> getUserMapById(@Param("id") Integer id);

    // 多参数传递
    User getUserByArgs(@Param("id") Integer id, @Param("username") String username);

    // 结果集使用resultMap
    User getResultMapById(@Param("id") Integer id);

    // 根据用户订单关系获取用户
    User getUserThoughRelation(@Param("orderId") Integer orderId);

}
