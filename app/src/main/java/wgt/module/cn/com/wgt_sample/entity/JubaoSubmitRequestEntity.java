package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class JubaoSubmitRequestEntity {
    private String content;
    private List<String> receiveRoleId;


    public JubaoSubmitRequestEntity(String content, List<String> receiveRoleId) {
        this.content = content;
        this.receiveRoleId = receiveRoleId;
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


}
