package wgt.module.cn.com.wgt_sample.report;


import java.util.List;

import wgt.module.cn.com.wgt_sample.entity.NewReportEntity;
import wgt.module.cn.com.wgt_sample.entity.PersonEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportEntity;
import wgt.module.cn.com.wgt_sample.entity.ReportStateEntity;
import wgt.module.cn.com.wgt_sample.utils.BasePresenter;
import wgt.module.cn.com.wgt_sample.utils.BaseView;

/**
 * Created by skc on 2020/6/19.
 */
public interface ReportContract {

    interface addView extends BaseView<Presenter> {

        void setReportType(List<NewReportEntity> data);

        void setAddReport(boolean isSuccess);
    }

    interface messageView extends BaseView<Presenter> {

        void setReportMessageJS(boolean isSuccess);

        void setReportMessageBJ(boolean isSuccess);
        void setReportMessageZX(boolean isSuccess);
        void setReportMessageZB(boolean isSuccess);
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
    }
}
