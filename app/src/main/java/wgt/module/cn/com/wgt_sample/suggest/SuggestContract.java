package wgt.module.cn.com.wgt_sample.suggest;


import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;
import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/19.
 */
public interface SuggestContract {

    interface View extends BaseView<Presenter> {

        void setSuggest(boolean isSuccess);

        void setSuggestList(SuggestEntity data);

        void ERRORLIST(String e);

        void addDialogDissmis();
    }

    interface Presenter extends BasePresenter {

        void addSuggest(String jsonData);

        void getSuggestList(String jsonData);
    }
}
