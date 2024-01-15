package cn.harusora.amailsite.auth.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.harusora.amailsite.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class AuthExceptionHandle {
    @ExceptionHandler({NotLoginException.class})
    @ResponseBody
    public Result notLogin(NotLoginException e) {
        log.error("{}, {}, cause by: {}", e.getMessage(), e.getClass(), e.getStackTrace()[0]);
        return Result.fail("未登录");
    }

    @ExceptionHandler({NotRoleException.class})
    @ResponseBody
    public Result notLogin(NotRoleException e) {
        log.error("{}, {}, cause by: {}", e.getMessage(), e.getClass(), e.getStackTrace()[0]);
        return Result.fail("无权限");
    }
}
