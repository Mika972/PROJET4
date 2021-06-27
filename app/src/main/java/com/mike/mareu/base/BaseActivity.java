package com.mike.mareu.base;

import androidx.appcompat.app.AppCompatActivity;

import com.mike.mareu.MeetingRoomApplication;
import com.mike.mareu.repository.MeetingRepository;

public abstract class BaseActivity extends AppCompatActivity {

    public MeetingRepository getMeetingRepository() {
        return ((MeetingRoomApplication) getApplication()).getMeetingRepository();
    }
}
