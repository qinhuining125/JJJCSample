package wgt.module.cn.com.wgt_sample.entity;

public class ReportStateEntity {
    // 是否有未办结的
    private boolean close;
    // 是否有未受理的
    private boolean accept;

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
