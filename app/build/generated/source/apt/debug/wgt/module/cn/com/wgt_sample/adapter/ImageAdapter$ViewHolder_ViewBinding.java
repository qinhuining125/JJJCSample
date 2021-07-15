// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class ImageAdapter$ViewHolder_ViewBinding<T extends ImageAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ImageAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.photoView = Utils.findRequiredViewAsType(source, R.id.photo_view_message, "field 'photoView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.photoView = null;

    this.target = null;
  }
}
