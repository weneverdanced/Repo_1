package com.wby.service.service_imp;

import com.wby.dao.UsersDao;
import com.wby.dao.dao_imp.UsersDaoImp;
import com.wby.entity.Users;
import com.wby.service.AccountService;

public class AccountServiceImp implements AccountService {
    private UsersDao usersDao = new UsersDaoImp();

    @Override
    public Users login(String id, String password) {
        Users newUser = null;
        Users user=usersDao.login(id,password);
        if(user==null){
            return null;
        }
        String type = usersDao.login(id,password).getType();
        newUser = new Users(
             user.getId(),
             user.getUsername(),
             user.getPassword(),
             user.getType(),
             user.getEmail()
        );
        return newUser;
    }

    @Override
    public Users register(String id, String username, String password, String type, String email) {
        // Check if username or ID already exists
        if (isUsernameOrIdExist(username, id)) {
            return null;
        }
        // Registration logic
        Users user = new Users(id, username, password, type, email);
        return usersDao.register(id, username, password, type, email);
    }

    @Override
    public boolean isUsernameOrIdExist(String username, String id) {
        Users existingUserByUsername = usersDao.findByUsername(username);
        Users existingUserById = usersDao.findById(id);
        return existingUserByUsername != null || existingUserById != null;
    }

    @Override
    public boolean updatePassword(String password,String id) {
        System.out.println("传过来的新密码"+password);
        if(usersDao.updatePassword(password,id)){
            System.out.println("service成功");
            return true;
        }else{
            System.out.println("service失败");
            return false;
        }
    }
}
