package com.ZBlog.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TComment {

  private Integer commentId;
  private Integer userId;
  private Integer blogId;
  private Integer likeNum;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Asia/Shanghai")
  private Date date;
  private String content;
  private Integer rootId =null;
  private Integer dialogId=null;

  //私有添加
  private String userName;
  private String nickName;
  private String avatar;
  private String toUserName;
  private String commentNum;
  private boolean isLike=true;
  private Integer lastCommentId;

  public Integer getLastCommentId() {
    return lastCommentId;
  }

  public void setLastCommentId(Integer lastCommentId) {
    this.lastCommentId = lastCommentId;
  }

  public boolean isLike() {
    return isLike;
  }

  public void setLike(boolean like) {
    isLike = like;
  }

  public String getCommentNum() {
    return commentNum;
  }

  public void setCommentNum(String commentNum) {
    this.commentNum = commentNum;
  }

  public String getToUserName() {
    return toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getBlogId() {
    return blogId;
  }

  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }

  public Integer getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(Integer likeNum) {
    this.likeNum = likeNum;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getRootId() {
    return rootId;
  }

  public void setRootId(Integer rootId) {
    this.rootId = rootId;
  }

  public Integer getDialogId() {
    return dialogId;
  }

  public void setDialogId(Integer dialogId) {
    this.dialogId = dialogId;
  }

  @Override
  public String toString() {
    return "TComment{" +
            "commentId=" + commentId +
            ", userId=" + userId +
            ", blogId=" + blogId +
            ", likeNum=" + likeNum +
            ", date=" + date +
            ", content='" + content + '\'' +
            ", toUserId=" + rootId +
            ", dialogId=" + dialogId +
            ", userName='" + userName + '\'' +
            ", avatar='" + avatar + '\'' +
            ", toUserName='" + toUserName + '\'' +
            ", commentNum='" + commentNum + '\'' +
            ", isLike=" + isLike +
            ", lastCommentId=" + lastCommentId +
            '}';
  }
}
