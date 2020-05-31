package com.ZBlog.controller;

import com.ZBlog.commom.Result;
import com.ZBlog.commom.ResultStatus;
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
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class UploadController extends ExceptionController{

    @Autowired
    TokenUtil tokenUtil;

    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/image")
    public Result upLoadAvatar(@RequestHeader("Authorization") String token, MultipartFile image){
        Result result = new Result();
        // 文件存储路径
        String imgPath = "/usr/ZBlog/blogData/images/";
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 文件存储文件夹
        File imgFolder = new File(imgPath+folderName);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }

        // 创建随机文件名
        String imgName = UUID.randomUUID().toString().replace("-", "") + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));

        // 文件读取路径,连接次数过多使用StringBuffer连接节省开销
        StringBuffer imgUrl =new StringBuffer();
        imgUrl.append("https://lczeros.cn").append("/blogData/images/").append(folderName).append("/").append(imgName);

        // 写入文件
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));

            HashMap map= new HashMap();
            map.put("imgUrl",imgUrl);
            //返回图片地址
            result.setData(map);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }
}
