// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class NewReportAdapter$ViewHolder_ViewBinding<T extends NewReportAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public NewReportAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.newreportCheckbox = Utils.findRequiredViewAsType(source, R.id.newreport_checkbox, "field 'newreportCheckbox'", CheckBox.class);
    target.newreportLayout = Utils.findRequiredViewAsType(source, R.id.newreport_layout, "field 'newreportLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.newreportCheckbox = null;
    target.newreportLayout = null;

    this.target = null;
  }
}
