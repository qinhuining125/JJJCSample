package wgt.module.cn.com.wgt_sample.entity;

public class XunchaProcessEntity {
    private String id;
    private String userId;
    private String patrolName;

    public String getPatrolName() {
        return patrolName;
    }

    public void setPatrolName(String patrolName) {
        this.patrolName = patrolName;
    }

    public XunchaProcessEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
