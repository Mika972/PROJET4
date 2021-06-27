package com.mike.mareu.model;

/**
 *  Model object representing a complete Meeting room after creation
 */
public class Meeting {
    /** Identifier */
    private Long id;

    /** Hour */
    private String hour;

    /** The name's room */
    private String room;

    /** The date meeting */
    private String date;

    /** The name's meetin */
    private String name;

    /** The participants */
    private String participant;

    /**
     * Constructor
     * @param id
     * @param hour
     * @param room
     * @param date
     * @param name
     * @param participant
     * */
    public Meeting(Long id, String hour, String room, String date, String name, String participant) {
        this.id = id;
        this.hour = hour;
        this.room = room;
        this.date = date;
        this.name = name;
        this.participant = participant;
    }

    public Long getId() { return id; }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}