package wgt.module.cn.com.wgt_sample.utils;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void dataDialogDissmis();

    void ERROR(String e);
}
