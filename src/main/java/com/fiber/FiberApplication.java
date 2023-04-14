package com.fiber;

import com.fiber.entity.UnitMeasurementHeight;
import com.fiber.entity.UnitMeasurementWeight;
import com.fiber.entity.UserEntity;
import com.fiber.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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
                    80D, 180D, UnitMeasurementWeight.KILOGRAMS, UnitMeasurementHeight.CENTIMETER,
                    List.of());

            UserEntity userEntity2 = new UserEntity(null, "Yuri",
                    "yuri@gmail.com", passwordEncoder.encode("yuri"),
                    60D, 155D, UnitMeasurementWeight.KILOGRAMS, UnitMeasurementHeight.CENTIMETER,
                    List.of());
            if(userRepository.findByName(userEntity.getName()).isEmpty()) {
                log.info("create user[{}]", userRepository.save(userEntity));
            }
            if(userRepository.findByName(userEntity2.getName()).isEmpty()) {
                log.info("create user[{}]", userRepository.save(userEntity2));
            }
        };
    }

}
