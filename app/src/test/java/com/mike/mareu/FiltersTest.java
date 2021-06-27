package com.mike.mareu;

import com.mike.mareu.di.Injection;
import com.mike.mareu.model.Meeting;
import com.mike.mareu.repository.MeetingRepository;
import com.mike.mareu.ui.meeting_room_list.ListMeetingRoomAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FiltersTest {
    private MeetingRepository meetingRepository;

    @Before
    public void setup() { meetingRepository = Injection.createMeetingRepository(); }

    @Test
    public void filterRoomWithSuccess() {
        List<Meeting> filterList = ListMeetingRoomAdapter.filterRoomMeetingList(meetingRepository.getMeeting(),"Alpha");
        assertEquals(1, filterList.size());
        filterList = ListMeetingRoomAdapter.filterRoomMeetingList(meetingRepository.getMeeting(),"FakeRoom");
        assertEquals(0, filterList.size());
    }

    @Test
    public void filterDateWithSuccess() {
        List<Meeting> filterDateList = ListMeetingRoomAdapter.filterRoomDateList(meetingRepository.getMeeting(), "22/09/2021");
        assertEquals(1, filterDateList.size());
        filterDateList = ListMeetingRoomAdapter.filterRoomDateList(meetingRepository.getMeeting(), "01/01/2021");
        assertEquals(0, filterDateList.size());
    }
}
