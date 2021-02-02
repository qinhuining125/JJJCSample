package wgt.module.cn.com.wgt_sample.report;

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
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.utils.HttpResultList;

public class ReportPresenter implements ReportContract.Presenter {

    private ReportContract.View mView;
    private ReportContract.addView mAddView;
    private ReportContract.messageView mMessageView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    ReportPresenter(ReportContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
    }

    ReportPresenter(ReportContract.addView mAddView, Context context) {
        this.mAddView = mAddView;
        this.context = context;
        mAddView.setPresenter(this);
    }

    ReportPresenter(ReportContract.messageView mMessageView, Context context) {
        this.mMessageView = mMessageView;
        this.context = context;
        mMessageView.setPresenter(this);
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
    public void getAddReport(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .setReport(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mAddView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mAddView.ERROR("提交失败，请确认网络环境后重试");
                        } else {
                            mAddView.ERROR("提交失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mAddView.setAddReport(httpResult.getResult());
                        } else {
                            mAddView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportList(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<ReportEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("获取问题上报失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("获取问题上报失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<ReportEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setReportList(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportJS(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportWork(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.spDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("问题接受失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("问题接受失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setReportJS(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportZB(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportZhuanban(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.spDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("问题转办失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("问题转办失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setReportZB(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportZX(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportZhixiao(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.spDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("问题知晓失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("问题知晓失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setReportZX(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
    @Override
    public void getReportBJ(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportFinish(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mView.spDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("问题办结失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("问题办结失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setReportBJ(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportMessageJS(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportWork(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("问题接受失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("问题接受失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setReportMessageJS(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportMessageBJ(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportFinish(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("问题办结失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("问题办结失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setReportMessageBJ(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportMessageZB(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportZhuanban(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("问题转办失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("问题转办失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setReportMessageZB(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportMessageZX(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportZhixiao(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("问题知晓失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("问题知晓失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setReportMessageZX(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getReportType(String roldId) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportType(roldId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<NewReportEntity>>() {
                    @Override
                    public void onCompleted() {
                        mAddView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mAddView.ERROR("获取上报类型失败，请确认网络环境后重试");
                        } else {
                            mAddView.ERROR("获取上报类型失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResultList<NewReportEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mAddView.setReportType(httpResult.getResult());
                        } else {
                            mAddView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getState() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<ReportStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<ReportStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setState(httpResult.getResult());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getPerson() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getPerson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<PersonEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("查询乡镇人员失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("查询乡镇人员失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResultList<PersonEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setPerson(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
