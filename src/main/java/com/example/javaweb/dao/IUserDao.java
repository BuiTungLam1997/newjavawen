package com.example.javaweb.dao;

import com.example.javaweb.model.UserModel;

public interface IUserDao extends GenericDAO<UserModel>{
UserModel findByUserNameAndPasswordAndStatus(String userName ,String password ,Integer status);
}
