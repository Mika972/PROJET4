package com.mike.mareu.ui.meeting_room_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.SearchView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mike.mareu.R;
import com.mike.mareu.base.BaseActivity;
import com.mike.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ListMeetingRoomAdapter.Listener {
    /* Annotate fields with @BindView */
    @BindView(R.id.recyclerview_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_list_meeting_room_fab)
    FloatingActionButton fab;

    private List<Meeting> meetingRoomsList;
    private ListMeetingRoomAdapter adapter;


    // Time picker variables
    private int fDate, fMonth, fYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        meetingRoomsList = new ArrayList<>();
        configureRecyclerView();
        openCreateActivity();
        iniList();
    }

    private void configureRecyclerView() {
        adapter = new ListMeetingRoomAdapter(meetingRoomsList, this);
        RecyclerView.LayoutManager layoutManagerRoomMeeting = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManagerRoomMeeting);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    // Refresh list meeting room
    private void iniList() {
        meetingRoomsList.clear();
        meetingRoomsList.addAll(getMeetingRepository().getMeeting());
        adapter.notifyDataSetChanged();
    }

    // the activity saves its states and restores them
    @Override
    protected void onResume() {
        super.onResume();
        iniList();
    }

   /* === ACTION BUTTONS === */
    // Delete Meeting room item
    @Override
    public void onClickDeleteButton(int position) {
        Meeting meeting = adapter.getMeeting(position);
        getMeetingRepository().deleteMeeting(meeting);
        iniList();
    }

    // Open new activity creation meeting room
    public void openCreateActivity() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddMeetingRoomActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    /* === FILTERS MANAGEMENT === */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_filter, menu);
        // Filter by meeting room's name
        MenuItem item = menu.findItem(R.id.search_bar);

        SearchView searchView = (SearchView)item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Filter by meeting date through the date picker
        MenuItem dateItem = menu.findItem(R.id.item_date_filter);
        dateItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                final Calendar cal = Calendar.getInstance();
                fDate   = cal.get(Calendar.DATE);
                fMonth  = cal.get(Calendar.MONTH);
                fYear   = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String theDateString = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                                adapter.GetDateFilter().filter(theDateString);
                            }
                        }, fYear, fMonth, fDate);
                datePickerDialog.show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}