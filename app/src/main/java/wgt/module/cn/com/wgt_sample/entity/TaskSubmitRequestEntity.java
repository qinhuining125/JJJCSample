package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class TaskSubmitRequestEntity {
    private String content;
    private List<String> receiveId;
    private List<String> receiveRoleId;

    public void setImages(String  images) {
        this.images = images;
    }

    //9.22日新加
//    private List<String>  images;
    private String  images;

    public TaskSubmitRequestEntity(String content, List<String> receiveId, List<String> receiveRoleId, String images) {
        this.content = content;
        this.receiveId = receiveId;
        this.receiveRoleId = receiveRoleId;
        this.images = images;
    }

    public List<String> getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(List<String> receiveId) {
        this.receiveId = receiveId;
    }

    public List<String> getReceiveRoleId() {
        return receiveRoleId;
    }

    public void setReceiveRoleId(List<String> receiveRoleId) {
        this.receiveRoleId = receiveRoleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String  getImages() {
        return images;
    }
}
