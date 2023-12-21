package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.payload.http.user.UserRegisterRequestPayload;
import com.fiber.payload.http.user.UserUpdateRequestPayload;
import com.fiber.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final SeasonService seasonService;

    public List<UserEntity> getAll() {
        List<UserEntity> users = this.userRepository.findAll();
        log.info("getAll - users size:{}", users.size());
        return users;
    }

    public UserEntity register(UserRegisterRequestPayload user) {
        UserEntity userEntity = this.userRepository.save(user.toEntity());
        seasonService.createDefault(userEntity.getId());
        return userEntity;
    }

    public UserEntity get(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity update(Long id, UserUpdateRequestPayload payload) {
        UserEntity userEntity = this.get(id);
        if (payload.name() != null) {
            userEntity.setName(payload.name());
        }
        if (payload.email() != null) {
            userEntity.setEmail(payload.email());
        }
        if (payload.weight() != null) {
            userEntity.setWeight(payload.weight());
        }
        if (payload.height() != null) {
            userEntity.setHeight(payload.height());
        }
        if (payload.weightUnit() != null && !payload.weightUnit().isEmpty()) {
            userEntity.weightUnit(payload.weightUnit());
        }
        if (payload.heightUnit() != null && !payload.heightUnit().isEmpty()) {
            userEntity.heightUnit(payload.heightUnit());
        }
        return this.userRepository.saveAndFlush(userEntity);
    }

    public boolean delete(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception er) {
            return false;
        }
    }
}
