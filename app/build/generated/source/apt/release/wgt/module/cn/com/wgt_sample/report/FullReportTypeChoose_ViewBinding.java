// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.report;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FullReportTypeChoose_ViewBinding<T extends FullReportTypeChoose> implements Unbinder {
  protected T target;

  private View view2131296596;

  private View view2131296524;

  @UiThread
  public FullReportTypeChoose_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.reportType1 = Utils.findRequiredViewAsType(source, R.id.reportType1, "field 'reportType1'", CheckBox.class);
    target.reportType2 = Utils.findRequiredViewAsType(source, R.id.reportType2, "field 'reportType2'", CheckBox.class);
    target.reportType3 = Utils.findRequiredViewAsType(source, R.id.reportType3, "field 'reportType3'", CheckBox.class);
    target.reportType4 = Utils.findRequiredViewAsType(source, R.id.reportType4, "field 'reportType4'", CheckBox.class);
    target.reportType5 = Utils.findRequiredViewAsType(source, R.id.reportType5, "field 'reportType5'", CheckBox.class);
    target.reportType6 = Utils.findRequiredViewAsType(source, R.id.reportType6, "field 'reportType6'", CheckBox.class);
    target.reportType7 = Utils.findRequiredViewAsType(source, R.id.reportType7, "field 'reportType7'", CheckBox.class);
    target.reportType8 = Utils.findRequiredViewAsType(source, R.id.reportType8, "field 'reportType8'", CheckBox.class);
    target.reportType9 = Utils.findRequiredViewAsType(source, R.id.reportType9, "field 'reportType9'", CheckBox.class);
    target.reportType10 = Utils.findRequiredViewAsType(source, R.id.reportType10, "field 'reportType10'", CheckBox.class);
    target.reportType11 = Utils.findRequiredViewAsType(source, R.id.reportType11, "field 'reportType11'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.reporttypechoose_back, "method 'onViewClicked'");
    view2131296596 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reportType_ok, "method 'onViewClicked'");
    view2131296524 = view;
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

    target.reportType1 = null;
    target.reportType2 = null;
    target.reportType3 = null;
    target.reportType4 = null;
    target.reportType5 = null;
    target.reportType6 = null;
    target.reportType7 = null;
    target.reportType8 = null;
    target.reportType9 = null;
    target.reportType10 = null;
    target.reportType11 = null;

    view2131296596.setOnClickListener(null);
    view2131296596 = null;
    view2131296524.setOnClickListener(null);
    view2131296524 = null;

    this.target = null;
  }
}
