package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class ReportSubmitRequestEntity {
    private String reportIds;
    private String clueDescribe;

    public ReportSubmitRequestEntity(String reportIds, String clueDescribe) {
        this.reportIds = reportIds;
        this.clueDescribe = clueDescribe;
    }

    public String getReportIds() {
        return reportIds;
    }

    public void setReportIds(String reportIds) {
        this.reportIds = reportIds;
    }

    public String getClueDescribe() {
        return clueDescribe;
    }

    public void setClueDescribe(String clueDescribe) {
        this.clueDescribe = clueDescribe;
    }
}
