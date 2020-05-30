package com.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TLike {

  private Integer likeId;
  private Integer userId;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="Asia/Shanghai")
  private Date date;
  private Integer blogId=null;
  private Integer commentId=null;
  private Integer dialogId;
  private Integer rootId;

  public Integer getRootId() {
    return rootId;
  }

  public void setRootId(Integer rootId) {
    this.rootId = rootId;
  }

  public Integer getLikeId() {
    return likeId;
  }

  public void setLikeId(Integer likeId) {
    this.likeId = likeId;
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

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getDialogId() {
    return dialogId;
  }

  public void setDialogId(Integer dialogId) {
    this.dialogId = dialogId;
  }

  @Override
  public String toString() {
    return "TLike{" +
            "likeId=" + likeId +
            ", userId=" + userId +
            ", date=" + date +
            ", blogId=" + blogId +
            ", commentId=" + commentId +
            ", rootId=" + dialogId +
            '}';
  }
}
