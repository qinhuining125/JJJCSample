package wgt.module.cn.com.wgt_sample.task;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import wgt.module.cn.com.wgt_sample.api.ApiInterface;
import wgt.module.cn.com.wgt_sample.app.BaseApplication;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.entity.UploadImageEntity;
import wgt.module.cn.com.wgt_sample.utils.AppUtils;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.utils.HttpResultList;

public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mView;
    private TaskContract.messageView mMessageView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;

    TaskPresenter(TaskContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
    }

    TaskPresenter(TaskContract.messageView mMessageView, Context context) {
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
    public void getTaskList(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.ERROR("获取指派任务失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("获取指派任务失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<TaskEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setTaskList(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTaskSL(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskSL(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.setTaskSL(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTaskZP(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskZP(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.setTaskZP(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTaskBJ(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskBJ(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.ERROR("任务办结失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("任务办结失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setTaskBJ(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void addTask(final String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .setTask(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mView.ERROR("指派任务失败，请确认网络环境后重试");
                        } else {
                            mView.ERROR("指派任务失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mView.setTask(httpResult.getResult());
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

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
                        e.printStackTrace();
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
    public void getTaskMessageSL(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskSL(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mMessageView.setTaskMessageSL(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTaskMessageZP(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskZP(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mMessageView.setTaskMessageZP(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTaskMessageBJ(String jsonData) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getTaskBJ(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData))
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
                            mMessageView.ERROR("任务办结失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("任务办结失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResult<Boolean> httpResult) {
                        if (httpResult.isSuccess()) {
                            mMessageView.setTaskMessageBJ(httpResult.getResult());
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

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
                .getTaskState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<TaskStateEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                       e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<TaskStateEntity> httpResult) {
                        if (httpResult.isSuccess()) {

                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("multipart/form-data");
    //多图片上传
    @Override
    public void uploadImages(List<File> fileList) {

        final MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i=0;
        for(File file:fileList){
            if(file.exists()){
//                Log.i("imageName:",file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                mbody.addFormDataPart("file1",file.getName(),RequestBody.create(MediaType.parse("image/*"),file));
                i++;
            }
        }
        List<MultipartBody.Part> parts=mbody.build().parts();
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .uploadImages(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("上传图片完毕-----","completed");
//                        Log.e("formadata",stringHttpResultList);
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("上传图片失败，请确认网络环境后重试");
                        } else {
                            Log.getStackTraceString(e);
                            mView.ERROR("上传图片失败");
                        }
                    }
                    @Override
                    public void onNext(HttpResultList<String> result) {
//                        Log.e("上传图片完毕-----",result.getResult().toString());
                        if (result.isSuccess()){
                            mView.setImageList(result.getResult());

                        }

                    }
                });
        mSubscriptions.add(subscription);

    }
}
