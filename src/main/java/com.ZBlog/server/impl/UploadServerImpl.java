package com.ZBlog.server.impl;

import com.ZBlog.bean.TFile;
import com.ZBlog.commom.Result;
import com.ZBlog.commom.ResultStatus;
import com.ZBlog.dao.FileDao;
import com.ZBlog.server.UploadServer;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service("UploadServer")
public class UploadServerImpl implements UploadServer {
    @Autowired
    FileDao fileDao;

    @Override
    public Result uploadImage(Integer userId, MultipartFile file) {
        Result result = new Result();

        try {
            //生成MD5
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            System.out.println(hashString);

            //创建文件名
            String imgName = hashString + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // 文件读取路径,连接次数过多使用StringBuffer连接节省开销
            StringBuffer imgUrl = new StringBuffer();
            // 服务器路径
            imgUrl.append("https://lczeros.cn/blogData/images/").append(imgName);

            if(fileDao.checkFileName(hashString) == null){
                //文件存储路径
                String imgPath = "/usr/ZBlog/blogData/images/";

                // 文件存储文件夹
                File imgFolder = new File(imgPath);
                if (!imgFolder.exists()) {
                    imgFolder.mkdirs();
                }

                // 写入文件
                IOUtils.write(file.getBytes(), new FileOutputStream(new File(imgFolder,imgName)));

                // 记录写入数据库
                TFile tFile = new TFile();
                tFile.setUploadMd5(hashString);
                tFile.setUserId(userId);
                tFile.setDate(new Date());
                tFile.setIsAvatar(0);
                fileDao.insertFileName(tFile);
            }else {
                result.setMessage("文件已存在");
            }

            //返回图片地址
            HashMap map = new HashMap();
            map.put("imgUrl", imgUrl);
            map.put("imgName",hashString);
            result.setData(map);
            return result;

            // 文件存储路径
//            String imgPath = "/usr/ZBlog/blogData/images/";
//            String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
//
//            // 文件存储文件夹
//            File imgFolder = new File(imgPath);
//            if (!imgFolder.exists()) {
//                imgFolder.mkdirs();
//            }

            // 创建随机文件名
            //String imgName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            String imgName = hashString + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            // 文件读取路径,连接次数过多使用StringBuffer连接节省开销
//            StringBuffer imgUrl = new StringBuffer();
            //服务器路径
//            imgUrl.append("https://lczeros.cn").append("/blogData/images/").append(imgName);

//            // 写入文件
//
//            IOUtils.write(file.getBytes(), new FileOutputStream(new File(imgName)));
//
//            HashMap map = new HashMap();
//            map.put("imgUrl", imgUrl);
//            //返回图片地址
//            result.setData(map);
//            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }
}
