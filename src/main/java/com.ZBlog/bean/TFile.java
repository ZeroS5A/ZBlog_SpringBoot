package com.ZBlog.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TFile {

  private Integer fileId;
  private Integer userId;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Asia/Shanghai")
  private Date date;
  private Integer blogId;
  private String fileMd5;
  private String uploadMd5;
  private Integer isAvatar=0;

    public String getUploadMd5() {
        return uploadMd5;
    }

    public void setUploadMd5(String uploadMd5) {
        this.uploadMd5 = uploadMd5;
    }

    public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


  public Integer getBlogId() {
    return blogId;
  }

  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }


  public String getFileMd5() {
    return fileMd5;
  }

  public void setFileMd5(String fileMd5) {
    this.fileMd5 = fileMd5;
  }


  public Integer getIsAvatar() {
    return isAvatar;
  }

  public void setIsAvatar(Integer isAvatar) {
    this.isAvatar = isAvatar;
  }


    @Override
    public String toString() {
        return "TFile{" +
                "fileId=" + fileId +
                ", userId=" + userId +
                ", date=" + date +
                ", blogId=" + blogId +
                ", fileMd5='" + fileMd5 + '\'' +
                ", uploadMd5='" + uploadMd5 + '\'' +
                ", isAvatar=" + isAvatar +
                '}';
    }
}
