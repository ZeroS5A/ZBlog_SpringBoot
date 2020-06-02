package com.ZBlog.controller;

import com.ZBlog.commom.Result;
import com.ZBlog.commom.ResultStatus;
import com.ZBlog.server.UploadServer;
import com.util.TokenUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class UploadController extends ExceptionController{

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UploadServer uploadServer;

    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/image")
    public Result upLoadAvatar(@RequestHeader("Authorization") String token, MultipartFile image){

        return uploadServer.uploadImage(Integer.valueOf(tokenUtil.getTokenData(token).get("userId")),image);
    }
}
