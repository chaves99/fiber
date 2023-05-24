package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.repository.DietSeasonRepository;
import com.fiber.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class SeasonServiceTest {

    @InjectMocks
    SeasonService service;

    @Mock
    DietSeasonRepository seasonRepository;

    @Mock
    UserRepository userRepository;

    private final DietSeasonEntity entity =
            new DietSeasonEntity(1L, "Test", "Test", 10d, 10d, 10d, 10d, null, null, true, null, null);

    @Test
    public void createIfNotExists_returnExistingSeasonSuccess() {
        Mockito.when(seasonRepository.findById(1L)).thenReturn(Optional.of(entity));
        DietSeasonEntity dietSeason = service.createIfNotExists(1L, 1L);
        Mockito.verify(seasonRepository, Mockito.never()).save(Mockito.any(DietSeasonEntity.class));
        System.err.println(entity);
        System.err.println(dietSeason);
        Assert.isTrue(entity.equals(dietSeason), "The entities should be equals");
    }

    @Test
    public void createIfNotExists_returnNewSeasonSuccess() {
        entity.setUser(UserEntity.builder().id(1L).build());
        Mockito.when(seasonRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(UserEntity.builder().id(1L).build()));

        Mockito.when(seasonRepository.save(Mockito.any())).thenReturn(entity);

        DietSeasonEntity dietSeason = service.createIfNotExists(1L, 1L);

        Assert.isTrue(dietSeason.getUser().getId().equals(1L), "User's id should be equals");
    }

    @Test()
    public void createIfNotExists_userNotFind() {
        Mockito.when(seasonRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.createIfNotExists(1L, 1L));
    }
}
