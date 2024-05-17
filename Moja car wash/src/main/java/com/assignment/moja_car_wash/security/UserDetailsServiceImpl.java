package com.assignment.moja_car_wash.security;

import com.assignment.moja_car_wash.domain.entities.SupervisorEntity;
import com.assignment.moja_car_wash.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SupervisorEntity> superUser = supervisorRepository.findByUsername(username);
        return superUser.map(SuperUserToUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
