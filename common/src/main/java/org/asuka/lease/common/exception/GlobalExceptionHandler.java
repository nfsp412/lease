package org.asuka.lease.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.asuka.lease.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemUserException.class)
    @ResponseBody
    public Result error(SystemUserException e) {
        e.printStackTrace();
        log.error(e.getCode() + e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(RoomException.class)
    @ResponseBody
    public Result error(RoomException e) {
        e.printStackTrace();
        log.error(e.getCode() + e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }


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
