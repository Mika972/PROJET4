package com.mike.mareu.ui.meeting_room_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.mareu.R;
import com.mike.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingRoomAdapter extends RecyclerView.Adapter<MeetingRoomViewHolder> implements Filterable {

    public interface Listener {
        void onClickDeleteButton(int position);
    }

    // COMMUNICATION
    private final Listener callback;
    // DATA
    public List<Meeting> meetingRoomsList;
    // FILTER
    private List<Meeting> meetingRoomsListFilter;

    public ListMeetingRoomAdapter(List<Meeting> meetingRoomsList, Listener callback) {
        this.meetingRoomsList = meetingRoomsList;
        this.meetingRoomsListFilter = meetingRoomsList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MeetingRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_meeting_room, parent, false);
        return new MeetingRoomViewHolder(itemView);
    }

    // UPDATE VIEW HOLDER WITH A MEETING ROOM
    @Override
    public void onBindViewHolder(@NonNull MeetingRoomViewHolder holder, int position) {
        holder.updateWithMeetingRoom(this.meetingRoomsList.get(position), this.callback);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return meetingRoomsList.size();
    }

    public Meeting getMeeting(int position) {
        return this.meetingRoomsList.get(position);
    }

    /* ===  RETURN FILTER === */
    /* For tests objects filters */

    public static List<Meeting> filterRoomMeetingList(List<Meeting> notFilterList, String constraint) {
        List<Meeting> filterList = new ArrayList<>();
        for (Meeting meet : notFilterList) {
            if (meet.getRoom().toLowerCase().contains(constraint.toLowerCase())) {
                filterList.add(meet);
            }
        }
        return filterList;
    }
    /* "" */
    public static List<Meeting> filterRoomDateList(List<Meeting> notFilterDateList, String dConstraint) {
        List<Meeting> dateFilterList = new ArrayList<>();
        for (Meeting dateMeet: notFilterDateList) {
            if (dateMeet.getDate().toLowerCase().contains(dConstraint.toLowerCase())) {
                dateFilterList.add(dateMeet);
            }
        }
        return dateFilterList;
    }

    // Provides the filter by meeting room name
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public FilterResults performFiltering(CharSequence constraint) {
                String mConstraint = constraint.toString();
                if (mConstraint.isEmpty()) {
                    meetingRoomsList = meetingRoomsListFilter;
                } else {
                    meetingRoomsList = filterRoomMeetingList(meetingRoomsListFilter, mConstraint);
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = meetingRoomsList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                meetingRoomsList = (ArrayList<Meeting>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // Provides the filter by date
    public Filter GetDateFilter() {
        return new Filter() {
            @Override
            public FilterResults performFiltering(CharSequence constraint) {
                String dConstraint = constraint.toString();
                if (dConstraint.isEmpty()) {
                    meetingRoomsList = meetingRoomsListFilter;
                } else {
                    meetingRoomsList = filterRoomDateList(meetingRoomsListFilter, dConstraint);
                }
                FilterResults dateFilterResults = new FilterResults();
                dateFilterResults.values = meetingRoomsList;
                return dateFilterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                meetingRoomsList = (ArrayList<Meeting>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}