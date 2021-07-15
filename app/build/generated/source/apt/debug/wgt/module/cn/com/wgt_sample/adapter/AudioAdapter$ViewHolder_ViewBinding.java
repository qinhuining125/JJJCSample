// Generated code from Butter Knife. Do not modify!
package wgt.module.cn.com.wgt_sample.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import java.lang.IllegalStateException;
import java.lang.Override;
import wgt.module.cn.com.wgt_sample.R;

public class AudioAdapter$ViewHolder_ViewBinding<T extends AudioAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AudioAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.photoView = Utils.findRequiredViewAsType(source, R.id.audio_view, "field 'photoView'", ImageView.class);
    target.audioClose = Utils.findRequiredViewAsType(source, R.id.audio_view_close, "field 'audioClose'", ImageView.class);
    target.audioStart = Utils.findRequiredViewAsType(source, R.id.audio_view_start, "field 'audioStart'", ImageView.class);
    target.audioPause = Utils.findRequiredViewAsType(source, R.id.audio_view_pause, "field 'audioPause'", ImageView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.tv_main_desc = Utils.findRequiredViewAsType(source, R.id.tv_main_desc, "field 'tv_main_desc'", TextView.class);
    target.plVideoTextureView = Utils.findRequiredViewAsType(source, R.id.plvAudio, "field 'plVideoTextureView'", PLVideoTextureView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.photoView = null;
    target.audioClose = null;
    target.audioStart = null;
    target.audioPause = null;
    target.progressBar = null;
    target.tv_main_desc = null;
    target.plVideoTextureView = null;

    this.target = null;
  }
}
