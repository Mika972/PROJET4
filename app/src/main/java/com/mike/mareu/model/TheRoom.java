package com.mike.mareu.model;

/**
 *  Model object representing a Room for the Spinner
 */
public class TheRoom {
    /** Identifier */
    private Long id;

    /** Name's room */
    private String roomName;

    /**
     * Constructor
     * @param id
     * @param roomName
     * */

    public TheRoom(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    // Text show in Spinner
    @Override
    public String toString() { return this.getRoomName(); }
}
