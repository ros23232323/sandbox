package com.lucidlogic.horsetracker.adapter;

import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.model.binding.Meeting;

import java.util.List;

/**
 * Created by ian on 08/06/16.
 */
public class MeetingAdapter extends GenericDatabindingAdapter<Meeting> {
    public MeetingAdapter(List<Meeting> meetings) {
        super(meetings, BR.meeting, R.layout.meeting_card);
    }
}

/*public class MeetingAdapter extends RecyclerView.Adapter<GenericDataBindingViewHolder> {


    List<Meeting> meetings;

    public MeetingAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public GenericDataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.meeting_card,parent, false);
        return new GenericDataBindingViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(GenericDataBindingViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.meeting,  meeting);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        notifyDataSetChanged();
    }

}
*/