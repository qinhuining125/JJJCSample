package wgt.module.cn.com.wgt_sample.utils;

import java.util.List;

public class HttpResultList<T> {
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
    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
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

}
