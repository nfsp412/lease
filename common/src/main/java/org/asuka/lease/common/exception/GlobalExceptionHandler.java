package org.asuka.lease.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.asuka.lease.common.result.Result;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.meta.Exclusive;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 更精确的异常类型的匹配
     * @param e
     * @return
     */
    @ExceptionHandler(SystemUserException.class)
    @ResponseBody
    public Result error(SystemUserException e) {
        e.printStackTrace();
        log.error(e.getCode() + e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }

    /**
     * 更精确的异常类型的匹配
     * @param e
     * @return
     */
    @ExceptionHandler(RoomException.class)
    @ResponseBody
    public Result error(RoomException e) {
        e.printStackTrace();
        log.error(e.getCode() + e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }

    /**
     * 更精确的异常类型的匹配
     * @param e
     * @return
     */
    @ExceptionHandler(LeaseException.class)
    @ResponseBody
    public Result error(LeaseException e) {
        e.printStackTrace();
        log.error(e.getCode() + e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.fail();
    }

}
