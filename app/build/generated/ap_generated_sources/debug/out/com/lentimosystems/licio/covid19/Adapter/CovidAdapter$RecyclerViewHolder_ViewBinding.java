// Generated code from Butter Knife. Do not modify!
package com.lentimosystems.licio.covid19.Adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lentimosystems.licio.covid19.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CovidAdapter$RecyclerViewHolder_ViewBinding implements Unbinder {
  private CovidAdapter.RecyclerViewHolder target;

  @UiThread
  public CovidAdapter$RecyclerViewHolder_ViewBinding(CovidAdapter.RecyclerViewHolder target,
      View source) {
    this.target = target;

    target.txtCountry = Utils.findRequiredViewAsType(source, R.id.txtCountry, "field 'txtCountry'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CovidAdapter.RecyclerViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtCountry = null;
  }
}
