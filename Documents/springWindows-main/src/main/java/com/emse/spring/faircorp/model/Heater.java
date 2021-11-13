package com.emse.spring.faircorp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity// (1)
@Table(name = "HEATER")// (2)
public class Heater {
    @Id // (3).
    @GeneratedValue

    private Long id;

    @Column(nullable = false, length = 255)// (4)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)// (5)
    private HeaterStatus heaterStatus;

    @Column(nullable = true)
    private Long power;

    @ManyToOne //child of Room
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room ;//define Room


    public Heater() {
    }

    
    public Heater(String name, HeaterStatus status, Room room){
        this.heaterStatus = status;
        this.name = name;
        this.room = room;
    }

    public Long getId() {
        return this.id;
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

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }
}