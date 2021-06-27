package com.mike.mareu.service;

import com.mike.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// List of meeting rooms
public abstract class MeetingRoomGenerator {

    static List<Meeting> generateMeetingRooms() { return new ArrayList<>(FAKE_MEETING_ROOMS); }

    public static List<Meeting> FAKE_MEETING_ROOMS = Arrays.asList(
        new Meeting(1L, "08:00", "Adama", "23/07/2021","Origine", "Joe_l-indien@roman.us"),
        new Meeting(2L, "09:00", "Arche", "26/07/2021","Le début", "Huck@roman.us"),
        new Meeting(3L, "10:45", "Orion", "06/09/2021","Le voyage", "Tom_Sawyer@roman.us"),
        new Meeting(4L, "11:00", "Alpha","22/09/2021" ,"Préparations", "thomas.p@esa.eu")
    );
}
