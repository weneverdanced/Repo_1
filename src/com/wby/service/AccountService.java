package com.wby.service;

import com.wby.entity.Submission;
import com.wby.entity.Users;

import java.util.List;

public interface AccountService {
    public Users login(String id, String password);
    public Users register(String id, String username, String password, String type, String email);
    boolean isUsernameOrIdExist(String username, String id);
    public boolean updatePassword(String password,String id);
}
