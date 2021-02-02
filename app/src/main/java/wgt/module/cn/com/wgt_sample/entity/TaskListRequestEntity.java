package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class TaskListRequestEntity {
    private int currentPage;
    private TaskListRequestSonEntity query;

    public TaskListRequestEntity(int currentPage, TaskListRequestSonEntity query) {
        this.currentPage = currentPage;
        this.query = query;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public TaskListRequestSonEntity getQuery() {
        return query;
    }

    public void setQuery(TaskListRequestSonEntity query) {
        this.query = query;
    }

    public static class TaskListRequestSonEntity{
        private String state;
        private String startTime;
        private String endTime;
        private String areaCode;

        public TaskListRequestSonEntity(String state, String startTime, String endTime, String areaCode) {
            this.state = state;
            this.startTime = startTime;
            this.endTime = endTime;
            this.areaCode = areaCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }
    }
}
