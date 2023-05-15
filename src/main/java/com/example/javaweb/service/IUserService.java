package com.example.javaweb.service;

import com.example.javaweb.model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName ,String password ,Integer status);
}
