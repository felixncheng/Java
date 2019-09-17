package com.chengmboy.app.aop;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author cheng_mboy
 */
@Aspect
public class LogAspect {


    @Pointcut("@annotation(com.chengmboy.app.aop.annotation.Log)")
    private void log() {}

    @Around("log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Map<String, Object> paramMap = new HashMap<>();
        Object[] args = proceedingJoinPoint.getArgs();
        String[] argNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < args.length; i++) {
            paramMap.put(argNames[i], args[i]);
        }
        if (paramMap.size() > 0) {
            System.out.println("方法："+ proceedingJoinPoint.getSignature());
            System.out.println("参数： "+JSONObject.toJSON(paramMap));
        }
        System.out.println("pre around");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("post around");
        return proceed;
    }
}
