package wgt.module.cn.com.wgt_sample.task;


import java.io.File;
import java.util.List;

import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskEntity;
import wgt.module.cn.com.wgt_sample.entity.TaskStateEntity;
import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/19.
 */
public interface TaskContract {

    /* 查看任务列表 */
    interface View extends BaseView<Presenter> {

        // 提交任务返回。
        void setTask(boolean isSuccess);

        // 任务受理返回。
        void setTaskSL(boolean isSuccess);

        // 任务指派返回。
        void setTaskZP(boolean isSuccess);

        // 任务办结返回。
        void setTaskBJ(boolean isSuccess);

        // 获取任务列表。
        void setTaskList(TaskEntity data);

        // 审批类型等待框。
        void spDialogDissmis();

        // 获取新建指派人员。
        void setPersonNEW(List<PersonEntity> dataList);

        // 获取第二次指派人员。
        void setPersonZP(List<PersonEntity> dataList, final String id, String text);

        void setPerson(List<PersonEntity> dataList);

        void setState(TaskStateEntity data);

        void setImageList(List<String> imageList);
    }

    /* 查看任务详细 */
    interface messageView extends BaseView<Presenter> {

        // 受理返回。
        void setTaskMessageSL(boolean isSuccess);

        // 指派返回。
        void setTaskMessageZP(boolean isSuccess);

        // 办结返回。
        void setTaskMessageBJ(boolean isSuccess);

        // 第二次指派获取人员。
        void setPersonMessageZP(List<PersonEntity> dataList, final String id, String text);
    }

    /*  presenter */
    interface Presenter extends BasePresenter {

        // 任务列表。
        void getTaskList(String jsonData);

        // 提交受理。
        void getTaskSL(String jsonData);

        // 指派。
        void getTaskZP(String jsonData);

        // 办结。
        void getTaskBJ(String jsonData);

        // 新增指派。
        void addTask(String jsonData);

        // 获取指派人员。
        void getPerson(int type, final String id, String text);

        void getPerson();

        // 详细页面受理。
        void getTaskMessageSL(String jsonData);

        // 指派。
        void getTaskMessageZP(String jsonData);

        // 办结。
        void getTaskMessageBJ(String jsonData);

        // 获取指派人员。
        void getMessagePerson(final String id, String text);

        void getState();

        //上传图片
        void uploadImages(List<File> images);
    }
}
