package com.mike.mareu.ui.meeting_room_list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.mareu.R;
import com.mike.mareu.model.Meeting;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    /* Annotate fields with @BindView */
    @BindView(R.id.item_date)
    TextView theDate;
    @BindView(R.id.item_meeting_hour)
    TextView theHour;
    @BindView(R.id.item_meetingRoom_name)
    TextView roomName;
    @BindView(R.id.item_subject_meeting)
    TextView themeNameOfMeeting;
    @BindView(R.id.item_list_participant)
    TextView participantName;
    @BindView(R.id.item_list_meeting_room_delete_button)
    ImageButton deleteItemButton;

    /* Weak reference objects, which do not prevent their referents from being made finalizable,
    *  finalized, and then reclaimed */
    private WeakReference<ListMeetingRoomAdapter.Listener> callbackWeakRef;

    public MeetingRoomViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithMeetingRoom(Meeting meeting, ListMeetingRoomAdapter.Listener callback) {
        this.theDate.setText(meeting.getDate());
        this.theHour.setText(meeting.getHour());
        this.roomName.setText(meeting.getRoom());
        this.themeNameOfMeeting.setText(meeting.getName());
        this.participantName.setText(meeting.getParticipant());
        this.deleteItemButton.setOnClickListener(this);

        this.callbackWeakRef = new WeakReference<ListMeetingRoomAdapter.Listener>(callback);
    }

    @Override
    public void onClick(View view) {
        ListMeetingRoomAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickDeleteButton(getAbsoluteAdapterPosition());
    }
}