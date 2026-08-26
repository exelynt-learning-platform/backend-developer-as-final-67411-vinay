package com.exelynt.resourcebookingsystem.service;

import com.exelynt.resourcebookingsystem.entity.User;

public interface UserService {

    User registerUser(User user);

    User findByEmail(String email);
}