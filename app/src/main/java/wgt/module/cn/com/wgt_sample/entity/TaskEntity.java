package wgt.module.cn.com.wgt_sample.entity;

import android.media.Image;

import com.jelly.mango.MultiplexImage;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class TaskEntity {
    private int currentPage;
    private int size;
    private List<TaskSonEntity> rows;
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

    public List<TaskSonEntity> getRows() {
        return rows;
    }

    public void setRows(List<TaskSonEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public class TaskSonEntity {
        // 任务内容
        private String content;
        // 任务时间
        private long createTime;
        // 流程
        private List<TaskFlowsEntity> flows;
        // 任务状态
        private int state;
        // 任务id
        private String id;
        // 任务发起人id
        private String userId;
        // 任务发起人
        private String userName;
        // 任务发起人id
        private String acceptUserId;
        // 任务发起人
        private String acceptRoleId;
        private int roleId;

        //9.17新增 图片
        public List<String> imgApp;

        public List<String> getImgApp() {
            return imgApp;
        }

        public void setImgApp(List<String> imgApp) {
            this.imgApp = imgApp;
        }


        public String getqR_Code() {
            return qR_Code;
        }

        public void setqR_Code(String qR_Code) {
            this.qR_Code = qR_Code;
        }

        public  String qR_Code;


        public TaskSonEntity(String content) {
            this.content = content;
        }





        public String getAcceptUserId() {
            return acceptUserId;
        }

        public void setAcceptUserId(String acceptUserId) {
            this.acceptUserId = acceptUserId;
        }

        public String getAcceptRoleId() {
            return acceptRoleId;
        }

        public void setAcceptRoleId(String acceptRoleId) {
            this.acceptRoleId = acceptRoleId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public List<TaskFlowsEntity> getFlows() {
            return flows;
        }

        public void setFlows(List<TaskFlowsEntity> flows) {
            this.flows = flows;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
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

    public class TaskFlowsEntity {
        // 时间。
        private long createTime;
        // 流程Id。
        private String id;
        // 接收人id。
        private String receiveId;
        // 接收人角色id。
        private int receiveRoleId;
        // 任务id。
        private String taskId;
        // 接收人。
        private String receiveName;
        // 办结内容。
        private String remark;
        // 0未受理，1已受理，2已办结。
        private int state;

        @Override
        public String toString() {
            return "TaskFlowsEntity{" +
                    "createTime=" + createTime +
                    ", id='" + id + '\'' +
                    ", receiveId='" + receiveId + '\'' +
                    ", receiveRoleId=" + receiveRoleId +
                    ", taskId='" + taskId + '\'' +
                    ", receiveName='" + receiveName + '\'' +
                    ", remark='" + remark + '\'' +
                    ", state=" + state +
                    '}';
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
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

        public String getReceiveId() {
            return receiveId;
        }

        public void setReceiveId(String receiveId) {
            this.receiveId = receiveId;
        }

        public int getReceiveRoleId() {
            return receiveRoleId;
        }

        public void setReceiveRoleId(int receiveRoleId) {
            this.receiveRoleId = receiveRoleId;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
