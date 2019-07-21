package de.caterwings.catering.aspect;

import de.caterwings.catering.constant.ErrorCodeEnum;
import de.caterwings.catering.exception.CateringServiceException;
import de.caterwings.catering.validation.annotation.QueryParamPredicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class QueryParamPredicateAspect {


    @Before("@annotation(de.caterwings.catering.validation.annotation.QueryParamPredicate)")
    public void execute(final JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final QueryParamPredicate annotation = method.getAnnotation(QueryParamPredicate.class);
        final List<String> validParams = Arrays.asList(annotation.validParams());
        for (Object key : ((Map) joinPoint.getArgs()[0]).keySet()) {
            if (!CollectionUtils.containsAny(validParams, key)) {
                throw new CateringServiceException(ErrorCodeEnum.INVALID_PARAM, "invalid param " + key);
            }
        }
    }
}


