// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FlowAdapter$ViewHolder_ViewBinding<T extends FlowAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public FlowAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.taskMessageZp1time = Utils.findRequiredViewAsType(source, R.id.task_message_zp1time, "field 'taskMessageZp1time'", TextView.class);
    target.taskMessageZp1line = Utils.findRequiredView(source, R.id.task_message_zp1line, "field 'taskMessageZp1line'");
    target.taskMessageZp1person = Utils.findRequiredViewAsType(source, R.id.task_message_zp1person, "field 'taskMessageZp1person'", TextView.class);
    target.taskMessageSltime = Utils.findRequiredViewAsType(source, R.id.task_message_sltime, "field 'taskMessageSltime'", TextView.class);
    target.taskMessageSlline = Utils.findRequiredView(source, R.id.task_message_slline, "field 'taskMessageSlline'");
    target.taskMessageSlperson = Utils.findRequiredViewAsType(source, R.id.task_message_slperson, "field 'taskMessageSlperson'", TextView.class);
    target.taskMessageSllayout = Utils.findRequiredViewAsType(source, R.id.task_message_sllayout, "field 'taskMessageSllayout'", LinearLayout.class);
    target.taskMessageBjtime = Utils.findRequiredViewAsType(source, R.id.task_message_bjtime, "field 'taskMessageBjtime'", TextView.class);
    target.taskMessageBjperson = Utils.findRequiredViewAsType(source, R.id.task_message_bjperson, "field 'taskMessageBjperson'", TextView.class);
    target.taskMessageBjlayout = Utils.findRequiredViewAsType(source, R.id.task_message_bjlayout, "field 'taskMessageBjlayout'", LinearLayout.class);
    target.taskMessageBjline = Utils.findRequiredView(source, R.id.task_message_bjline, "field 'taskMessageBjline'");
    target.taskMessageBjline2 = Utils.findRequiredView(source, R.id.task_message_bjline2, "field 'taskMessageBjline2'");
    target.layoutZp = Utils.findRequiredViewAsType(source, R.id.layout_zp, "field 'layoutZp'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.taskMessageZp1time = null;
    target.taskMessageZp1line = null;
    target.taskMessageZp1person = null;
    target.taskMessageSltime = null;
    target.taskMessageSlline = null;
    target.taskMessageSlperson = null;
    target.taskMessageSllayout = null;
    target.taskMessageBjtime = null;
    target.taskMessageBjperson = null;
    target.taskMessageBjlayout = null;
    target.taskMessageBjline = null;
    target.taskMessageBjline2 = null;
    target.layoutZp = null;

    this.target = null;
  }
}
