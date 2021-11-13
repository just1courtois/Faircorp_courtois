package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findWindowsByRoom(Long id);
    List<Window> findRoomOpenWindows(Long id);
    void openAllWindows(Long id);
    void deleteWindow(Long id);
}

