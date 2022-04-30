package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.security.UserMaxsus;

import java.util.List;

public interface UserService extends CommonService<User>{


    User create(Long cardNumber, String password) throws Exception;

    User getCurrentUser();

    String authentication(UserMaxsus userMaxsus) throws Exception;

    List<User> getAll();

    User getById(Long id);
}
