package cn.harusora.amailsite.common.exception;

import cn.harusora.amailsite.common.result.Result;
import cn.harusora.amailsite.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error("{}, {}, cause by: {}", e.getMessage(), e.getClass(), e.getStackTrace()[0]);
        return Result.error("系统异常，无法意料的错误，请联系管理员");
    }

    @ExceptionHandler({AmailException.class})
    @ResponseBody
    public Result fail(AmailException e) {
        log.error("{}, {}, cause by: {}", e.getMessage(), e.getClass(), e.getStackTrace()[0]);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Result fail(MethodArgumentNotValidException e) throws Throwable {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 拼接错误信息
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append(";");
        }
        sb.deleteCharAt(sb.length() - 1);

        log.error("{}, {}, cause by: {}", sb, e.getClass(), e.getStackTrace()[0]);
        return Result.fail(sb);
    }
}


