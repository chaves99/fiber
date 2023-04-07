package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.payload.UserUpdateRequestPayload;
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

    public List<UserEntity> getAll() {
        List<UserEntity> users = this.userRepository.findAll();
        log.info("getAll - users size:{}", users.size());
        return users;
    }

    public UserEntity register(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public UserEntity get(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("User not found");
        });
    }

    public UserEntity update(Long id, UserUpdateRequestPayload payload) {
        UserEntity userEntity = this.get(id);
        if (payload.name() != null) {
            userEntity.setName(payload.name());
        }
        if (payload.email() != null) {
            userEntity.setEmail(payload.email());
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
