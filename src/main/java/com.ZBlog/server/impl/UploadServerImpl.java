package com.ZBlog.server.impl;

import com.ZBlog.commom.Result;
import com.ZBlog.server.UploadServer;
import org.springframework.web.multipart.MultipartFile;

public class UploadServerImpl implements UploadServer {
    @Override
    public Result uploadAvatar(Integer userId, MultipartFile file) {
        return null;
    }
}
