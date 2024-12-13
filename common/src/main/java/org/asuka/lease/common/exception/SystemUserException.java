package org.asuka.lease.common.exception;

import lombok.Data;
import org.asuka.lease.common.result.ResultCodeEnum;

@Data
public class SystemUserException extends RuntimeException {
    private Integer code;

    public SystemUserException() {
    }

    public SystemUserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SystemUserException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "SystemUserException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
