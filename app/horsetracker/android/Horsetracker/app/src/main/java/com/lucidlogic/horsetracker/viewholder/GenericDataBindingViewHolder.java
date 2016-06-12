package com.lucidlogic.horsetracker.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ian on 08/06/16.
 */
public class GenericDataBindingViewHolder extends RecyclerView.ViewHolder{

    private ViewDataBinding viewDataBinding;

    public GenericDataBindingViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
        this.viewDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

}
