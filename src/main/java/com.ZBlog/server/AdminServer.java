package com.ZBlog.server;

import com.ZBlog.commom.Result;

public interface AdminServer {
    public Result getUserList();

    public Result banUserByUserId(String userId);

    public Result changeRoleByUserId(String userId, String role);
}
