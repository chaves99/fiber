package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.repository.DietSeasonRepository;
import com.fiber.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SeasonService {

    private final DietSeasonRepository seasonRepository;

    private final UserRepository userRepository;

    public List<SeasonResponsePayload> getByUserId(Long id) {
        var seasonEntity = seasonRepository.findByUserId(id);
        if (seasonEntity == null || seasonEntity.size() == 0) {
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

    public SeasonResponsePayload create(SeasonCreateRequestPayload payload) {
        try {
            UserEntity user = userRepository.findById(payload.userId())
                    .orElseThrow(() -> new ResourceNotFoundException(""));
            DietSeasonEntity entity = payload.toEntity(user, true);
            return SeasonResponsePayload.fromEntity(seasonRepository.save(entity));
        } catch (Exception e) {
            log.error("create season error", e);
            throw e;
        }
    }

    public DietSeasonEntity createIfNotExists(Long seasonId, Long userId) {
        return null;
    }


}
