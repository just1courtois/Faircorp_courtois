package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;

import java.util.List;

public interface HeaterDaoCustom {

    List<Heater> findHeatersByRoom(Long id);
    List<Heater> findHeatersInABuilding(Long id);
    void deleteHeater(Long id);
    void deleteAllHeatersByRoom(Long id);




}
