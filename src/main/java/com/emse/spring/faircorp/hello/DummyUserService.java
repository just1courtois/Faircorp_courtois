package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {
    String[] array = {"Elodie", "Charles"};

    @Autowired
    private GreetingService greetingService;


    public void greetAll() {
        for (int i = 0; i < array.length; i++) {
            greetingService.greet(array[i]);
        }
    }
}


