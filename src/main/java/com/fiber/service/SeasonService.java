package com.fiber.service;

import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.SeasonResponsePayload;
import com.fiber.repository.DietSeasonRepository;
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

    public List<SeasonResponsePayload> getByUserId(Long id) {
        try {
            var seasonEntity = seasonRepository.findByUserId(id);
            if (seasonEntity == null || seasonEntity.size() == 0) {
                throw new ResourceNotFoundException("Not found season for userid:[" + id + "]");
            }
            log.debug("getByUserId:{}", seasonEntity);
            return seasonEntity
                    .stream()
                    .map(SeasonResponsePayload::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("getByUserId - Exception:", e);
            return null;
        }
    }


}
