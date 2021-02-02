package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class SuggestSubmitRequestEntity {
    private String content;

    public SuggestSubmitRequestEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
