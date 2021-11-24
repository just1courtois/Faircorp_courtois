package com.emse.spring.faircorp.api.requests;
import com.emse.spring.faircorp.model.*;

import java.util.List;

public class WindowRequest {
    private Long id;
    private String name;
    private WindowStatus windowStatus;
    private String roomName;
    //private Long roomId;

    public WindowRequest() {
    }


    public WindowRequest(List<Window> windows) {
    }

    public WindowRequest(Window window) {
        this.id = window.getId();
        this.name = window.getName();
        this.windowStatus = window.getWindowStatus();
        this.roomName = window.getRoom().getName();
        //this.roomId = window.getRoom().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /*public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }*/
}
