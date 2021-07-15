package wgt.module.cn.com.wgt_sample.report;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.List;

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
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportpersonEntity;
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
                .getReportType(roldId, BaseApplication.userid)
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
    public void getReportPerson(final int type, final String id, final String text) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportPerson(BaseApplication.prm == 1003 ? 0 : 1)// 如果是1003，type=0,否则为1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<ReportpersonEntity>>() {
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
                    public void onNext(HttpResultList<ReportpersonEntity> httpResult) {
                        if (httpResult.isSuccess()) {
                            if (type == 0) {//当前登录系统的用户是1003，乡镇纪委管理员，乡镇纪委管理员管理员进行转办的话，只能选择到村干部和站所
                                mView.setPersonZB(httpResult.getResult(), id, text);
                            } else {//当前登录系统的用户是1012，村干部，村干部进行转办的话，只能选择到乡镇纪委管理员和站所
                                mView.setPersonZB(httpResult.getResult(), id, text);
                            }
                        } else {
                            mView.ERROR(httpResult.getMessage());
                        }
                    }
                });
        mSubscriptions.add(subscription);

    }

    //图片
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
                            mAddView.setUploadImageListFromServer(result.getResult());

                        }

                    }
                });
        mSubscriptions.add(subscription);
    }

    //语音
    @Override
    public void uploadAudios(List<File> fileList) {
        final MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i=0;
        for(File file:fileList){
            if(file.exists()){
//                Log.i("imageName:",file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                mbody.addFormDataPart("fileAudio1",file.getName(),RequestBody.create(MediaType.parse("audio/*"),file));
                i++;
            }
        }
        List<MultipartBody.Part> parts=mbody.build().parts();
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .uploadAudios(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<String>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("上传语音失败，请确认网络环境后重试");
                        } else {
                            Log.getStackTraceString(e);
                            mView.ERROR("上传语音失败");
                        }
                    }
                    @Override
                    public void onNext(HttpResultList<String> result) {
                        if (result.isSuccess()){
                            mAddView.setUploadAudioListFromServer(result.getResult());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    //视频
    @Override
    public void uploadVideos(List<File> fileList) {
        final MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i=0;
        for(File file:fileList){
            if(file.exists()){
//                Log.i("imageName:",file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                mbody.addFormDataPart("fileVideo1",file.getName(),RequestBody.create(MediaType.parse("video/*"),file));
                i++;
            }
        }
        List<MultipartBody.Part> parts=mbody.build().parts();
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .uploadVideos(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<String>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            mView.ERROR("上传视频失败，请确认网络环境后重试");
                        } else {
                            Log.getStackTraceString(e);
                            mView.ERROR("上传视频失败");
                        }
                    }
                    @Override
                    public void onNext(HttpResultList<String> result) {
                        if (result.isSuccess()){
                            mAddView.setUploadVideoListFromServer(result.getResult());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }



    @Override
    public void getMessagePerson(final String id, final String text) {
        Subscription subscription = AppUtils.provideRetrofit(BaseApplication.baseURL)
                .create(ApiInterface.class)
                .getReportPerson(BaseApplication.prm == 1003 ? 0 : 1)// 如果是1003，type=0,否则为1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultList<ReportpersonEntity>>() {
                    @Override
                    public void onCompleted() {
                        mMessageView.dataDialogDissmis();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("获取指派人员失败","e----");
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            mMessageView.ERROR("获取指派人员失败，请确认网络环境后重试");
                        } else {
                            mMessageView.ERROR("获取指派人员失败");
                        }
                    }
                    @Override
                    public void onNext(HttpResultList<ReportpersonEntity> httpResult) {
                        Log.e("获取指派人员成功",httpResult.getResult().toString());
                        if (httpResult.isSuccess()) {
                            mMessageView.setPersonMessageZB(httpResult.getResult(), id, text);
                        } else {
                            mMessageView.ERROR(httpResult.getMessage());
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
