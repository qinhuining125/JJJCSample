package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class TaskZPRequestEntity {
    private String taskId;
    private String receiveId;
    private String receiveRoleId;

    public TaskZPRequestEntity(String taskId, String receiveId, String receiveRoleId) {
        this.taskId = taskId;
        this.receiveId = receiveId;
        this.receiveRoleId = receiveRoleId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getReceiveRoleId() {
        return receiveRoleId;
    }

    public void setReceiveRoleId(String receiveRoleId) {
        this.receiveRoleId = receiveRoleId;
    }
}
