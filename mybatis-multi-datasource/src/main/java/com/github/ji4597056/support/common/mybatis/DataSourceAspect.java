package com.github.ji4597056.support.common.mybatis;

import java.util.Optional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * datasource aspect
 *
 * @author Jeffrey
 * @since 2018/01/10 17:09
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    /**
     * jointPointExpression
     */
    @Pointcut("execution(* com.github.ji4597056.dao..*.*(..))")
    public void jointPointExpression() {

    }

    /**
     * set datasource key
     *
     * @param point ProceedingJoinPoint
     */
    @Around("jointPointExpression()")
    public Object setDataSourceKey(ProceedingJoinPoint point)
        throws Throwable {
        try {
            Optional.ofNullable(
                AnnotationUtils.findAnnotation(point.getTarget().getClass(), DataSourceType.class))
                .ifPresent(
                    dataSourceType -> DatabaseContextHolder.setDbType(dataSourceType.value()));
            return point.proceed();
        } finally {
            DatabaseContextHolder.clearDbType();
        }
    }
}
