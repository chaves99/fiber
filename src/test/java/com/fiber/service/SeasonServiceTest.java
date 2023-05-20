package com.fiber.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
public class SeasonServiceTest {


    SeasonService service;

    @Test
    public void createIfNotExists_success() {
        service.createIfNotExists(null, null);
    }
}
