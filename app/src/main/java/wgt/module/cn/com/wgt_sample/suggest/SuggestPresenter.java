package wgt.module.cn.com.wgt_sample.suggest;

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
import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;

public class SuggestPresenter implements SuggestContract.Presenter {

    private SuggestContract.View mView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    SuggestPresenter(SuggestContract.View mView, Context context) {
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
    public void addSuggest(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .setSuggest(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.addDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("提交失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("提交失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setSuggest(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getSuggestList(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getSuggestList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<SuggestEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERRORLIST("获取廉政建议失败，请确认网络环境后重试");
                        } else {
                            mView.ERRORLIST("获取廉政建议失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<SuggestEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setSuggestList(httpResult.getResult());
                        } else {
                            mView.ERRORLIST(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
