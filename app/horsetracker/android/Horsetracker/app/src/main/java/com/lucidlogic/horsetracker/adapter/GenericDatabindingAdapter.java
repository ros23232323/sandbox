package com.lucidlogic.horsetracker.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lucidlogic.horsetracker.viewholder.GenericDataBindingViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 11/06/16.
 */
public class GenericDatabindingAdapter<T> extends RecyclerView.Adapter<GenericDataBindingViewHolder> {

    private final int layoutCardId;
    private final int bindingVariable;
    private List<T> objs = new ArrayList<>();

    public GenericDatabindingAdapter(int bindingVariable, int layoutCardId) {
        this.bindingVariable = bindingVariable;
        this.layoutCardId = layoutCardId;
    }

    public GenericDatabindingAdapter(List<T> objs, int bindingVariable, int layoutCardId) {
        this.objs = objs;
        this.bindingVariable = bindingVariable;
        this.layoutCardId = layoutCardId;
    }

    @Override
    public GenericDataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutCardId,parent, false);
        return new GenericDataBindingViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(GenericDataBindingViewHolder holder, int position) {
        T obj = objs.get(position);
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(this.bindingVariable,  obj);
    }

    @Override
    public int getItemCount() {

        return objs.size();
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
        notifyDataSetChanged();
    }

}

