package com.hobro11.command.security.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class AuthCheckAop {

    @Pointcut("@annotation(com.hobro11.command.security.aop.PreAuthCheck)")
    public void preAuthCheck() {
    }

    @Before("preAuthCheck()")
    public void beforePreAuthCheck(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (isSupport(method) == false) {
            return;
        }

        PreAuthCheck preAuthCheck = method.getAnnotation(PreAuthCheck.class);
        String idFieldName = preAuthCheck.memberIdParam();
        String role = preAuthCheck.role();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (StringUtils.hasText(idFieldName)) {
            Parameter[] parameters = method.getParameters();
            Object[] args = joinPoint.getArgs();

            Long id = getIdFromArgs(parameters, args, idFieldName);
            if (id == null) {
                throw new RuntimeException("Unauthorized");
            }

            String username = authentication.getName();

            if (Long.parseLong(username) != id) {
                throw new RuntimeException("Unauthorized");
            }
        }

        if (StringUtils.hasText(role)) {
            if (isContainRole(role, authentication) == false) {
                throw new RuntimeException("Unauthorized");
            }
        }
    }

    boolean isSupport(Method method) {
        return method.isAnnotationPresent(PreAuthCheck.class);
    }

    private Long getIdFromArgs(Parameter[] parameters, Object[] args, String idFieldName) {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getName().equals(idFieldName)) {
                return (Long) args[i];
            }
        }
        return null;
    }

    private boolean isContainRole(String role, Authentication authentication) {
        return authentication.getAuthorities().stream().filter(a -> a.getAuthority().equals(role)).findFirst()
                .isPresent();
    }

}
