package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.repository.DietSeasonRepository;
import com.fiber.util.ListsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SeasonService {

    private final DietSeasonRepository seasonRepository;

    private final UserService userService;

    public List<SeasonResponsePayload> getByUserId(Long id) {
        var seasonEntity = seasonRepository.findByUserId(id);
        if (ListsUtil.containSomething(seasonEntity)) {
            throw new ResourceNotFoundException("Not found seasons for userid:[" + id + "]");
        }
        log.debug("getByUserId:{}", seasonEntity);
        return seasonEntity
                .stream()
                .map(SeasonResponsePayload::fromEntity)
                .collect(Collectors.toList());
    }

    public SeasonResponsePayload getActiveByUser(Long idUser) {
        var activeByUserId = seasonRepository.findActiveByUserId(idUser);

        return activeByUserId
                .map(SeasonResponsePayload::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Not found active season for userid:[" + idUser + "]"));

    }

    public DietSeasonEntity create(SeasonCreateRequestPayload payload) {
        log.info("create - payload:{}", payload);
        DietSeasonEntity seasonEntity = payload.toEntity(userService.get(payload.userId()), true);
        disableLastSeasonByUser(payload.userId());
        if (seasonEntity.getInitialDate() == null) {
            seasonEntity.setInitialDate(LocalDate.now());
        }
        return seasonRepository.save(seasonEntity);
    }

    private void disableLastSeasonByUser(Long userId) {
        var activeByUserId = seasonRepository.findActiveByUserId(userId);
        activeByUserId.ifPresent(season -> {
            log.info("disabling last season by user:[{}]", userId);
            season.setActive(Boolean.FALSE);
            seasonRepository.saveAndFlush(season);
        });
    }

    public DietSeasonEntity updateFinalDate(Long seasonId, LocalDate finalDate) {
        log.info("updateFinalDate - season id:{} finalDate:{}", seasonId, finalDate);
        DietSeasonEntity seasonEntity = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found season with id:[" + seasonId + "]"));
        if (finalDate != null) {
            seasonEntity.setFinalDate(finalDate);
        }
        return seasonRepository.saveAndFlush(seasonEntity);
    }

    public Optional<DietSeasonEntity> getDietSeason(Long id) {
        return seasonRepository.findById(id);
    }

    public void createDefault(Long userId) {
        SeasonCreateRequestPayload payload = new SeasonCreateRequestPayload("Default",
                "Default", 0D, 0D, 0D, 0D, LocalDate.now(), null, userId);
        create(payload);
    }


}
