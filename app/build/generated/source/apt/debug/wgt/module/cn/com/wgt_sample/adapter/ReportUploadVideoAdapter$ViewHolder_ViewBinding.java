// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class ReportUploadVideoAdapter$ViewHolder_ViewBinding<T extends ReportUploadVideoAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ReportUploadVideoAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.photoView = Utils.findRequiredViewAsType(source, R.id.upload_video_view, "field 'photoView'", ImageView.class);
    target.photoClose = Utils.findRequiredViewAsType(source, R.id.video_view_close, "field 'photoClose'", ImageView.class);
    target.videoStart = Utils.findRequiredViewAsType(source, R.id.video_view_start, "field 'videoStart'", ImageView.class);
    target.videoPause = Utils.findRequiredViewAsType(source, R.id.video_view_pause, "field 'videoPause'", ImageView.class);
    target.plVideoTextureView = Utils.findRequiredViewAsType(source, R.id.plvVideo, "field 'plVideoTextureView'", PLVideoTextureView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.photoView = null;
    target.photoClose = null;
    target.videoStart = null;
    target.videoPause = null;
    target.plVideoTextureView = null;

    this.target = null;
  }
}
