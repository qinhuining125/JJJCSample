// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.jubao;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class JubaoBaixingActivity_ViewBinding<T extends JubaoBaixingActivity> implements Unbinder {
  protected T target;

  private View view2131296411;

  @UiThread
  public JubaoBaixingActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.webView = Utils.findRequiredViewAsType(source, R.id.web_view, "field 'webView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.jubao_baixing_back, "field 'btnBack' and method 'onViewClicked'");
    target.btnBack = Utils.castView(view, R.id.jubao_baixing_back, "field 'btnBack'", ImageView.class);
    view2131296411 = view;
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

    target.webView = null;
    target.btnBack = null;

    view2131296411.setOnClickListener(null);
    view2131296411 = null;

    this.target = null;
  }
}
