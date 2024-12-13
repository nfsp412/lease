package org.asuka.lease.common.exception;

import lombok.Data;
import org.asuka.lease.common.result.ResultCodeEnum;

@Data
public class RoomException extends RuntimeException {
    private Integer code;

    public RoomException() {
    }

    public RoomException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public RoomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "RoomException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
