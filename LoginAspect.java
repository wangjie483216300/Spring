package com.CloudDinner.Aspect;

import com.CloudDinner.Dao.LoginDao;
import com.CloudDinner.Model.Message.LoginMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LoginAspect {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(* com.CloudDinner.Service.LoginServer.login())")
    public void getpoint(){}

    @Around("getpoint()")
    public String around(ProceedingJoinPoint point){
        LoginMessage loginMessage=null;
        String result = "false" ;
        try {
            System.out.println(point.getSignature().getName()+"方法的前置通知执行    1");
            //redis缓存实现
            Object[] args = point.getArgs();
            System.out.println(args);
            String passwd = String.valueOf(redisTemplate.opsForValue().get(args[0]));
            if (loginDao.selectID(String.valueOf(args[0]))==passwd){
                result = "true";
            }else {
                loginMessage=(LoginMessage)point.proceed();
                if (loginMessage.getMessage()=="登录成功!"){
                    redisTemplate.opsForValue().set(args[0],args[1],30, TimeUnit.SECONDS);
                    result = "true";
                }
            }
            System.out.println(args[0]);
            System.out.println(redisTemplate.opsForValue().get(args[0]));
            System.out.println(point.getSignature().getName()+"方法的返回通知执行    2");
        } catch (Throwable throwable) {
            System.out.println(point.getSignature().getName()+"方法的异常通知执行    3");
            throwable.printStackTrace();
        }
        System.out.println(point.getSignature().getName()+"方法的后置通知执行    4");
        return result;
    }

}
