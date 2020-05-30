package com.server.impl;

import com.commom.Result;
import com.server.UploadServer;
import org.springframework.web.multipart.MultipartFile;

public class UploadServerImpl implements UploadServer {
    @Override
    public Result uploadAvatar(Integer userId, MultipartFile file) {
        return null;
    }
}
