package cn.harusora.amailsite.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * @version V1.0
 * @Date: 11:37
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    /**
     * 操作数据库
     */

    /**
     * 切面
     */
    @Around(value = "execution(* cn.harusora.amailsite.*.controller.*.*(..))")
    @Order(1)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成请求唯一 id
        String requestId = UUID.randomUUID().toString();
        //用户id

        //用户名


        /*获取请求体内容*/
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestUri = request.getRequestURI();/*获取请求地址*/
        //url
        String requestURL = String.valueOf(request.getRequestURL());
        //请求方法
        String requestMethod = request.getMethod();
        //ip地址
        String remoteAdr = this.getIpAddress(request);

        /*从切面值入点获取植入点方法*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*获取切入点方法*/
        Method method = signature.getMethod();
        //类名
        String className = method.getDeclaringClass().getName();
        //方法名
        String methodName = method.getName();
        //require参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        //provide参数
        Object[] args = joinPoint.getArgs();
        String provideParam = Arrays.toString(args);

        log.info("request start，id: {}, url: {}, ip: {}, method: {}, params: {}",
                requestId,
                requestURL,
                remoteAdr,
                requestMethod,
                provideParam);
        log.info("opera start，className: {}, methodName: {}, parameterTypes: {}",
                className,
                methodName,
                parameterTypes);

        Object proceed = null;
        //操作时间
        long startTime = System.currentTimeMillis();
        try {
            //执行增强后的方法
            proceed = joinPoint.proceed();

        } catch (Throwable throwable) {
            //类型

            //返回结果

            //报错信息
            throw throwable;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("request end, id: {}, cost: {}ms",
                    requestId,
                    endTime-startTime);
        }
        return proceed;
    }

    //ip处理工具类
    public String getIpAddress(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }
}