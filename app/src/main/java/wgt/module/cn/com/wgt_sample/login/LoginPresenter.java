package wgt.module.cn.com.wgt_sample.login;

import android.content.Context;
import android.text.TextUtils;

import java.net.ConnectException;
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
import wgt.module.cn.com.wgt_sample.entity.User;
import wgt.module.cn.com.wgt_sample.entity.VersionEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    LoginPresenter(LoginContract.View mView, Context context) {
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
    public void login(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .login(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<User>>() {
                    @Override
                    public void onCompleted() {
                        mView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                            mView.ERROR("登录失败，请确认网络环境后重试。");
                        } else {
                            mView.ERROR("登录失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<User> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.loginSuccess(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

}
