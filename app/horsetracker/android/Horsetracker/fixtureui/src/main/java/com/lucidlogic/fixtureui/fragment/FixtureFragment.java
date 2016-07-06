package com.lucidlogic.fixtureui.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lucidlogic.fixtureui.BeanTransformers;
import com.lucidlogic.fixtureui.CustomExpandableListAdapter;
import com.lucidlogic.fixtureui.R;
import com.lucidlogic.fixtureui.binding.Racecard;
import com.lucidlogic.fixtureui.parse.RacecardParse;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.parse.ParseObservable;
import rx.schedulers.Schedulers;

public class FixtureFragment extends Fragment {

    public static final String ARG_RACECARD = "racecard";

    CustomExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Racecard racecard;

    public static FixtureFragment newInstance(Racecard racecard) {
        FixtureFragment fragment = new FixtureFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RACECARD, racecard);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            racecard = getArguments().getParcelable(ARG_RACECARD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // setting list adapter
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fixture, container, false);
        View v = binding.getRoot();

        expListView = (ExpandableListView) v.findViewById(com.lucidlogic.fixtureui.R.id.lvExp);
        listAdapter = new CustomExpandableListAdapter(getContext(), racecard);
        expListView.setAdapter (listAdapter);
        return v;
    }
}