package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.model.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private  HeaterDao heaterDao;

    @Autowired
    private BuildingDao buildingDao;

    @Test//tested
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getById(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
    }
    @Test//tested
    public void shouldFindAWindow() {
        Window window = windowDao.getById(-10L);
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindowStatus()).isEqualTo(WindowStatus.CLOSED);
    }

    @Test //tested : null value was assigned to a property of primitive type setter spring boot => replace int and double by Integer and Double
    public void shouldFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-9L);
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("id", "windowStatus")
                .containsExactly(Tuple.tuple(-8L, WindowStatus.OPEN));
    }

    @Test //tested
    public void shouldNotFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-10L);
        Assertions.assertThat(result).isEmpty();
    }

    @Test //tested
    public void shouldFindARoom() {
        Room room = roomDao.getById(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
    }

    @Test//tested
    public void shouldDeleteWindowsByRoom() {
        Room room = roomDao.getById(-10L);
        List<Long> roomIds = room.getWindows().stream().map(Window::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        windowDao.deleteByRoom(-10L);
        List<Window> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result).isEmpty();

    }

    /*@Test
    public void shouldFindAllWindowsOfTheBuilding() {
        Building building = buildingDao.getById(-10L);
        List<Long> roomIds = building.getRooms().stream().map(Room::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        buildingDao.findAllWindows(-10L);
        List<Window> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result.size()).isEqualTo(4);
    }*/
}