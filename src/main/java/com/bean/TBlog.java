package com.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public class TBlog {

  private Integer blogId;
  private long userId;
  private long classId;
  private String type;
  private String title;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Asia/Shanghai")
  private Date blogDate;
  private String blogContentHtml;
  private String blogContentMd;
  private Integer browse=null;
  private String summary;
  //私有添加
  private String userName;
  private String avatar;
  private List<TTags>  tagsList;
  private boolean isLike;
  private Integer likeNum;

  private Integer commentNum;

  public Integer getCommentNum() {
    return commentNum;
  }

  public void setCommentNum(Integer commentNum) {
    this.commentNum = commentNum;
  }

  public Integer getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(Integer likeNum) {
    this.likeNum = likeNum;
  }

  public boolean isLike() {
    return isLike;
  }

  public void setLike(boolean like) {
    isLike = like;
  }

  public List<TTags> getTagsList() {
    return tagsList;
  }

  public void setTagsList(List<TTags> tagsList) {
    this.tagsList = tagsList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getBlogId() {
    return blogId;
  }

  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Date getBlogDate() {
    return blogDate;
  }

  public void setBlogDate(Date blogDate) {
    this.blogDate = blogDate;
  }


  public String getBlogContentHtml() {
    return blogContentHtml;
  }

  public void setBlogContentHtml(String blogContentHtml) {
    this.blogContentHtml = blogContentHtml;
  }


  public String getBlogContentMd() {
    return blogContentMd;
  }

  public void setBlogContentMd(String blogContentMd) {
    this.blogContentMd = blogContentMd;
  }


  public Integer getBrowse() {
    return browse;
  }

  public void setBrowse(Integer browse) {
    this.browse = browse;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @Override
  public String toString() {
    return "TBlog{" +
            "blogId=" + blogId +
            ", userId=" + userId +
            ", classId=" + classId +
            ", type='" + type + '\'' +
            ", title='" + title + '\'' +
            ", blogDate=" + blogDate +
            ", blogContentHtml='" + blogContentHtml + '\'' +
            ", blogContentMd='" + blogContentMd + '\'' +
            ", browse=" + browse +
            ", summary='" + summary + '\'' +
            ", userName='" + userName + '\'' +
            ", avatar='" + avatar + '\'' +
            ", tagsList=" + tagsList +
            '}';
  }
}
