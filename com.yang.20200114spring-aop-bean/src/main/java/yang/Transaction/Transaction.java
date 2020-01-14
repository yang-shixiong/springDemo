package yang.Transaction;

import org.aspectj.lang.ProceedingJoinPoint;

// 这个就是我们的事务类
public class Transaction {

    // 定义在方法之前调用的，也就是AOP中的前置通知
    public void before() {
        System.out.println("-----will do something before you---");
    }

    // 定义一个在方法之后调用的增强功能，也就是AOP中后置通知,如果出现错误不执行
    public void afterReturning() {
        System.out.println("-----will do something after you-----");
    }

    // 环绕增强，point就是切入点，可以让程序按照我们既定方针执行
    public Object aroundMethod(ProceedingJoinPoint point) {
        Object o = null;
        try {
            System.out.println("-----will do something before you---");
            o = point.proceed();
            System.out.println("-----will do something after you-----");
        } catch (Throwable e) {
            System.out.println("-----get error-----" + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("-----after all done ------------");
        }
        return o;
    }

    // 这个是出现错误执行的
    public void afterException() {
        System.out.println("-----get error-----");
    }

    // 这个是最终执行的，也就是相当于finally的方法
    public void after() {
        System.out.println("-----after all done ------------");
    }
}
