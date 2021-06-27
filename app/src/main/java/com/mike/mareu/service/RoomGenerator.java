package com.mike.mareu.service;

import com.mike.mareu.model.TheRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// List of rooms
public abstract class RoomGenerator {

    static List<TheRoom> generateRooms() { return new ArrayList<>(FAKE_ROOMS); }

    public static List<TheRoom> FAKE_ROOMS = Arrays.asList(
        new TheRoom(1L, "Adama"),
        new TheRoom(2L, "Arche"),
        new TheRoom(3L, "Orion"),
        new TheRoom(4L, "Alpha"),
        new TheRoom(5L, "Angkor"),
        new TheRoom(6L, "Corail"),
        new TheRoom(7L, "Picchu"),
        new TheRoom(8L, "Muraille"),
        new TheRoom(9L, "Taj Mahal"),
        new TheRoom(10L, "Station")
    );
}
