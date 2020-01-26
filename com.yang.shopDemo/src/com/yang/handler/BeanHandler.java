package com.yang.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * 设置查询单一类型的结果集处置方法
 *
 * @param <T> 范型
 */
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType;

    // 构造函数，设置当前类型的类
    public BeanHandler(Class<T> classType) {
        this.classType = classType;
    }

    // 处理函数
    @Override
    public T handle(ResultSet rs) throws Exception {
        // 结果集有结果开始处理
        if (rs.next()) {
            // 1。实例化一个当前处理类型的对象
            T obj = this.classType.getDeclaredConstructor().newInstance();
            // 2。获取这个类型信息，属性， Object.class就是删除那个默认的class的属性
            BeanInfo beanInfo = Introspector.getBeanInfo(this.classType, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 从结果集中，获取对应的属性值
                Object object = rs.getObject(propertyDescriptor.getName());
                // 利用反射，调用set方法，为对象赋值
                propertyDescriptor.getWriteMethod().invoke(obj, object);
            }
            return obj;
        }
        return null;
    }
}
