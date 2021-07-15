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

public class ReportAdapter$ViewHolder_ViewBinding<T extends ReportAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ReportAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.reportAdapterPerson = Utils.findRequiredViewAsType(source, R.id.report_adapter_person, "field 'reportAdapterPerson'", TextView.class);
    target.reportAdapterType = Utils.findRequiredViewAsType(source, R.id.report_adapter_type, "field 'reportAdapterType'", TextView.class);
    target.reportAdapterText = Utils.findRequiredViewAsType(source, R.id.report_adapter_text, "field 'reportAdapterText'", TextView.class);
    target.reportAdapterTime = Utils.findRequiredViewAsType(source, R.id.report_adapter_time, "field 'reportAdapterTime'", TextView.class);
    target.reportAdapterJs = Utils.findRequiredViewAsType(source, R.id.report_adapter_js, "field 'reportAdapterJs'", TextView.class);
    target.reportAdapterFinish = Utils.findRequiredViewAsType(source, R.id.report_adapter_finish, "field 'reportAdapterFinish'", TextView.class);
    target.reportAdapterZhixiao = Utils.findRequiredViewAsType(source, R.id.task_adapter_zx, "field 'reportAdapterZhixiao'", TextView.class);
    target.reportAdapterZhuanban = Utils.findRequiredViewAsType(source, R.id.task_adapter_zb, "field 'reportAdapterZhuanban'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.reportAdapterPerson = null;
    target.reportAdapterType = null;
    target.reportAdapterText = null;
    target.reportAdapterTime = null;
    target.reportAdapterJs = null;
    target.reportAdapterFinish = null;
    target.reportAdapterZhixiao = null;
    target.reportAdapterZhuanban = null;

    this.target = null;
  }
}
