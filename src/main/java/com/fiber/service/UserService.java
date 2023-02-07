package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public UserEntity getUser(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("User not found");
        });
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
