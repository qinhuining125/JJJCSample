package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class SuggestEntity {
    private int currentPage;
    private int size;
    private List<SuggestSonEntity> rows;
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

    public List<SuggestSonEntity> getRows() {
        return rows;
    }

    public void setRows(List<SuggestSonEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public class SuggestSonEntity {
        private String content;
        private long createTime;
        private String userName;
        private String roleName;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
