package com.fiber;

import com.fiber.entity.UserEntity;
import com.fiber.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class FiberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiberApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            UserEntity userEntity = new UserEntity(null, "Chaves",
                    "viniciusbaleia1999@gmail.com", passwordEncoder.encode("chaves"),
                    3500.0, 500.0, 200.0);

            UserEntity userEntity2 = new UserEntity(null, "Yuri",
                    "yuri@gmail.com", passwordEncoder.encode("yuri"),
                    2400.0, 300.0, 150.0);
            if(userRepository.findByName(userEntity.getName()).isEmpty()) {
                log.info("create user[{}]", userRepository.save(userEntity));

            }
            if(userRepository.findByName(userEntity2.getName()).isEmpty()) {
                log.info("create user[{}]", userRepository.save(userEntity2));
            }
        };
    }

}
