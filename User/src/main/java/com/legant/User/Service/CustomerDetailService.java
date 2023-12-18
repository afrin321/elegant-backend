package com.legant.User.Service;

import com.legant.User.Model.CustomerDetails;
import com.legant.User.Model.User;
import com.legant.User.Repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(CustomerDetailService.class);

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) {

        try {
            User user = userRepo.findByEmail(email).orElse(null);
            return new CustomerDetails(user);

        } catch (Exception e) {
            logger.error("Error loading user details for username: " + email, e);
            throw e;
        }
    }
}
