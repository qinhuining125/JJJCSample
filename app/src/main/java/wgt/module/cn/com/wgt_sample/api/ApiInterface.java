package wgt.module.cn.com.wgt_sample.api;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import wgt.module.cn.com.wgt_sample.entity.JubaoStateEntity;
import wgt.module.cn.com.wgt_sample.entity.JubaopersonEntity;
import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportpersonEntity;
import wgt.module.cn.com.wgt_sample.entity.SuggestEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.entity.User;
import wgt.module.cn.com.wgt_sample.entity.VersionEntity;
import wgt.module.cn.com.wgt_sample.entity.XunchaProcessEntity;
import wgt.module.cn.com.wgt_sample.utils.HttpResult;
import wgt.module.cn.com.wgt_sample.utils.HttpResultList;

public interface ApiInterface {

    // 登录。
    @Headers({"Content-Type: application/json"})
    @POST("api/login.json")
    Observable<HttpResult<User>> login(@Body RequestBody requestBody);

    // 上报建议 。
    @Headers({"Content-Type: application/json"})
    @POST("api/advice/saveData.json")
    Observable<HttpResult<Boolean>> setSuggest(@Body RequestBody requestBody);

    // 获取廉政建议 。
    @Headers({"Content-Type: application/json"})
    @POST("api/advice/pagelist.json")
    Observable<HttpResult<SuggestEntity>> getSuggestList(@Body RequestBody requestBody);

    /**
     * 问题上报。
     */
    // 上报类型 。
    @Headers({"Content-Type: application/json"})
    @GET("api/reportType/list.json")
    Observable<HttpResultList<NewReportEntity>> getReportType(@Query("roldId")  String roldId, @Query("userId")  String userId);
    // 上报 。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/saveData.json")
    Observable<HttpResult<Boolean>> setReport(@Body RequestBody requestBody);

    // 上报分页列表 。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/pagelist.json")
    Observable<HttpResult<ReportEntity>> getReportList(@Body RequestBody requestBody);

    // 受理 。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/accept.json")
    Observable<HttpResult<Boolean>> getReportWork(@Body RequestBody requestBody);

    // 办结 。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/close.json")
    Observable<HttpResult<Boolean>> getReportFinish(@Body RequestBody requestBody);
    // 知晓。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/knowTask.json")
    Observable<HttpResult<Boolean>> getReportZhixiao(@Body RequestBody requestBody);
    // 转办 。
    @Headers({"Content-Type: application/json"})
    @POST("api/clueReport/turnToOtherTask.json")
    Observable<HttpResult<Boolean>> getReportZhuanban(@Body RequestBody requestBody);

    // 状态 。
    @GET("api/clueReport/state.json")
    Observable<HttpResult<ReportStateEntity>> getReportState();

    // 获取问题上报可以转办的人员角色()
    @GET("api/clueReport/reportZBRoleList.json")
    Observable<HttpResultList<ReportpersonEntity>> getReportPerson(@Query("type")  int type);

    /**
     * 指派任务。
     */

     // 提交任务 。
    @Headers({"Content-Type: application/json"})
    @POST("api/taskInfo/saveData.json")
    Observable<HttpResult<Boolean>> setTask(@Body RequestBody requestBody);

    // 指派分页列表 。
    @Headers({"Content-Type: application/json"})
    @POST("api/taskInfo/pagelist.json")
    Observable<HttpResult<TaskEntity>> getTaskList(@Body RequestBody requestBody);

    // 受理 。
    @Headers({"Content-Type: application/json"})
    @POST("api/taskInfo/accept.json")
    Observable<HttpResult<Boolean>> getTaskSL(@Body RequestBody requestBody);

    // 指派 。
    @Headers({"Content-Type: application/json"})
    @POST("api/taskInfo/assign.json")
    Observable<HttpResult<Boolean>> getTaskZP(@Body RequestBody requestBody);

    // 办结 。
    @Headers({"Content-Type: application/json"})
    @POST("api/taskInfo/close.json")
    Observable<HttpResult<Boolean>> getTaskBJ(@Body RequestBody requestBody);

