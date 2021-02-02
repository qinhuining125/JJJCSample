package wgt.module.cn.com.wgt_sample.entity;

import java.io.Serializable;

/**
 * Created by skc on 2019/7/8.
 */
public class VersionEntity implements Serializable {

    private String version;
    private String versionName;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
