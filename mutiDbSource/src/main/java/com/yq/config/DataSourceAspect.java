package com.yq.config;

import com.yq.annotation.SpecifyDBSource;
import com.yq.dds.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源AOP切面定义
 */
@Component
@Aspect
@Slf4j
public class DataSourceAspect {
    //切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
    @Pointcut("execution( * com.yq.mapper.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (m != null && m.isAnnotationPresent(SpecifyDBSource.class)) {
                SpecifyDBSource dbSetting = m.getAnnotation(SpecifyDBSource.class);
                String dbName = dbSetting.value();
                DynamicDataSourceHolder.putDataSource(dbName);
                log.debug("threadId={}, add {} to ThreadLocal", Thread.currentThread().getId(), dbName);
            } else {
                log.debug("use default for there is no specifying or m is null.");
            }
        } catch (Exception e) {
            log.error("threadId={}, Failed to add data to ThreadLocal", Thread.currentThread().getId(), e);
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSource();
    }
}
