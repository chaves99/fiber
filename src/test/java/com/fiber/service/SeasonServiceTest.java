package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.repository.DietSeasonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
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


    @Test
    public void create_shouldCreateSeason() {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Test Season",
                "Description Season", 10d, 10d, 10d, 0d, LocalDate.now(), null, 1L);
        UserEntity user = UserEntity.builder().id(1L).name("User Test").build();
        DietSeasonEntity dietSeasonMock = mock(DietSeasonEntity.class);

        when(userService.get(anyLong())).thenReturn(user);

        service.create(payload);

        verify(seasonRepository, only()).save(any(DietSeasonEntity.class));
    }

    @Test
    public void create_shouldSetDateToNow() {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Test Season",
                "Description Season", 10d, 10d, 10d, 0d, null, null, 1L);
        UserEntity user = UserEntity.builder().id(1L).name("User Test").build();
        ArgumentCaptor<DietSeasonEntity> captor = ArgumentCaptor.forClass(DietSeasonEntity.class);

        when(userService.get(anyLong())).thenReturn(user);
        doReturn(mock(DietSeasonEntity.class)).when(seasonRepository).save(captor.capture());

        service.create(payload);

        assertThat(captor.getValue().getInitialDate()).isNotNull();
    }

}
