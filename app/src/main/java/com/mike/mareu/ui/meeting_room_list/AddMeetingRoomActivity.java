package com.mike.mareu.ui.meeting_room_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mike.mareu.R;
import com.mike.mareu.base.BaseActivity;
import com.mike.mareu.model.Meeting;
import com.mike.mareu.model.TheRoom;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingRoomActivity extends BaseActivity {
    /* Annotate fields with @BindView and a view ID for Butter Knife */
    @BindView(R.id.spinner_rooms)
    Spinner spinner_Rooms;
    @BindView(R.id.hour_Lyt)
    EditText hourInput;
    @BindView(R.id.name_meeting_Lyt)
    TextInputLayout meetingNameInput;
    @BindView(R.id.participant_Lyt)
    TextInputLayout participantInput;
    @BindView(R.id.booking_button)
    MaterialButton createButton;
    @BindView(R.id.date_Lyt)
    EditText dateTxt;

    private int mDate, mMonth, mYear;
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting_room);
        /* He finds and automatically cast the corresponding view in our layout. */
        ButterKnife.bind(this);

        /* Spinner room list */
        List<TheRoom> roomsList = (List<TheRoom>) getMeetingRepository().getRooms();

        ArrayAdapter<TheRoom> adapter = new ArrayAdapter<TheRoom>(this,
                android.R.layout.simple_spinner_item,
                (List<TheRoom>) roomsList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner_Rooms.setAdapter(adapter);
        init();
    }

    // Refresh and fill in all the fields before activating the submit button of the form
    private void init() {
        meetingNameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                createButton.setEnabled(s.length() > 0);
            }
        });
    }

    // Select hour with time picker
    private void maMeetingTimePicker() {
        final Calendar pickTime = Calendar.getInstance();
        mHour   = pickTime.get(Calendar.HOUR_OF_DAY);
        mMinute = pickTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingRoomActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
                String hourString = String.format("%02d:%02d", hourOfDay, minuteOfHour);
                hourInput.setText(hourString);
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    // Select date with date picker
    public void maMeetingDatePicker() {
        final Calendar cal = Calendar.getInstance();
        mDate   = cal.get(Calendar.DATE);
        mMonth  = cal.get(Calendar.MONTH);
        mYear   = cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingRoomActivity.this,
                android.R.style.Theme_DeviceDefault_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dateString = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                        dateTxt.setText(dateString);
                    }
                }, mYear, mMonth, mDate);
        datePickerDialog.show();
    }

    // Add new meeting room & Check is room available
    public void addMeetingRoom() {
        if (!getMeetingRepository().isRoomAvailable(spinner_Rooms.getSelectedItem().toString(), dateTxt.getText().toString(),
                hourInput.getText().toString()))
            {
                Toast.makeText(AddMeetingRoomActivity.this, "Cette salle est prise", Toast.LENGTH_SHORT).show();
            }
        else  {
                Meeting addMeeting = new Meeting(
                    System.currentTimeMillis(),
                    hourInput.getText().toString(),
                    spinner_Rooms.getSelectedItem().toString(),
                    dateTxt.getText().toString(),
                    meetingNameInput.getEditText().getText().toString(),
                    participantInput.getEditText().getText().toString()
                );
                getMeetingRepository().createMeeting(addMeeting);
                finish();
        }

    }

    // Picker selection buttons and add new meeting room button
    @OnClick({R.id.booking_button, R.id.date_Lyt, R.id.hour_Lyt})
    void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.date_Lyt:
                maMeetingDatePicker();
                break;
            case R.id.booking_button:
                addMeetingRoom();
                break;
            case R.id.hour_Lyt:
                maMeetingTimePicker();
            default:
                break;
        }
    }

}