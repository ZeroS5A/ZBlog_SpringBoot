package com.ZBlog.server.impl;

import com.ZBlog.commom.Result;
import com.ZBlog.dao.UserDao;
import com.ZBlog.server.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServerImpl implements AdminServer {
    @Autowired
    UserDao userDao;

    @Override
    public Result getUserList() {
        return null;
    }
}
