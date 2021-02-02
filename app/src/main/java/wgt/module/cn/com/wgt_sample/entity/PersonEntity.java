package wgt.module.cn.com.wgt_sample.entity;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by skc on 2020/6/22.
 */
public class PersonEntity implements IPickerViewData{

    private String areaCode;
    private String areaName;
    private String userId;
    private String roleId;
    private List<PersonSonEntity> childs;

    public PersonEntity(String areaName) {
        this.areaName = areaName;
    }

    public PersonEntity(String areaCode, String areaName, String userId, String roleId) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PersonSonEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<PersonSonEntity> childs) {
        this.childs = childs;
    }

    @Override
    public String getPickerViewText() {
        return areaName;
    }

    public static class PersonSonEntity implements IPickerViewData{
        private String areaCode;
        private String areaName;
        private String userId;
        private String roleId;

        @Override
        public String toString() {
            return "PersonSonEntity{" +
                    "areaCode='" + areaCode + '\'' +
                    ", areaName='" + areaName + '\'' +
                    ", userId='" + userId + '\'' +
                    ", roleId='" + roleId + '\'' +
                    '}';
        }

        public PersonSonEntity() {

        }

        public PersonSonEntity(String areaName, String areaCode) {
            this.areaCode = areaCode;
            this.areaName = areaName;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String getPickerViewText() {
            return areaName;
        }
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", childs=" + childs +
                '}';
    }
}
