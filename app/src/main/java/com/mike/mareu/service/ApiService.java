package com.mike.mareu.service;

import com.mike.mareu.model.Meeting;
import com.mike.mareu.model.TheRoom;

import java.util.List;

public interface ApiService {
    List<Meeting> getMeetingRooms();
    void deleteMeetingRoom(Meeting room);
    void createMeetingRoom(Meeting NewRoom);

    // List rooms off spinner
    List<TheRoom> getRooms();

}
