package com.sp.fc.web.config;

import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

@Getter
@Setter
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
    implements MethodSecurityExpressionOperations {

    MethodInvocation methodInvocation;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, MethodInvocation methodInvocation) {
        super(authentication);
        this.methodInvocation = methodInvocation;
    }

    private Object filterObject;
    private Object returnObject;

    public boolean isStudent() {
        return getAuthentication().getAuthorities().stream()
                .filter(a->a.getAuthority().equals("ROLE_STUDENT"))
                .findAny().isPresent();
    }

    public boolean isTutor() {
        return getAuthentication().getAuthorities().stream()
                .filter(a->a.getAuthority().equals("ROLE_TUTOR"))
                .findAny().isPresent();
    }

    @Override
    public Object getThis() {
        return this;
    }
}
