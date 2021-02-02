package wgt.module.cn.com.wgt_sample.xuncha;

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
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.utils.HttpResultList;

public class XunchaPresenter implements XunchaContract.Presenter {

    private XunchaContract.View mView;
    private XunchaContract.messageView mMessageView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    XunchaPresenter(XunchaContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
    }

    XunchaPresenter(XunchaContract.messageView mMessageView, Context context) {
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
    public void getXunchaList(String jsonData) {
            Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                    .create(ApiInterface.class)
                    .getXunchaList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HttpResult<TaskEntity>>() {
                        @Override
                        public void onCompleted() {
                            mView.dataDialogDissmis();
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof SocketTimeoutException) {
                                mView.ERROR("获取巡查任务失败，请确认网络环境后重试");
                            } else {
                                mView.ERROR("获取巡查任务失败");
                            }
                        }

                        @Override
                        public void onNext(HttpResult<TaskEntity> httpResult) {
                            if (httpResult.isSuccess()) {
                                mView.setXunchaList(httpResult.getResult());
                            } else {
                                mView.ERROR(httpResult.getMessage());
                            }
                        }
                    });
            mSubscriptions.add(subscription);
        }

    @Override
    public void getXunchaSL(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaSL(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.ERROR("任务受理失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("任务受理失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setXunchaSL(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getXunchaZP(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaZP(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.ERROR("任务指派失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("任务指派失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setXunchaZP(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

//    @Override
//    public void getXunchaBJ(String jsonData) {
//        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
//                .create(ApiInterface.class)
//                .getXunchaBJ(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<Boolean>>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.spDialogDissmis();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof SocketTimeoutException) {
//                            mView.ERROR("任务办结失败，请确认网络环境后重试");
//                        } else {
//                            mView.ERROR("任务办结失败");
//                        }
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<Boolean> httpResult) {
//                        if (httpResult.isSuccess()) {
//                            mView.setXunchaBJ(httpResult.getResult());
//                        } else {
//                            mView.ERROR(httpResult.getMessage());
//                        }
//                    }
//                });
//        mSubscriptions.add(subscription);
//    }

//    @Override
//    public void addXuncha(String jsonData) {
//        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
//                .create(ApiInterface.class)
//                .setTask(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<Boolean>>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.spDialogDissmis();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof SocketTimeoutException) {
//                            mView.ERROR("指派任务失败，请确认网络环境后重试");
//                        } else {
//                            mView.ERROR("指派任务失败");
//                        }
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<Boolean> httpResult) {
//                        if (httpResult.isSuccess()) {
//                            mView.setXuncha(httpResult.getResult());
//                        } else {
//                            mView.ERROR(httpResult.getMessage());
//                        }
//                    }
//                });
//        mSubscriptions.add(subscription);
//    }

    @Override
    public void getPerson(final int type, final String id, final String text) {
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
                            mView.ERROR("获取指派人员失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("获取指派人员失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResultList<PersonEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            if (type == 0) {
                                mView.setPersonNEW(httpResult.getResult());
                            } else if (type == 1) {
                                mView.setPersonZP(httpResult.getResult(), id, text);
                            }
                        } else {
                            mView.ERROR(httpResult.getMessage());
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

    /**
     * 查看详情中使用审批接口
     */

    @Override
    public void getXunchaMessageSL(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaSL(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mMessageView.ERROR("任务受理失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("任务受理失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setXunchaMessageSL(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getXunchaMessageZP(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaZP(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mMessageView.ERROR("任务指派失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("任务指派失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setXunchaMessageZP(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

//    @Override
//    public void getXunchaMessageBJ(String jsonData) {
//        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
//                .create(ApiInterface.class)
//                .getTaskBJ(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<Boolean>>() {
//                    @Override
//                    public void onCompleted() {
//                        mMessageView.dataDialogDissmis();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof SocketTimeoutException) {
//                            mMessageView.ERROR("任务办结失败，请确认网络环境后重试");
//                        } else {
//                            mMessageView.ERROR("任务办结失败");
//                        }
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<Boolean> httpResult) {
//                        if (httpResult.isSuccess()) {
//                            mMessageView.setXunchaMessageBJ(httpResult.getResult());
//                        } else {
//                            mMessageView.ERROR(httpResult.getMessage());
//                        }
//                    }
//                });
//        mSubscriptions.add(subscription);
//    }

    @Override
    public void getMessagePerson(final String id, final String text) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getPerson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<PersonEntity>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("获取指派人员失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("获取指派人员失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResultList<PersonEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setPersonMessageZP(httpResult.getResult(), id, text);
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getState() {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getXunchaState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<TaskStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HttpResult<TaskStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setState(httpResult.getResult());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
