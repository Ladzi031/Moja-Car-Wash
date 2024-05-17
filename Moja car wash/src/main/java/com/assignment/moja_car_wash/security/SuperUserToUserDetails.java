package com.assignment.moja_car_wash.security;

import com.assignment.moja_car_wash.domain.entities.SupervisorEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SuperUserToUserDetails implements UserDetails {
    // this class is used to convert the super-user-entity to into a UserDetails object...

    private final String name;
    private final String password;

    private final Set<GrantedAuthority> authorities;

    public SuperUserToUserDetails(SupervisorEntity user) {
        this.name = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

