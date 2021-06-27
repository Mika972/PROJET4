package com.mike.mareu.repository;

import com.mike.mareu.model.Meeting;
import com.mike.mareu.model.TheRoom;
import com.mike.mareu.service.ApiService;
import java.util.Calendar;

import java.util.List;

public class MeetingRepository {

    private final ApiService apiService;

    public MeetingRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<Meeting> getMeeting() {
        return apiService.getMeetingRooms();
    }

    public void createMeeting(Meeting  meeting) {
        apiService.createMeetingRoom(meeting);
    }

    public void deleteMeeting(Meeting  meeting) {
        apiService.deleteMeetingRoom(meeting);
    }


    // List rooms spinner
        public List<TheRoom> getRooms() {
        return apiService.getRooms();
    }


    // 08:30 --> 08 30
    private int[] splitHour(String hour) {
        String[] parts = hour.split(":");
        int[] result = new int[2];
        result[0] = Integer.parseInt(parts[0]);
        result[1] = Integer.parseInt(parts[1]);
        return result;
    }
    // Check is room available
    public boolean isRoomAvailable(String room, String date, String hour) {
        for (Meeting meeting : getMeeting()) {
            if (meeting.getRoom().equals(room) && meeting.getDate().equals(date)) {
                int[] parseMeeting = splitHour(meeting.getHour());
                int[] parseHour = splitHour(hour);

                Calendar dateReunion = Calendar.getInstance();
                dateReunion.set(Calendar.HOUR, parseMeeting[0]);
                dateReunion.set(Calendar.MINUTE, parseMeeting[1]);
                dateReunion.add(Calendar.MINUTE, 45);

                Calendar dateNouvelleReunion = Calendar.getInstance();
                dateNouvelleReunion.set(Calendar.HOUR, parseHour[0]);
                dateNouvelleReunion.set(Calendar.MINUTE, parseHour[1]);

                if(dateNouvelleReunion.before(dateReunion)) {
                    return false;
                }

            }
        }
        return true;
    }

}