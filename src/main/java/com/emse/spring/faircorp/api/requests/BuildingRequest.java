package com.emse.spring.faircorp.api.requests;

import com.emse.spring.faircorp.model.Building;

public class BuildingRequest {
    private Long id;
    private String name;

    public BuildingRequest(){
    }

    public BuildingRequest(Building building){
        this.id = building.getId();
        this.name = building.getName();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
