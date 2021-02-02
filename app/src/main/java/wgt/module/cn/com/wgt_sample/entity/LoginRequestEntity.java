package wgt.module.cn.com.wgt_sample.entity;

/**
 * Created by skc on 2020/6/19.
 */
public class LoginRequestEntity {

    private String account;
    private String password;

    public LoginRequestEntity(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getUsername() {
        return account;
    }

    public void setUsername(String username) {
        this.account = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
