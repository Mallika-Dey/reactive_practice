package com.example.reactive.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceLog {
    Log logger = LogFactory.getLog(UserServiceLog.class);
}
