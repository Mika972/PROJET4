package com.mike.mareu;

import com.mike.mareu.di.Injection;
import com.mike.mareu.model.Meeting;
import com.mike.mareu.model.TheRoom;
import com.mike.mareu.repository.MeetingRepository;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.mike.mareu.service.MeetingRoomGenerator.FAKE_MEETING_ROOMS;
import static com.mike.mareu.service.RoomGenerator.FAKE_ROOMS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MeetingRepositoryTest {
    private MeetingRepository meetingRepository;

    // Creation of a dummy meeting room for testing the creation meeting room
    private Meeting newMeetingRoom = new Meeting(5L, "18h15", "Control Room", "24/06/2021", "Command", "Oneil@cpt.nv");

    @Before
    public void setup() { meetingRepository = Injection.createMeetingRepository(); }

    // est the return of the spinner rooms
    @Test
    public void getRoomsWithSuccess() {
        List<TheRoom> roomSpinner = meetingRepository.getRooms();
        List<TheRoom> roomsExpected = FAKE_ROOMS;
        assertThat(roomSpinner, containsInAnyOrder(roomsExpected.toArray()));
    }

    // Test the Return of reserved rooms
    @Test
    public void getMeetingRoomsWithSuccess() {
        List<Meeting> meetingActual = meetingRepository.getMeeting();
        List<Meeting> expectedMeetingRooms = FAKE_MEETING_ROOMS;
        assertThat(meetingActual, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetingRooms.toArray()));
    }

    // Test deleting a meeting room reservation
    @Test
    public void deleteMeetingsRoomsWithSuccess() {
        Meeting meetingRoomToDelete = meetingRepository.getMeeting().get(0);
        meetingRepository.deleteMeeting(meetingRoomToDelete);
        assertFalse(meetingRepository.getMeeting().contains(meetingRoomToDelete));
    }

    // Test the creation of a meeting room
    @Test
    public void createMeetingRoomWithSuccess() {
        meetingRepository.createMeeting(newMeetingRoom);
        assertTrue(meetingRepository.getMeeting().contains(newMeetingRoom));
    }
}