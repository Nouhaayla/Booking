package com.Hotelcode.lakeSide_hotel.commands;

import com.Hotelcode.lakeSide_hotel.model.Role;
import com.Hotelcode.lakeSide_hotel.model.User;
import com.Hotelcode.lakeSide_hotel.repository.RoleRepository;
import com.Hotelcode.lakeSide_hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Initialize roles
            if (roleRepository.findAll().isEmpty()) {
                Role userRole = new Role("ROLE_USER");
                roleRepository.save(userRole);

                Role adminRole = new Role("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            // Register a default user
            if (userRepository.findByEmail("defaultuser@example.com").isEmpty()) {
                Role userRole = roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("Role not found: ROLE_USER"));

                User user = new User();
                user.setFirstName("Default");
                user.setLastName("User");
                user.setEmail("defaultuser@example.com");
                user.setPassword(passwordEncoder.encode("password123"));
                user.setRoles(Collections.singleton(userRole));
                userRepository.save(user);
            }
        };
    }
}
