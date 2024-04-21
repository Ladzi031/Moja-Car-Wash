package com.assignment.moja_car_wash.config;

import com.assignment.moja_car_wash.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(req -> req.requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
                ).formLogin(
                        Customizer.withDefaults()
                );

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }
}

/*
        // hardcode admin info... we can't provide registration for them in the front-end
        // application is small and potential misuse of the application by user might find the registration form
        // and gains access... so will only provide login form for them...
        UserDetails admin = User.withUsername("admin").password(encoder.encode("password1")).roles("ADMIN").build();

        UserDetails admin2 = User.withUsername("admin2").password(encoder.encode("password1")).roles("ADMIN").build();

        // now, where do we keep these guys?
        return new InMemoryUserDetailsManager(admin, admin2);



 here we define the super-users of our system...
 hardcode-ing is very much bad practice...
 since the application is small, we are going to hardcode, the admin info
 we can't afford to create login and registration for them in the front-end... for security reasons...
 potential abuse by an ordinary user, fiddling with the website might find these registration forms...
*/