package wgt.module.cn.com.wgt_sample.entity;

public class JubaoStateEntity {
    private Boolean pending;
    private Boolean assign;
    private Boolean accept;

    @Override
    public String toString() {
        return "JubaoStateEntity{" +
                "pending=" + pending +
                ", assign=" + assign +
                ", accept=" + accept +
                '}';
    }

    public JubaoStateEntity(Boolean pending, Boolean assign, Boolean accept) {
        this.pending = pending;
        this.assign = assign;
        this.accept = accept;
    }

    public JubaoStateEntity() {
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Boolean getAssign() {
        return assign;
    }

    public void setAssign(Boolean assign) {
        this.assign = assign;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }
}
