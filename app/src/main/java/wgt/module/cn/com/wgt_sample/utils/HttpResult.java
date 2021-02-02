package wgt.module.cn.com.wgt_sample.utils;

import java.util.List;

import wgt.module.cn.com.wgt_sample.entity.JubaopersonEntity;

/**
 * Created by skc on 2020/6/20.
 */

public class HttpResult<T> {
    private T result;
    /**
     * 调用是否成功
     */
    private boolean success;
    /**
     * 返回结果的code
     */
    private String code;
    /**
     * 返回结果信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
