package com.comrade.aop;

import com.comrade.model.Welcome;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DearComradeAopConfig {

    @Pointcut("execution(* com.comrade.service.WelcomeService.*(..))")
    public void welcomeMessagePointCut(){
        log.info("welcomeMessagePointCut()");
    }

    @Pointcut("execution(* com.comrade.service.*.*(..))")
    public void commonPointCut(){
        log.info("commonPointCut()");
    }



    @Pointcut("execution(* com.comrade.service.WelcomeService.welcomeMessageException(..))")
    public void welcomeMessageExceptionPointCut(){
        log.info("welcomeMessageExceptionPointCut()");
    }

    @Before("com.comrade.aop.DearComradeAopConfig.welcomeMessagePointCut()")
    public void welcomeMessageAdviceBefore(){
        log.info("welcomeMessageAdviceBefore() = @Before");
    }

    @Around("welcomeMessagePointCut()")
    public Object welcomeMessageAdviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("welcomeMessageAdviceAround() = @Around");
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Time taken to complete method execution = {}", (endTime - startTime));
        return object;
    }

    @After("welcomeMessagePointCut()")
    public void welcomeMessageAdviceAfter(){
        log.info("welcomeMessageAdviceAfter() = @After");
    }

    @AfterReturning("com.comrade.aop.DearComradeAopConfig.welcomeMessagePointCut()")
    public void welcomeMessageAdviceAfterReturning(){
        log.info("welcomeMessageAdviceAfterReturning() = @AfterReturning");
    }

    @AfterReturning(value = "welcomeMessagePointCut()",returning = "message")
    public void welcomeMessageAdviceAfterReturningWithObject(JoinPoint joinPoint, Object message){
        log.info("welcomeMessageAdviceAfterReturningWithObject() = @AfterReturning");
        if (message instanceof Welcome welcome) {
            log.info("welcomeMessageAdviceAfterReturning() = {}",welcome);
        }
    }

    @AfterThrowing(value = "com.comrade.aop.DearComradeAopConfig.welcomeMessageExceptionPointCut()", throwing = "throwable")
    public void welcomeMessageExceptionAdviceAfterThrowing(JoinPoint joinPoint, Throwable throwable){
        log.info("welcomeMessageExceptionAdviceAfterThrowing() = @AfterThrowing");
        log.info("joinPoint.getKind() = {}", joinPoint.getKind());
        log.info("joinPoint.getSignature().getName() = {}", joinPoint.getSignature().getName());
        log.info("joinPoint.getSignature().toString() = {}", joinPoint.getSignature().toString());
        log.error("welcomeMessageExceptionAdvice() {}",throwable.getMessage());
    }

    @Around("@annotation(com.comrade.annotation.Timer)")
    public Object timeTracker(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Time taken to complete method execution {} = {} ms",proceedingJoinPoint.getSignature().toShortString(), (endTime - startTime));
        return object;
    }
/*
    @Pointcut("aopUserPointCut() && !(aopGetterPointCut() || aopSetterPointCut())")
    @Pointcut("execution(* com.pool.service.*.get*(..))")
	@Pointcut("execution(* com.pool.service.*.set*(..))")
	@Before("execution(* add*(com.shiva.model.Product))")
    @Before("execution(* addProduct(..))")
    @Before("execution(* com.shiva.service.*.*(..))")
    @Before("execution(public void add*())")
    @Before("execution(* add*())")
    @Before("execution(* users*())")
    @Before("execution(public void called())")
	@Before("execution(* getAllAopUsers())")
	@Before("execution(public void com.pool.service.aop.AopUserServiceImpl.called())")
	@Before("execution(public * com.pool.service.aop.AopUserServiceImpl.getAllAopUsers())")
	@Before("execution(public * com.pool.service.aop.AopUserServiceImpl.getAll*())")
	@Before("execution(public java.util.List<com.pool.model.aop.AopUser> com.pool.service.aop.AopUserServiceImpl.getAllAopUsers())")
	@Before("execution(public com.pool.model.aop.AopUser save(com.pool.model.aop.AopUser))")
	@Before("execution(* com.pool.service.aop.*.*(..))")
	@Before("com.pool.config.aop.aopuser.expression.AopUserAspectCommonExpressions.aopAllowPackageNoSetterPointCut()")
	@Around("execution(* com.pool.service.aop.AopUserServiceImpl.getAllAopUsers(..))")
	@AfterThrowing(pointcut = "com.pool.config.aop.aopuser.expression.AopUserAspectCommonExpressions.afterReturningAopUserPointcut()",throwing  ="throwable")
	@AfterReturning(pointcut = "com.pool.config.aop.aopuser.expression.AopUserAspectCommonExpressions.afterReturningAopUserPointcut()", returning = "aopUsers")
	@After("execution(* com.pool.service.aop.AopUserServiceImpl.getAllAopUsers(..))")
 */

}
