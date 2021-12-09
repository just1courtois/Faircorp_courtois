package com.emse.spring.faircorp.api.controller;

import com.emse.spring.faircorp.api.dto.HeaterDto;
import com.emse.spring.faircorp.api.dto.WindowDto;
import com.emse.spring.faircorp.api.requests.HeaterRequest;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/heaters")
@Transactional
@CrossOrigin
    public class HeaterController {
        private final HeaterDao heaterDao;
        private final RoomDao roomDao;

        public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
            this.heaterDao = heaterDao;
            this.roomDao = roomDao;
        }

        @GetMapping // (5)
        public List<HeaterDto> findAll() {
            return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
        }

        @GetMapping(path = "/room/{room_id}") // (5)
        public List<HeaterDto> findHeaterByRoom(@PathVariable Long room_id) {
            return heaterDao.findHeatersByRoom(room_id).stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
        }

        @GetMapping(path = "/{id}")
        public HeaterDto findById(@PathVariable Long id) {
            return heaterDao.findById(id).map(HeaterDto::new).orElse(null); // (7)
        }


        @PutMapping(path = "/{id}/switch")
        public HeaterDto switchStatus(@PathVariable Long id) {
            Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
            return new HeaterDto(heater);
        }

        @PutMapping(path = "/switchHeatersOn/{room_id}")
        public HeaterDto switchHeatersStatusToOn(@PathVariable Long room_id) {
            List<Heater> heaters = heaterDao.findHeatersByRoom(room_id);
            for (Heater heater : heaters) {
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.OFF? HeaterStatus.ON: HeaterStatus.ON);
            }
            return new HeaterDto(heaters);

        }

        @PutMapping(path = "/switchHeatersOff/{room_id}")
        public HeaterDto switchHeatersStatusToOff(@PathVariable Long room_id) {
            List<Heater> heaters = heaterDao.findHeatersByRoom(room_id);
            for (Heater heater : heaters) {
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON? HeaterStatus.OFF: HeaterStatus.OFF);
            }
            return new HeaterDto(heaters);

        }



        @PostMapping // (8)
        public HeaterDto create(@RequestBody HeaterDto dto) {
            // HeaterDto must always contain the window room
            Room room = roomDao.getById(dto.getRoomId());
            Heater heater = null;
            // On creation id is not defined
            if (dto.getId() == null) {
                heater = heaterDao.save(new Heater(dto.getName(), dto.getHeaterStatus(), room));
            }
            else {
                heater = heaterDao.getById(dto.getId());  // (9)
                heater.setHeaterStatus(dto.getHeaterStatus());
            }
            return new HeaterDto(heater);
        }

        @PutMapping(path = "/rename/{id}/{name}")
        public HeaterDto rename(@PathVariable Long id, String name) {
            Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
            heater.setName(name);
            return new HeaterDto(heater);
        }

        @PostMapping(path = "/createByRoom/{id}") // (8)
        @CrossOrigin
        public HeaterRequest createByRoom(@RequestBody HeaterRequest dto, @PathVariable Long id) {
            // WindowDto must always contain the window room
            Room room = roomDao.getById(id);
            Heater heater = null;
            dto.setId(null);
            if (dto.getId() == null) {
                heater = heaterDao.save(new Heater(dto.getName(), dto.getHeaterStatus(), room));
            }
            else {
                heater = heaterDao.getById(dto.getId());  // (9)
                heater.setHeaterStatus(dto.getHeaterStatus());
            }
            return new HeaterRequest(heater);
        }

        @DeleteMapping(path = "/{id}")
        public void delete(@PathVariable Long id) {
            heaterDao.deleteById(id);
        }
    }
