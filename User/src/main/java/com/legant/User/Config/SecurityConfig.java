package com.legant.User.Config;

import com.legant.User.Service.CustomerDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private final CustomerDetailService customerDetailService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(CustomerDetailService customerDetailService) {
        this.customerDetailService = customerDetailService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            return http
                    .csrf().disable()
                    .authorizeRequests(auth ->
                            auth.antMatchers("/api/user/signup").permitAll()
                                    .antMatchers("/api/user/home").authenticated())
                    .userDetailsService(customerDetailService)
                    .formLogin(withDefaults())
                    .build();
        } catch (Exception e) {
            logger.error("Error loading user details" + e);
            throw e;
        }

    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
