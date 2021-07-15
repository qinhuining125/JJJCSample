// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.report;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.utils.MyDrawerLayout;

public class ReportActivity_ViewBinding<T extends ReportActivity> implements Unbinder {
  protected T target;

  private View view2131296531;

  private View view2131296549;

  private View view2131296541;

  private View view2131296535;

  private View view2131296537;

  private View view2131296546;

  private View view2131296532;

  private View view2131296551;

  private View view2131296555;

  private View view2131296542;

  private View view2131296548;

  private View view2131296538;

  private View view2131296547;

  private View view2131296558;

  private View view2131296561;

  @UiThread
  public ReportActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.reportAllreporttext = Utils.findRequiredViewAsType(source, R.id.report_allreporttext, "field 'reportAllreporttext'", TextView.class);
    target.reportAllreportline = Utils.findRequiredView(source, R.id.report_allreportline, "field 'reportAllreportline'");
    target.reportWaitreporttext = Utils.findRequiredViewAsType(source, R.id.report_waitreporttext, "field 'reportWaitreporttext'", TextView.class);
    target.reportWaitreportline = Utils.findRequiredView(source, R.id.report_waitreportline, "field 'reportWaitreportline'");
    target.reportWorkreporttext = Utils.findRequiredViewAsType(source, R.id.report_workreporttext, "field 'reportWorkreporttext'", TextView.class);
    target.reportWorkreportline = Utils.findRequiredView(source, R.id.report_workreportline, "field 'reportWorkreportline'");
    target.reportFinishreporttext = Utils.findRequiredViewAsType(source, R.id.report_finishreporttext, "field 'reportFinishreporttext'", TextView.class);
    target.reportFinishreportline = Utils.findRequiredView(source, R.id.report_finishreportline, "field 'reportFinishreportline'");
    target.reportZhixiaoreporttext = Utils.findRequiredViewAsType(source, R.id.report_zhixiaoreporttext, "field 'reportZhixiaoreporttext'", TextView.class);
    target.reportZhixiaoreportline = Utils.findRequiredView(source, R.id.report_zhixiaoreportline, "field 'reportZhixiaoreportline'");
    target.reportZhuanbanreporttext = Utils.findRequiredViewAsType(source, R.id.report_zhuanbanreporttext, "field 'reportZhuanbanreporttext'", TextView.class);
    target.reportZhuanbanreportline = Utils.findRequiredView(source, R.id.report_zhuanbanreportline, "field 'reportZhuanbanreportline'");
    target.reportList = Utils.findRequiredViewAsType(source, R.id.report_list, "field 'reportList'", SwipeRecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh_layout, "field 'refresh'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.report_add, "field 'reportAdd' and method 'onViewClicked'");
    target.reportAdd = Utils.castView(view, R.id.report_add, "field 'reportAdd'", ImageView.class);
    view2131296531 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.reportWaitred = Utils.findRequiredView(source, R.id.report_waitred, "field 'reportWaitred'");
    target.reportWorkred = Utils.findRequiredView(source, R.id.report_workred, "field 'reportWorkred'");
    target.reportDrawerlayout = Utils.findRequiredViewAsType(source, R.id.report_drawerlayout, "field 'reportDrawerlayout'", MyDrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.report_starttime, "field 'reportStarttime' and method 'onViewClicked'");
    target.reportStarttime = Utils.castView(view, R.id.report_starttime, "field 'reportStarttime'", TextView.class);
    view2131296549 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_endtime, "field 'reportEndtime' and method 'onViewClicked'");
    target.reportEndtime = Utils.castView(view, R.id.report_endtime, "field 'reportEndtime'", TextView.class);
    view2131296541 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_area, "field 'reportArea' and method 'onViewClicked'");
    target.reportArea = Utils.castView(view, R.id.report_area, "field 'reportArea'", TextView.class);
    view2131296535 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.reportArealayout = Utils.findRequiredViewAsType(source, R.id.report_arealayout, "field 'reportArealayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.report_back, "method 'onViewClicked'");
    view2131296537 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_menu, "method 'onViewClicked'");
    view2131296546 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_allreport, "method 'onViewClicked'");
    view2131296532 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_waitreport, "method 'onViewClicked'");
    view2131296551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_workreport, "method 'onViewClicked'");
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_finishreport, "method 'onViewClicked'");
    view2131296542 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_qx, "method 'onViewClicked'");
    view2131296548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_cz, "method 'onViewClicked'");
    view2131296538 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_qd, "method 'onViewClicked'");
    view2131296547 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_zhixiaoreport, "method 'onViewClicked'");
    view2131296558 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.report_zhuanbanreport, "method 'onViewClicked'");
    view2131296561 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.reportAllreporttext = null;
    target.reportAllreportline = null;
    target.reportWaitreporttext = null;
    target.reportWaitreportline = null;
    target.reportWorkreporttext = null;
    target.reportWorkreportline = null;
    target.reportFinishreporttext = null;
    target.reportFinishreportline = null;
    target.reportZhixiaoreporttext = null;
    target.reportZhixiaoreportline = null;
    target.reportZhuanbanreporttext = null;
    target.reportZhuanbanreportline = null;
    target.reportList = null;
    target.refresh = null;
    target.reportAdd = null;
    target.reportWaitred = null;
    target.reportWorkred = null;
    target.reportDrawerlayout = null;
    target.reportStarttime = null;
    target.reportEndtime = null;
    target.reportArea = null;
    target.reportArealayout = null;

    view2131296531.setOnClickListener(null);
    view2131296531 = null;
    view2131296549.setOnClickListener(null);
    view2131296549 = null;
    view2131296541.setOnClickListener(null);
    view2131296541 = null;
    view2131296535.setOnClickListener(null);
    view2131296535 = null;
    view2131296537.setOnClickListener(null);
    view2131296537 = null;
    view2131296546.setOnClickListener(null);
    view2131296546 = null;
    view2131296532.setOnClickListener(null);
    view2131296532 = null;
    view2131296551.setOnClickListener(null);
    view2131296551 = null;
    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296542.setOnClickListener(null);
    view2131296542 = null;
    view2131296548.setOnClickListener(null);
    view2131296548 = null;
    view2131296538.setOnClickListener(null);
    view2131296538 = null;
    view2131296547.setOnClickListener(null);
    view2131296547 = null;
    view2131296558.setOnClickListener(null);
    view2131296558 = null;
    view2131296561.setOnClickListener(null);
    view2131296561 = null;

    this.target = null;
  }
}
