package com.lucidlogic.horsetracker.view.impl;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.handler.EntityClickHandler;
import com.lucidlogic.horsetracker.model.Entity;
import com.lucidlogic.horsetracker.presenter.EntityPresenter;
import com.lucidlogic.horsetracker.presenter.impl.EntityPresenterImpl;
import com.lucidlogic.horsetracker.view.EntityView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntityFragment extends Fragment implements EntityView {

    public static final String ENTITY_OBJECT_ID = "ENTITY_OBJECT_ID";
    private String entityObjectId;
    private EntityPresenter entityPresenter;
    private Entity entity;

    public EntityFragment() {
        entityPresenter = new EntityPresenterImpl();
        entityPresenter.setView(this);
        this.entity = new Entity();
    }

    public static EntityFragment newInstance(String entityObjectId) {
        EntityFragment fragment = new EntityFragment();
        Bundle args = new Bundle();
        args.putString(ENTITY_OBJECT_ID, entityObjectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entityObjectId = getArguments().getString(ENTITY_OBJECT_ID);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_entity, container, false);
        viewDataBinding.setVariable(BR.entity,  entity);
        viewDataBinding.setVariable(BR.handler,  new EntityClickHandler());
        entityPresenter.updateView(this.entityObjectId);
        return viewDataBinding.getRoot();
    }

    @Override
    public void updateView(Entity e) {
        this.entity = e;
        this.entity.notifyChange();
    }
}
