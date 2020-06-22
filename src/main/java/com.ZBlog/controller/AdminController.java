package com.ZBlog.controller;

import com.ZBlog.commom.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends ExceptionController{

    @RequiresRoles(value={"admin"},logical = Logical.OR)
    @PostMapping("/getUserList")
    public Result getUserList(@RequestHeader("Authorization") String token){

        return null;
    }
}
