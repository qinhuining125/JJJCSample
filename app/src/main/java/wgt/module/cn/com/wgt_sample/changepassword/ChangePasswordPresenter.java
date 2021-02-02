package wgt.module.cn.com.wgt_sample.changepassword;

import android.content.Context;

import java.net.SocketTimeoutException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import wgt.module.cn.com.wgt_sample.api.ApiInterface;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;

/**
 * Created by skc on 2020/6/16.
 */
public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {

    private ChangePasswordContract.View mView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    public ChangePasswordPresenter(ChangePasswordContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        //清除所有订阅
        mSubscriptions.clear();
    }

    @Override
    public void changePassword(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .changepassword(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("修改失败，请确认网络环境后重试。");
                        } else {
                            mView.ERROR("修改密码失败。");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.success(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
