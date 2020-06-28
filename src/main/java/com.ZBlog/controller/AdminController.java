package com.ZBlog.controller;

import com.ZBlog.commom.Result;
import com.ZBlog.server.AdminServer;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends ExceptionController{

    @Autowired
    AdminServer adminServer;

    @RequiresRoles(value={"admin"})
    @PostMapping("/getUserList")
    public Result getUserList(){
        return adminServer.getUserList();
    }

    @RequiresRoles(value={"admin"})
    @PostMapping("/banUser")
    public Result banUserByUserId(@RequestBody String userId){
        return adminServer.banUserByUserId(userId);
    };

    @RequiresRoles(value={"admin"})
    @PostMapping("/changeRole")
    public Result changeRoleByUserId(@RequestBody Map<String, String> map){
        return adminServer.changeRoleByUserId(map.get("userId"),map.get("role"));
    };
}
