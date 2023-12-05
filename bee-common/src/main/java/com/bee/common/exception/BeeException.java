package com.bee.common.exception;

import com.bee.common.util.common.MessageUtils;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description 自定义异常
 */
public class BeeException extends RuntimeException {

    private static final long serialVersionUID = 3803973388799360654L;

    private int code;

    private String msg;

    public BeeException(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(code);
    }

    public BeeException(int code, String... params) {
        this.code = code;
        this.msg = MessageUtils.getMessage(code, params);
    }

    public BeeException(int code, Throwable e) {
        super(e);
        this.code = code;
        this.msg = MessageUtils.getMessage(code);
    }

    public BeeException(int code, Throwable e, String... params) {
        super(e);
        this.code = code;
        this.msg = MessageUtils.getMessage(code, params);
    }

    public BeeException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
    }

    public BeeException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
