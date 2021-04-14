package com.ergou.document.myxcption;

import com.ergou.document.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: luoergou
 * @description:
 * @date: 2021-04-14 21:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException{
    private Integer code;

    private String reason;

    public MyException(String msg) {
        super(msg);
        this.code = ResultCodeEnum.DATA_EXCEPTION.getCode();
    }

    public MyException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    public MyException(ResultCodeEnum codeEnum, String msg) {
        super(msg);
        this.code = codeEnum.getCode();
    }

    public MyException(ResultCodeEnum codeEnum, String msg, String reason) {
        super(msg);
        this.code = codeEnum.getCode();
        this.reason = reason;
    }
}
