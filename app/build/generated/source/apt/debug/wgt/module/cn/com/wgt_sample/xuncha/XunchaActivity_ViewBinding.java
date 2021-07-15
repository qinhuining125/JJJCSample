// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.xuncha;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;
import wgt.module.cn.com.wgt_sample.utils.MyDrawerLayout;

public class XunchaActivity_ViewBinding<T extends XunchaActivity> implements Unbinder {
  protected T target;

  private View view2131296679;

  private View view2131296675;

  private View view2131296710;

  private View view2131296714;

  private View view2131296708;

  private View view2131296682;

  private View view2131296678;

  private View view2131296790;

  private View view2131296687;

  private View view2131296707;

  private View view2131296680;

  private View view2131296706;

  @UiThread
  public XunchaActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.taskAlltasktext = Utils.findRequiredViewAsType(source, R.id.task_alltasktext, "field 'taskAlltasktext'", TextView.class);
    target.taskAlltaskline = Utils.findRequiredView(source, R.id.task_alltaskline, "field 'taskAlltaskline'");
    target.taskWaittasktext = Utils.findRequiredViewAsType(source, R.id.task_waittasktext, "field 'taskWaittasktext'", TextView.class);
    target.taskWaittaskline = Utils.findRequiredView(source, R.id.task_waittaskline, "field 'taskWaittaskline'");
    target.taskWorktasktext = Utils.findRequiredViewAsType(source, R.id.task_worktasktext, "field 'taskWorktasktext'", TextView.class);
    target.taskWorktaskline = Utils.findRequiredView(source, R.id.task_worktaskline, "field 'taskWorktaskline'");
    target.taskList = Utils.findRequiredViewAsType(source, R.id.task_list, "field 'taskList'", SwipeRecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh_layout, "field 'refresh'", SwipeRefreshLayout.class);
    target.taskWaitred = Utils.findRequiredView(source, R.id.task_waitred, "field 'taskWaitred'");
    target.taskWorkred = Utils.findRequiredView(source, R.id.task_workred, "field 'taskWorkred'");
    view = Utils.findRequiredView(source, R.id.task_back, "field 'taskBack' and method 'onViewClicked'");
    target.taskBack = Utils.castView(view, R.id.task_back, "field 'taskBack'", ImageView.class);
    view2131296679 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_alltask, "field 'taskAlltask' and method 'onViewClicked'");
    target.taskAlltask = Utils.castView(view, R.id.task_alltask, "field 'taskAlltask'", RelativeLayout.class);
    view2131296675 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_waittask, "field 'taskWaittask' and method 'onViewClicked'");
    target.taskWaittask = Utils.castView(view, R.id.task_waittask, "field 'taskWaittask'", RelativeLayout.class);
    view2131296710 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_worktask, "field 'taskWorktask' and method 'onViewClicked'");
    target.taskWorktask = Utils.castView(view, R.id.task_worktask, "field 'taskWorktask'", RelativeLayout.class);
    view2131296714 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_starttime, "field 'taskStarttime' and method 'onViewClicked'");
    target.taskStarttime = Utils.castView(view, R.id.task_starttime, "field 'taskStarttime'", TextView.class);
    view2131296708 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_endtime, "field 'taskEndtime' and method 'onViewClicked'");
    target.taskEndtime = Utils.castView(view, R.id.task_endtime, "field 'taskEndtime'", TextView.class);
    view2131296682 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_area, "field 'taskArea' and method 'onViewClicked'");
    target.taskArea = Utils.castView(view, R.id.task_area, "field 'taskArea'", TextView.class);
    view2131296678 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.yask_arealayout, "field 'taskArealayout' and method 'onViewClicked'");
    target.taskArealayout = Utils.castView(view, R.id.yask_arealayout, "field 'taskArealayout'", LinearLayout.class);
    view2131296790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.taskDrawerlayout = Utils.findRequiredViewAsType(source, R.id.task_drawerlayout, "field 'taskDrawerlayout'", MyDrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.task_menu, "method 'onViewClicked'");
    view2131296687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_qx, "method 'onViewClicked'");
    view2131296707 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_cz, "method 'onViewClicked'");
    view2131296680 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.task_qd, "method 'onViewClicked'");
    view2131296706 = view;
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

    target.taskAlltasktext = null;
    target.taskAlltaskline = null;
    target.taskWaittasktext = null;
    target.taskWaittaskline = null;
    target.taskWorktasktext = null;
    target.taskWorktaskline = null;
    target.taskList = null;
    target.refresh = null;
    target.taskWaitred = null;
    target.taskWorkred = null;
    target.taskBack = null;
    target.taskAlltask = null;
    target.taskWaittask = null;
    target.taskWorktask = null;
    target.taskStarttime = null;
    target.taskEndtime = null;
    target.taskArea = null;
    target.taskArealayout = null;
    target.taskDrawerlayout = null;

    view2131296679.setOnClickListener(null);
    view2131296679 = null;
    view2131296675.setOnClickListener(null);
    view2131296675 = null;
    view2131296710.setOnClickListener(null);
    view2131296710 = null;
    view2131296714.setOnClickListener(null);
    view2131296714 = null;
    view2131296708.setOnClickListener(null);
    view2131296708 = null;
    view2131296682.setOnClickListener(null);
    view2131296682 = null;
    view2131296678.setOnClickListener(null);
    view2131296678 = null;
    view2131296790.setOnClickListener(null);
    view2131296790 = null;
    view2131296687.setOnClickListener(null);
    view2131296687 = null;
    view2131296707.setOnClickListener(null);
    view2131296707 = null;
    view2131296680.setOnClickListener(null);
    view2131296680 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;

    this.target = null;
  }
}
