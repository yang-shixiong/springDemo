package com.yang.handler;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> classType;

    public BeanListHandler(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        // 1。初始化一个列表，存储我们的对象
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            // 2。实例化一个当前处理类型的对象
            T obj = this.classType.getDeclaredConstructor().newInstance();

            // 3。获取这个类型信息，属性， Object.class就是删除那个默认的class的属性
            BeanInfo beanInfo = Introspector.getBeanInfo(this.classType, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 4。从结果集中，获取对应的属性值
                Object object = rs.getObject(propertyDescriptor.getName());
                // 5。利用反射，调用set方法，为对象赋值
                propertyDescriptor.getWriteMethod().invoke(obj, object);
            }
            // 6。向列表添加对象
            list.add(obj);
        }
        return list;
    }
}
