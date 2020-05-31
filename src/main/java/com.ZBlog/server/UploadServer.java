package com.ZBlog.server;

import com.ZBlog.commom.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadServer {
    public Result uploadAvatar(Integer userId, MultipartFile file);
}
