// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131296438;

  private View view2131296439;

  private View view2131296440;

  private View view2131296442;

  private View view2131296437;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mainLayout = Utils.findRequiredViewAsType(source, R.id.main_layout, "field 'mainLayout'", LinearLayout.class);
    target.report = Utils.findRequiredViewAsType(source, R.id.reportimg, "field 'report'", ImageView.class);
    target.task = Utils.findRequiredViewAsType(source, R.id.taskimg, "field 'task'", ImageView.class);
    target.xuncha = Utils.findRequiredViewAsType(source, R.id.xunchawork, "field 'xuncha'", ImageView.class);
    target.mainMkLayout = Utils.findRequiredViewAsType(source, R.id.main_mk_layout, "field 'mainMkLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.main_report, "field 'layoutReport' and method 'onViewClicked'");
    target.layoutReport = Utils.castView(view, R.id.main_report, "field 'layoutReport'", LinearLayout.class);
    view2131296438 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_suggest, "field 'layoutuggest' and method 'onViewClicked'");
    target.layoutuggest = Utils.castView(view, R.id.main_suggest, "field 'layoutuggest'", LinearLayout.class);
    view2131296439 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_task, "field 'layoutTask' and method 'onViewClicked'");
    target.layoutTask = Utils.castView(view, R.id.main_task, "field 'layoutTask'", LinearLayout.class);
    view2131296440 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.viewOnePlaceHolder = Utils.findRequiredView(source, R.id.view_one, "field 'viewOnePlaceHolder'");
    target.viewTwoPlaceHolder = Utils.findRequiredView(source, R.id.view_two, "field 'viewTwoPlaceHolder'");
    view = Utils.findRequiredView(source, R.id.main_xunchawork, "field 'layoutXunchaWork' and method 'onViewClicked'");
    target.layoutXunchaWork = Utils.castView(view, R.id.main_xunchawork, "field 'layoutXunchaWork'", LinearLayout.class);
    view2131296442 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_person, "method 'onViewClicked'");
    view2131296437 = view;
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

    target.mainLayout = null;
    target.report = null;
    target.task = null;
    target.xuncha = null;
    target.mainMkLayout = null;
    target.layoutReport = null;
    target.layoutuggest = null;
    target.layoutTask = null;
    target.viewOnePlaceHolder = null;
    target.viewTwoPlaceHolder = null;
    target.layoutXunchaWork = null;

    view2131296438.setOnClickListener(null);
    view2131296438 = null;
    view2131296439.setOnClickListener(null);
    view2131296439 = null;
    view2131296440.setOnClickListener(null);
    view2131296440 = null;
    view2131296442.setOnClickListener(null);
    view2131296442 = null;
    view2131296437.setOnClickListener(null);
    view2131296437 = null;

    this.target = null;
  }
}
