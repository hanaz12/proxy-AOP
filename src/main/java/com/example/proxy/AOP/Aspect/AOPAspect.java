package com.example.proxy.AOP.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPAspect {

    @Before("execution(* com.example.proxy.AOP.Service.*.*(..))")
    public void logingBeforeMethod(JoinPoint joinPoint) {
        System.out.println("AOPAspect.logingBeforeMethod"+joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.proxy.AOP.Service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[Around] Before: " + joinPoint.getSignature().getName());
long startTime = System.currentTimeMillis();
Object result = joinPoint.proceed();
long endTime = System.currentTimeMillis();
System.out.println("AOPAspect [Around] After: " + joinPoint.getSignature().getName());
System.out.println("AOPAspect[Around] Execution Time: " + (endTime - startTime) + "ms");

    return result;}

    @AfterReturning(
            pointcut = "execution(* com.example.proxy.AOP.Service.*.*(..))",
            returning = "result"
    )
    public void AfterReturning( Object result) {
        System.out.println("AOPAspect AfterReturning: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.proxy.AOP.Service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex)  {
        System.out.println("AOPAspect AfterThrowing: " + joinPoint.getSignature().getName());
    }
}
