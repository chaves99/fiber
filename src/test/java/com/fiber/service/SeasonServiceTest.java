package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.repository.DietSeasonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SeasonServiceTest {

    @InjectMocks
    SeasonService service;

    @Mock
    DietSeasonRepository seasonRepository;

    @Mock
    UserService userService;

    private final DietSeasonEntity entity =
            new DietSeasonEntity(1L, "Test", "Test", 10d, 10d, 10d, 10d, null, null, true, null, null);


    @Test
    public void create_shouldCreateSeason() {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Test Season",
                "Description Season", 10d, 10d, 10d, 0d, LocalDate.now(), null, 1L);
//        DietSeasonEntity dietSeasonEntity = DietSeasonEntity.builder().id(1l).build();
        UserEntity user = UserEntity.builder().id(1L).name("User Test").build();
        DietSeasonEntity dietSeasonMock = mock(DietSeasonEntity.class);

        when(userService.get(anyLong())).thenReturn(user);
        when(seasonRepository.save(any(DietSeasonEntity.class)))
                .thenReturn(dietSeasonMock);

        SeasonResponsePayload seasonResponsePayload = service.create(payload);

        verify(dietSeasonMock, only()).toResponsePayload();
    }

}
