package com.mike.mareu.di;

import com.mike.mareu.repository.MeetingRepository;
import com.mike.mareu.service.FakeApiService;

// Dependency Injection
public class Injection {

    public static MeetingRepository createMeetingRepository() {
        return new MeetingRepository(new FakeApiService());
    }

}
