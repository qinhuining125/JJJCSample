// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.report;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class FullWGYReportTypeChoose_ViewBinding<T extends FullWGYReportTypeChoose> implements Unbinder {
  protected T target;

  private View view2131296596;

  private View view2131296524;

  @UiThread
  public FullWGYReportTypeChoose_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.asdf = Utils.findRequiredViewAsType(source, R.id.asdf, "field 'asdf'", ListView.class);
    view = Utils.findRequiredView(source, R.id.reporttypechoose_back, "field 'reporttypechooseBack' and method 'onViewClicked'");
    target.reporttypechooseBack = Utils.castView(view, R.id.reporttypechoose_back, "field 'reporttypechooseBack'", ImageView.class);
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

    target.asdf = null;
    target.reporttypechooseBack = null;

    view2131296596.setOnClickListener(null);
    view2131296596 = null;
    view2131296524.setOnClickListener(null);
    view2131296524 = null;

    this.target = null;
  }
}
