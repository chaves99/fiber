package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.repository.DietSeasonRepository;
import com.fiber.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SeasonServiceTest {

    @InjectMocks
    SeasonService service;

    @Mock
    DietSeasonRepository seasonRepository;

    @Mock
    UserRepository userRepository;


    @Test
    public void create_shouldCreateSeason() {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Test Season",
                "Description Season", 10d, 10d, 10d, 0d, LocalDate.now(), null, 1L);
        UserEntity user = UserEntity.builder().id(1L).name("User Test").build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        service.create(payload);

        verify(seasonRepository, times(1)).save(any(DietSeasonEntity.class));
    }

    @Test
    public void create_shouldSetDateToNow() {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Test Season",
                "Description Season", 10d, 10d, 10d, 0d, null, null, 1L);
        UserEntity user = UserEntity.builder().id(1L).name("User Test").build();
        ArgumentCaptor<DietSeasonEntity> captor = ArgumentCaptor.forClass(DietSeasonEntity.class);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doReturn(mock(DietSeasonEntity.class)).when(seasonRepository).save(captor.capture());

        service.create(payload);

        assertThat(captor.getValue().getInitialDate()).isNotNull();
    }

    @Test
    public void updateFinalDate_shouldUpdate() {
        when(seasonRepository.findById(anyLong()))
                .thenReturn(Optional.of(DietSeasonEntity.builder().id(1L).build()));

        ArgumentCaptor<DietSeasonEntity> captor = ArgumentCaptor.forClass(DietSeasonEntity.class);
        doReturn(mock(DietSeasonEntity.class)).when(seasonRepository).saveAndFlush(captor.capture());

        LocalDate now = LocalDate.now();
        service.updateFinalDate(1L, now);

        assertThat(captor.getValue().getFinalDate()).isEqualTo(now);
    }

    @Test
    public void updateFinalDate_shouldThrowNotFoundException() {
        when(seasonRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateFinalDate(1L, null));
    }

}
