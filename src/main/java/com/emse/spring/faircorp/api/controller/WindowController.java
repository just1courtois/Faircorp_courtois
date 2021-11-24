package com.emse.spring.faircorp.api.controller;

import com.emse.spring.faircorp.api.dto.WindowDto;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
    @RequestMapping("/api/windows") // (2)
    @Transactional // (3)
    public class WindowController {
        private final WindowDao windowDao;
        private final RoomDao roomDao;

        public WindowController(WindowDao windowDao, RoomDao roomDao) { // (4)
            this.windowDao = windowDao;
            this.roomDao = roomDao;
        }
        
        @GetMapping // (5)
        public List<WindowDto> findAll() {
            return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());  // (6)
        }
    

        @GetMapping(path = "/{id}")
        public WindowDto findById(@PathVariable Long id) {
            return windowDao.findById(id).map(WindowDto::new).orElse(null); // (7)
        }

        @GetMapping(path = "/room/{room_id}")
        public List<WindowDto> findByRoom(@PathVariable Long room_id) {
            return windowDao.findWindowsByRoom(room_id).stream().map(WindowDto::new).collect(Collectors.toList()); // (7)
        }


        @CrossOrigin
        @PutMapping(path = "/switchWindowsOpen/{room_id}")
        public WindowDto switchWindowsStatusToOpen(@PathVariable Long room_id) {
            List<Window> windows = windowDao.findWindowsByRoom(room_id);
            for (Window window : windows) {
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.CLOSED? WindowStatus.OPEN: WindowStatus.OPEN);
            }
            return new WindowDto(windows);
        }

        @PutMapping(path = "/switchWindowsOn/{room_id}")
        public WindowDto switchWindowsStatusToClose(@PathVariable Long room_id) {
            List<Window> windows = windowDao.findWindowsByRoom(room_id);
            for (Window window : windows) {
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN? WindowStatus.CLOSED: WindowStatus.CLOSED);
            }
            return new WindowDto(windows);
        }

        @CrossOrigin
        @PutMapping(path = "/{id}/switch")
        public WindowDto switchStatus(@PathVariable Long id) {
            Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            return new WindowDto(window);
        }

        @PostMapping // (8)
        public WindowDto create(@RequestBody WindowDto dto) {
            // WindowDto must always contain the window room
            Room room = roomDao.getById(dto.getRoomId());
            Window window = null;
            // On creation id is not defined
            if (dto.getId() == null) {
                window = windowDao.save(new Window(dto.getName(), dto.getWindowStatus(), room));
            }
            else {
                window = windowDao.getById(dto.getId());  // (9)
                window.setWindowStatus(dto.getWindowStatus());
            }
            return new WindowDto(window);
        }

        @DeleteMapping(path = "/{id}")
        public void delete(@PathVariable Long id) {
            windowDao.deleteWindow(id);
        }
    }
