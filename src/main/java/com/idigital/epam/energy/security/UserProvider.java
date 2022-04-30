package com.idigital.epam.energy.security;

import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserProvider implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepository.findUserByCardNumber(username);
        if(ou.isPresent()) {
            User u = ou.get();
         UserMaxsus um =  new UserMaxsus(u);
         um.setLavozimlar(u.getRole());
         return um;
        }
        throw new UsernameNotFoundException("Not Found");


    }
}
