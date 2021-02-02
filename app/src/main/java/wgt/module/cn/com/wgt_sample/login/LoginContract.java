package wgt.module.cn.com.wgt_sample.login;


import wgt.module.cn.com.wgt_sample.entity.User;
import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/19.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void loginSuccess(User result);

    }

    interface Presenter extends BasePresenter {

        void login(String jsonData);

    }
}
