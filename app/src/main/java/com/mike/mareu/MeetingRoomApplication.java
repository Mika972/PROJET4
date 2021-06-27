package com.mike.mareu;

import android.app.Application;

import com.mike.mareu.di.Injection;
import com.mike.mareu.repository.MeetingRepository;

public class MeetingRoomApplication extends Application {

    private MeetingRepository meetingRepository;

    // ---

    public MeetingRepository getMeetingRepository() {
        if (meetingRepository == null) meetingRepository = Injection.createMeetingRepository();
        return meetingRepository;
    }
}
