package com.idigital.epam.energy.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idigital.epam.energy.entity.Role;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.repository.RoleRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.security.JwtUtil;
import com.idigital.epam.energy.security.UserMaxsus;
import com.idigital.epam.energy.security.UserProvider;
import com.idigital.epam.energy.service.DTO.ResponseResident;
import com.idigital.epam.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    HMACUtilsService hmacUtils;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }



    @Override
    public User getById(Long id) {
        return Optional.of(userRepository.findById(id)).filter(Optional::isPresent).get().get();
    }


    @Override
    public String authentication(UserMaxsus userMaxsus) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userMaxsus.getUsername(), userMaxsus.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        UserDetails userDetails = userProvider.loadUserByUsername(userMaxsus.getUsername());

        return jwtTokenUtil.generateToken(userDetails, userMaxsus.isRememberMe());
    }

    @Override
    public User create(Long cardNumber, String password) throws Exception {

        try {
            String resident = hmacUtils.getRequestWithHmac("ENERGY", "get_resident", "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/resident/card/" + cardNumber, "energyKey").getBody().toString();
            ObjectMapper mapper = new ObjectMapper();
            ResponseResident user = mapper.readValue(resident, ResponseResident.class);
            User u = new User();
            if (!userRepository.findUserByCardNumber(cardNumber.toString()).isPresent()){
                u.setActive(user.getResult().getActive());
                u.setFirstName(user.getResult().getFirstName());
                Set<Role> roles = new HashSet<>();
                roles.addAll(new HashSet<>(roleRepository.findAll()));
                u.setRole(roles);
                u.setLastName(user.getResult().getLastName());
                u.setPassword(passwordEncoder.encode(password));
                u.setCardNumber(String.valueOf(user.getResult().getCardNumber()));
//                u.setCitizenCardId(user.getResult().getCardNumber().toString());
                return userRepository.save(u);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        String username = getPrincipal();
        if (username != null){
            return Optional.of(userRepository.findUserByCardNumber(username)).filter(Optional::isPresent).get().get();
        }

        return null;
    }
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Override
    public User update(User data) throws Exception {
        return null;
    }

    @Override
    public void delete(User data) {

    }
}
