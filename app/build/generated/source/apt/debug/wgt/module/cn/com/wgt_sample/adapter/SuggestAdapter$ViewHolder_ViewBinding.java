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

public class SuggestAdapter$ViewHolder_ViewBinding<T extends SuggestAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SuggestAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.suggestAdapterPerson = Utils.findRequiredViewAsType(source, R.id.suggest_adapter_person, "field 'suggestAdapterPerson'", TextView.class);
    target.suggestAdapterTime = Utils.findRequiredViewAsType(source, R.id.suggest_adapter_time, "field 'suggestAdapterTime'", TextView.class);
    target.suggestAdapterContent = Utils.findRequiredViewAsType(source, R.id.suggest_adapter_content, "field 'suggestAdapterContent'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.suggestAdapterPerson = null;
    target.suggestAdapterTime = null;
    target.suggestAdapterContent = null;

    this.target = null;
  }
}
