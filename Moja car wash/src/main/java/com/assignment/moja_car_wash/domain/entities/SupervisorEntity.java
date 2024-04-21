package com.assignment.moja_car_wash.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "super_users")
public class SupervisorEntity {

    @Id
    private String username;

    private String password;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}
