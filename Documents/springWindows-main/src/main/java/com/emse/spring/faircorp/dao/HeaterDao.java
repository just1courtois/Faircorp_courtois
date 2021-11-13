package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeaterDao extends JpaRepository<Heater, Long>, HeaterDaoCustom {
    @Query("select h from Heater h where h.name=:name")
    Heater findByName(@Param("name") String name);

    List<Heater> findByRoomId(Long room_id);

    @Modifying
    @Query("delete from Heater h where h.room = ?1")
    void deleteByRoom(@Param("id") Long id);
}
