package com.lucidlogic.horsetracker.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.adapter.MeetingAdapter;
import com.lucidlogic.horsetracker.databinding.FragmentRacecardBinding;
import com.lucidlogic.horsetracker.model.binding.Racecard;


public class RacecardFragment extends Fragment {

    public static final String ARG_RACECARD = "racecard";

    // TODO: Rename and change types of parameters
    private Racecard racecard;

    private OnFragmentInteractionListener mListener;

    public RacecardFragment() {
    }


    public static RacecardFragment newInstance(Racecard racecard) {
        RacecardFragment fragment = new RacecardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RACECARD, racecard);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            racecard = getArguments().getParcelable(ARG_RACECARD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRacecardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_racecard, container, false);
        View v = binding.getRoot();
//        binding.setRacecard(racecard);
        initMeetingsRecyclerView(v);
        return v;
    }

    public void initMeetingsRecyclerView(View v){
        RecyclerView rv = (RecyclerView)v.findViewById(R.id.meetings_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new MeetingAdapter(racecard.getMeetings()));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public Racecard getRacecard() {
        return racecard;
    }

    public void setRacecard(Racecard racecard) {
        this.racecard = racecard;
    }

}
