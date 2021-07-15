package wgt.module.cn.com.wgt_sample.report;


import java.io.File;
import java.util.List;

import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportpersonEntity;
import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/19.
 */
public interface ReportContract {

    interface addView extends BaseView<Presenter> {

        void setReportType(List<NewReportEntity> data);

        void setAddReport(boolean isSuccess);

        //图片
        void setUploadImageListFromServer(List<String> imageList);

        //语音
        void setUploadAudioListFromServer(List<String> audioList);

        //视频
        void setUploadVideoListFromServer(List<String> videoList);

    }

    interface messageView extends BaseView<Presenter> {

        void setReportMessageJS(boolean isSuccess);

        void setReportMessageBJ(boolean isSuccess);
        void setReportMessageZX(boolean isSuccess);
        void setReportMessageZB(boolean isSuccess);
        // 指派获取人员。
        void setPersonMessageZB(List<ReportpersonEntity> dataList, final String id, String text);

    }


    interface View extends BaseView<Presenter> {

        void setReportList(ReportEntity data);

        void setReportJS(boolean isSuccess);

        void setReportBJ(boolean isSuccess);
        void setReportZB(boolean isSuccess);
        void setReportZX(boolean isSuccess);

        void spDialogDissmis();

        void setState(ReportStateEntity data);

        void setPerson(List<PersonEntity> dataList);

        // 获取转办（指派）人员。
        void setPersonZB(List<ReportpersonEntity> dataList, final String id, String text);

        //图片
        void setImageList(List<String> imageList);

        //语音
        void setAudioList(List<String> audioList);

        //视频
        void setVideoList(List<String> videoList);

    }

    interface Presenter extends BasePresenter {

        void getAddReport(String jsonData);

        void getReportList(String jsonData);

        void getReportJS(String jsonData);

        void getReportBJ(String jsonData);
        void getReportZX(String jsonData);
        void getReportZB(String jsonData);

        void getReportMessageJS(String jsonData);

        void getReportMessageBJ(String jsonData);
        void getReportMessageZB(String jsonData);
        void getReportMessageZX(String jsonData);

        void getReportType(String roldId);

        void getState();

        void getPerson();

        // 获取转办（指派）人员。
        void getMessagePerson(final String id, String text);

        // 获取指派人员。
        void getReportPerson(int type, final String id, String text);

        //提交图片
        void uploadImages(List<File> images);

        //提交语音
        void uploadAudios(List<File> audios);

        //提交视频
        void uploadVideos(List<File> videos);

    }
}
