package yang.Transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 这个就是我们的事务类
@Component
@Aspect // 声明这是一个切片类
public class Transaction {
    // 使用这个可以声明一个切入点，这样后续就可以直接引用
    @Pointcut(value = "execution(* yang.UserService.Impl.*.*(..))")
    public void pointcut() {
    }

    // 定义在方法之前调用的，也就是AOP中的前置通知
    @Before("Transaction.pointcut()")
    public void before() {
        System.out.println("-----will do something before you---");
    }

    // 定义一个在方法之后调用的增强功能，也就是AOP中后置通知,如果出现错误不执行
    @AfterReturning(value = "Transaction.pointcut()", returning = "val")
    public void afterReturning(Object val) {
        System.out.println("-----will do something after you-----" + val);
    }

    // 环绕增强，point就是切入点，可以让程序按照我们既定方针执行
    @Around("Transaction.pointcut()")
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
    @AfterThrowing(value = "Transaction.pointcut()", throwing = "ex")
    public void afterException(Exception ex) {
        System.out.println("-----get error-----" + ex.getMessage());
    }

    // 这个是最终执行的，也就是相当于finally的方法
    @After("Transaction.pointcut()")
    public void after() {
        System.out.println("-----after all done ------------");
    }
}
