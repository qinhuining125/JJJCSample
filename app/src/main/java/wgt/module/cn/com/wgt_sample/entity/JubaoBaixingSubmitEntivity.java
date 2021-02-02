package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

public class JubaoBaixingSubmitEntivity {
    private String userId;
    private String userName;
    private String contact;
    private String revealTarget;
    private String content;
    private String type;
    private String townCode;
    private String villageCode;
    private int roleId;
    private String inspectionId;
    private List<String> imageArray;

    public JubaoBaixingSubmitEntivity() {
    }

    @Override
    public String toString() {
        return "JubaoBaixingSubmitEntivity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", contact='" + contact + '\'' +
                ", revealTarget='" + revealTarget + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", townCode='" + townCode + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", roleId='" + roleId + '\'' +
                ", inspectionId='" + inspectionId + '\'' +
                ", imageArray=" + imageArray +
                '}';
    }

    public JubaoBaixingSubmitEntivity(String userId, String userName, String contact, String revealTarget, String content, String type,
                                      String townCode, String villageCode, int roleId, String inspectionId, List<String> imageArray) {
        this.userId = userId;
        this.userName = userName;
        this.contact = contact;
        this.revealTarget = revealTarget;
        this.content = content;
        this.type = type;
        this.townCode = townCode;
        this.villageCode = villageCode;
        this.roleId = roleId;
        this.inspectionId = inspectionId;
        this.imageArray = imageArray;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRevealTarget() {
        return revealTarget;
    }

    public void setRevealTarget(String revealTarget) {
        this.revealTarget = revealTarget;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public List<String> getImageArray() {
        return imageArray;
    }

    public void setImageArray(List<String> imageArray) {
        this.imageArray = imageArray;
    }
}
