package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class TaskBJRequestEntity {
    private String taskId;
    private String remark;

    public TaskBJRequestEntity(String taskId, String remark) {
        this.taskId = taskId;
        this.remark = remark;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
