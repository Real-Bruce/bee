package com.bee.common.util;

import com.bee.common.exception.ErrorCode;
import com.bee.common.util.common.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description
 */
@ApiModel(value = "响应")
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 3226396365575268574L;

    /**
     * 编码：0表示成功，其他值为失败
     */
    @ApiModelProperty(value = "编码：0表示成功，其他值为失败")
    private int code = 0;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String msg ="success";

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    public ResultVo<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success() {
        return code == 0;
    }

    public ResultVo<T> error() {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(code);
        return this;
    }
    public ResultVo<T> error(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(code);
        return this;
    }
    public ResultVo<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
    public ResultVo<T> error(String msg) {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
        return this;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
