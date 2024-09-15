package com.wby.dao;

import com.wby.entity.Users;

public interface UsersDao {
    /**
     * 登录
     * @param id 输入账号
     * @param password 输入密码
     * @return 返回登录的用户信息
     */
    public Users login(String id, String password);

    /**
     * 注册
     * @return 返回注册的用户信息
     */
    public Users register(String id, String username, String password, String type, String email);

    Users findByUsername(String username);
    Users findById(String id);

    /**
     * 修改密码，更新用户信息
     * @param password 输入新密码
     * @return 返回用户新信息
     */
    public boolean updatePassword(String password,String id);
}
