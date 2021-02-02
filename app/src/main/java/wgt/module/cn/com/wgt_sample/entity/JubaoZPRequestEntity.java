package wgt.module.cn.com.wgt_sample.entity;

public class JubaoZPRequestEntity {
    private String id;
    private int roleId;

    public JubaoZPRequestEntity(String id, int roleId) {
        this.id = id;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
