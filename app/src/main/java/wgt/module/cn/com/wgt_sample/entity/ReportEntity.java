package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class ReportEntity {
    private int currentPage;
    private int size;
    private List<ReportSonEntity> rows;
    private int total;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ReportSonEntity> getRows() {
        return rows;
    }

    public void setRows(List<ReportSonEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public class ReportSonEntity {
        // 问题上报内容
        private String clueDescribe;
        // 问题上报编号
        private String clueNo;
        private long createTime;
        private String reportIds;
        private String id;
        // 上报人id
        private String userId;
        // 上报人
        private String userName;
        private int reportRoleId;
        // 0未受理，1已受理，2已完结
        private int state;
        private String receiveId;
        // 获取上报类型。
        private List<Dto> dtoList;
        private List<ReportFlowsEntity> flows;

        public List<Dto> getDtoList() {
            return dtoList;
        }

        public void setDtoList(List<Dto> dtoList) {
            this.dtoList = dtoList;
        }

        public String getClueNo() {
            return clueNo;
        }

        public void setClueNo(String clueNo) {
            this.clueNo = clueNo;
        }

        public String getReceiveId() {
            return receiveId;
        }

        public void setReceiveId(String reveiveId) {
            this.receiveId = reveiveId;
        }

        public int getReportRoleId() {
            return reportRoleId;
        }

        public void setReportRoleId(int reportRoleId) {
            this.reportRoleId = reportRoleId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }


        public String getClueDescribe() {
            return clueDescribe;
        }

        public void setClueDescribe(String clueDescribe) {
            this.clueDescribe = clueDescribe;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getReportIds() {
            return reportIds;
        }

        public void setReportIds(String reportIds) {
            this.reportIds = reportIds;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<ReportFlowsEntity> getFlows() {
            return flows;
        }

        public void setFlows(List<ReportFlowsEntity> flows) {
            this.flows = flows;
        }
    }

    public class ReportFlowsEntity {
        private String clueId;
        private long createTime;
        private String id;
        private String remark;
        private String receiveName;
        private String reveiveId;
        private int state;

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getClueId() {
            return clueId;
        }

        public void setClueId(String clueId) {
            this.clueId = clueId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getReveiveId() {
            return reveiveId;
        }

        public void setReveiveId(String reveiveId) {
            this.reveiveId = reveiveId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    public class Dto {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