    // 人员查询 。
    @Headers({"Content-Type: application/json"})
    @GET("api/user/list.json")
    Observable<HttpResultList<PersonEntity>> getPerson();

    // 状态 。
    @GET("api/taskInfo/state.json")
    Observable<HttpResult<TaskStateEntity>> getTaskState();

    //多图片上传
//    @Multipart
//    @Headers({"Content-Type:application/json"})
//    @POST("web/DemoImageController/uploadMult")
//    Observable<HttpResult<Boolean>> uploadImages(@Body RequestBody requestBody);

    //图片
    @Multipart
    @POST("web/DemoImageController/appUploadMult")
    Observable<HttpResultList<String>> uploadImages(@Part List<MultipartBody.Part> partLis);

    //语音
    @Multipart
    @POST("web/DemoAudioController/appUploadMult")
    Observable<HttpResultList<String>> uploadAudios(@Part List<MultipartBody.Part> partLis);

    //视频
    @Multipart
    @POST("web/DemoVideoController/appUploadMult")
    Observable<HttpResultList<String>> uploadVideos(@Part List<MultipartBody.Part> partLis);


    // 获取版本 。
    @GET("download/version.json")
    Observable<HttpResult<VersionEntity>> getVS();

    // 修改密码。
    @Headers({"Content-Type: application/json"})
    @POST("api/user/changpas.json")
    Observable<HttpResult<Boolean>> changepassword(@Body RequestBody requestBody);



    /**
     * 巡查宣传
     */
    // 指派分页列表 。
    @Headers({"Content-Type: application/json"})
    @POST("api/InspectionPublicity/pagelist.json")
    Observable<HttpResult<TaskEntity>> getXunchaList(@Body RequestBody requestBody);


    // 受理 。
    @Headers({"Content-Type: application/json"})
    @POST("api/InspectionPublicity/accept.json")
    Observable<HttpResult<Boolean>> getXunchaSL(@Body RequestBody requestBody);


    // 指派 。
    @Headers({"Content-Type: application/json"})
    @POST("api/InspectionPublicity/assign.json")
    Observable<HttpResult<Boolean>> getXunchaZP(@Body RequestBody requestBody);

    // 状态 。
    @GET("api/InspectionPublicity/state.json")
    Observable<HttpResult<TaskStateEntity>> getXunchaState();



//    // 办结 。
//    @Headers({"Content-Type: application/json"})
//    @POST("api/taskInfo/close.json")
//    Observable<HttpResult<Boolean>> getXunchaBJ(@Body RequestBody requestBody);

//    // 人员查询 。
//    @Headers({"Content-Type: application/json"})
//    @GET("api/user/list.json")
//    Observable<HttpResultList<PersonEntity>> getPerson();

    /**
     * 百姓举报
     */
    // 指派分页列表 。
    @Headers({"Content-Type: application/json"})
    @POST("api/revealInfo/pagelist.json")
    Observable<HttpResult<TaskEntity>> getJubaoList(@Body RequestBody requestBody);

    // 指派 。
    @Headers({"Content-Type: application/json"})
    @POST("api/revealInfo/savetree.json")
    Observable<HttpResult<Boolean>> getJubaoZP(@Body RequestBody requestBody);

    // 受理=已知晓 。
    @Headers({"Content-Type: application/json"})
    @GET("api/revealInfo/knowTask.json")
    Observable<HttpResult<Boolean>> getJubaoSL(@Query("uid") String uid);

    // 状态 。
    @GET("api/revealInfo/state.json")
    Observable<HttpResult<JubaoStateEntity>> getJubaoState();

    // 获取巡察组列表 。
    @GET("api/revealInfo/xclist.json")
    Observable<HttpResultList<JubaopersonEntity>> getJubaoPerson();

    //获取是否有进行中得巡查
    @GET("api/patrolInfo/isHaveProcess.json")
    Observable<HttpResult<XunchaProcessEntity>> getisHaveProcess();

    @Headers({"Content-Type: application/json"})
    @POST("api/revealInfo/saveData.json")
    Observable<HttpResult<Boolean>> submitJubao(@Body RequestBody requestBody);
}


