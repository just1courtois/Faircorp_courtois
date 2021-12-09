package com.emse.spring.faircorp.api.requests;

import com.emse.spring.faircorp.model.Room;

public class RoomRequest {
    private Long id;
    private String name;
    private Integer floor;
    private Double current_temperature;
    private Double target_temperature;

    //private Long buildingId;


    public RoomRequest() {
    }

    public RoomRequest(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.current_temperature = room.getCurrentTemperature();
        this.target_temperature = room.getTargetTemperature();
        //this.buildingId = room.getBuilding().getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }

    /*public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }*/
}

