package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class HeaterDaoTest {
    @Autowired
    private  HeaterDao heaterDao;

    @Test
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getById(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
    }
}
