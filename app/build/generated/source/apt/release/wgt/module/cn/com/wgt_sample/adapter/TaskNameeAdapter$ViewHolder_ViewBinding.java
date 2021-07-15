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

public class TaskNameeAdapter$ViewHolder_ViewBinding<T extends TaskNameeAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TaskNameeAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.personname = Utils.findRequiredViewAsType(source, R.id.personname, "field 'personname'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.personname = null;

    this.target = null;
  }
}
