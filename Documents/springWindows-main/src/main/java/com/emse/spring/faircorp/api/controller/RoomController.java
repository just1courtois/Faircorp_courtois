package com.emse.spring.faircorp.api.controller;

import com.emse.spring.faircorp.api.dto.BuildingDto;
import com.emse.spring.faircorp.api.dto.HeaterDto;
import com.emse.spring.faircorp.api.dto.RoomDto;
import com.emse.spring.faircorp.api.dto.WindowDto;
import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;
    private List<Window> windows;

    public RoomController(RoomDao roomDao, BuildingDao buildingDao, WindowDao windowDao, HeaterDao heaterDao){
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;

    }


    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }




    @PostMapping // (8)  créer une room
    public RoomDto create(@RequestBody RoomDto dto) {
        // RoomDto must always contain the room building
        Building building = buildingDao.getById(dto.getBuildingId());
        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(), dto.getName(), building, dto.getCurrent_temperature(), dto.getTarget_temperature() ));
        } else {
            room = roomDao.getById(dto.getId());  // (9)
        }
        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }



    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteRoom(id);
    }






}
