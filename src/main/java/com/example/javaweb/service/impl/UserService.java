package com.example.javaweb.service.impl;

import com.example.javaweb.dao.IUserDao;
import com.example.javaweb.dao.impl.UserDao;
import com.example.javaweb.model.UserModel;
import com.example.javaweb.service.IUserService;

public class UserService implements IUserService {

    private IUserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        return userDao.findByUserNameAndPasswordAndStatus(userName,password,status);
    }
}
