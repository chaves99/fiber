package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticatedUserRetrieverService {

    private UserRepository userRepository;

    public Optional<UserEntity> retrieve(Authentication authentication) {
        return userRepository.findByName(authentication.getName())
                .or(() -> userRepository.findByEmail(authentication.getName()));
    }

}
