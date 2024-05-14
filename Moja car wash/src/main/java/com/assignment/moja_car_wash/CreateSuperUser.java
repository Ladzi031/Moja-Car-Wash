package com.assignment.moja_car_wash;

import com.assignment.moja_car_wash.domain.entities.SupervisorEntity;
import com.assignment.moja_car_wash.repository.SupervisorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateSuperUser {
    private final SupervisorRepository supervisorRepository;
    private final PasswordEncoder passwordEncoder;
    private final Set<String> roles = Set.of("ROLE_ADMIN");

    public CreateSuperUser(SupervisorRepository supervisorRepository, PasswordEncoder passwordEncoder) {
        this.supervisorRepository = supervisorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 3; i++) {
            String name = "name" + i;
            String password = "password" + i;
            String email = "example@gmail.com";
            SupervisorEntity superUser = SupervisorEntity.builder()
                    .username(name)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .roles(roles)
                    .build();
            supervisorRepository.save(superUser);
        }
    }
}
