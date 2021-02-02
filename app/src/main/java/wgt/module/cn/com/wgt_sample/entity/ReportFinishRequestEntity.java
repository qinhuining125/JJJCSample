package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class ReportFinishRequestEntity {
    private String clueId;
    private String remark;

    public ReportFinishRequestEntity(String clueId, String remark) {
        this.clueId = clueId;
        this.remark = remark;
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
