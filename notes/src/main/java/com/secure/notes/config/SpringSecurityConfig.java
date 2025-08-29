package com.secure.notes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    DataSource dataSource;
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.formLogin(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

//        UserDetails user1 = User.withUsername("user1").password("{noop}user1@123").roles("USER").build();
//        UserDetails user2 = User.withUsername("user2").password("{noop}user2@123").roles("USER").build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        if(!userDetailsManager.userExists("user1")){
           userDetailsManager.createUser(User.withUsername("user1").password("{noop}user1@123").roles("USER").build());
       }
        if(!userDetailsManager.userExists("user2")){
            userDetailsManager.createUser(User.withUsername("user2").password("{noop}user2@123").roles("ADMIN").build());
        }
//       userDetailsManager.createUser(user1);
//       userDetailsManager.createUser(user2);
        return userDetailsManager;
    }
}
