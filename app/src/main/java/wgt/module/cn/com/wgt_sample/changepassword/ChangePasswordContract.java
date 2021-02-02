package wgt.module.cn.com.wgt_sample.changepassword;

import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/16.
 */
public interface ChangePasswordContract {

    interface View extends BaseView<Presenter> {

        void dataDialogDissmis();

        void ERROR(String e);

        void success(boolean isSuccess);
    }

    interface Presenter extends BasePresenter {

        void changePassword(String mn);
    }
}
