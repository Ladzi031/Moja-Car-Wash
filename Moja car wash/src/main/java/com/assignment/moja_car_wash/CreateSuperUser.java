package com.assignment.moja_car_wash;

import com.assignment.moja_car_wash.domain.entities.SupervisorEntity;
import com.assignment.moja_car_wash.repository.SupervisorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
        SupervisorEntity mzwandile = SupervisorEntity.builder()
                .username("mzwandile")
                .password(passwordEncoder.encode("password1"))
                .email("mzwandile@gmail.com")
                .roles(roles)
                .build();

        SupervisorEntity ladzani = SupervisorEntity.builder()
                .username("ladzani")
                .password(passwordEncoder.encode("password2"))
                .email("ladzani@gmail.com")
                .roles(roles)
                .build();

        SupervisorEntity phiwa = SupervisorEntity.builder()
                .username("phiwa")
                .password(passwordEncoder.encode("password3"))
                .email("phiwa@gmail.com")
                .roles(roles)
                .build();

        SupervisorEntity germina = SupervisorEntity.builder()
                .username("germina")
                .password(passwordEncoder.encode("password4"))
                .email("germina@gmail.com")
                .roles(roles)
                .build();

        List<SupervisorEntity> admins = List.of(mzwandile, ladzani, phiwa, germina);
        supervisorRepository.saveAll(admins);
    }

}
