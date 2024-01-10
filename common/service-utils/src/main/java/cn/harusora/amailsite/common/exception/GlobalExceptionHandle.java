package cn.harusora.amailsite.common.exception;

import cn.harusora.amailsite.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.getMessage());
        return Result.error("系统异常，无法意料的错误，请联系管理员");
    }

    @ExceptionHandler({AmailException.class})
    @ResponseBody
    public Result fail(AmailException e) {
        log.error(e.getMessage());
        return Result.fail(e.getMessage());
    }
}


