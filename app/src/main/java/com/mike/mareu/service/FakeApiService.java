package com.mike.mareu.service;

import com.mike.mareu.model.Meeting;
import com.mike.mareu.model.TheRoom;

import java.util.List;

import static com.mike.mareu.service.MeetingRoomGenerator.generateMeetingRooms;
import static com.mike.mareu.service.RoomGenerator.generateRooms;

public class FakeApiService implements ApiService {

    // List rooms
    private final List<TheRoom> the_rooms = generateRooms();

    // list of reserved meeting rooms
    private final List<Meeting> meeting_rooms = generateMeetingRooms();


    @Override
    public List<Meeting> getMeetingRooms() {
        return meeting_rooms;
    }

    @Override
    public void deleteMeetingRoom(Meeting room) {
        meeting_rooms.remove(room);
    }

    @Override
    public void createMeetingRoom(Meeting newRoom) {
        meeting_rooms.add(newRoom);
    }


    // List rooms
    @Override
    public List<TheRoom> getRooms() { return the_rooms; }

}