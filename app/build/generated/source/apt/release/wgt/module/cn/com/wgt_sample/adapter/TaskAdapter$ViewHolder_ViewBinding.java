// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class TaskAdapter$ViewHolder_ViewBinding<T extends TaskAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TaskAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.taskAdapterPerson = Utils.findRequiredViewAsType(source, R.id.task_adapter_person, "field 'taskAdapterPerson'", TextView.class);
    target.taskAdapterType = Utils.findRequiredViewAsType(source, R.id.task_adapter_type, "field 'taskAdapterType'", TextView.class);
    target.taskAdapterText = Utils.findRequiredViewAsType(source, R.id.task_adapter_text, "field 'taskAdapterText'", TextView.class);
    target.taskAdapterTime = Utils.findRequiredViewAsType(source, R.id.task_adapter_time, "field 'taskAdapterTime'", TextView.class);
    target.taskAdapterSl = Utils.findRequiredViewAsType(source, R.id.task_adapter_sl, "field 'taskAdapterSl'", TextView.class);
    target.taskAdapterZp = Utils.findRequiredViewAsType(source, R.id.task_adapter_zp, "field 'taskAdapterZp'", TextView.class);
    target.taskAdapterFinish = Utils.findRequiredViewAsType(source, R.id.task_adapter_finish, "field 'taskAdapterFinish'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.taskAdapterPerson = null;
    target.taskAdapterType = null;
    target.taskAdapterText = null;
    target.taskAdapterTime = null;
    target.taskAdapterSl = null;
    target.taskAdapterZp = null;
    target.taskAdapterFinish = null;

    this.target = null;
  }
}
