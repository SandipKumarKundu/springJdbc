package com.example.demo.respository;

import com.example.demo.model.Users;

import java.util.HashMap;
import java.util.List;

public interface UsersRepositoryCustom {
    List<Users> login(String userName,String password);
}
