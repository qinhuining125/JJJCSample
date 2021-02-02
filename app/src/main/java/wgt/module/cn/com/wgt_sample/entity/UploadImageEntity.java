package wgt.module.cn.com.wgt_sample.entity;

public class UploadImageEntity {
    private boolean result;
    private boolean success;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UploadImageEntity{" +
                "result=" + result +
                ", success=" + success +
                '}';
    }
}
