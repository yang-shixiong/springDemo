package yang.Transaction;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    private Transaction transaction;


    // 构造函数，传入增强类
    public CglibProxy(Transaction transaction) {
        this.transaction = transaction;
    }

    // 设置被代理对象
    public Object getProxy(Class clazz){
        // 将目标类设置为父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    // object原对象， objects 参数，method调用方法
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 前置增强
        this.transaction.before();
        // 调用方法，可以从invokeSuper猜测出他这是通过反射调用父类的方法，正如我们所说的，cglib就是创建一个目标对象的子类
        Object invoke = methodProxy.invokeSuper(o, objects);
        // 后置增强
        this.transaction.after();
        return invoke;
    }
}
