package com.cloud.base.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SecurityContext {

    public static Principal getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        return (Principal) subject.getPrincipal();
    }

    public static String getAccount() {
        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        return principal.getAccount();
    }

}
