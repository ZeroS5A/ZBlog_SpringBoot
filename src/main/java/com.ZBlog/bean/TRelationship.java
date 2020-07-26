package com.ZBlog.bean;


public class TRelationship {

  private Integer relationshipId;
  private String type;
  private Integer user1;
  private Integer user2;

  // 私有添加
  private String userName;
  private String nickName;
  private String avatar;
  private String summary;

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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Integer getRelationshipId() {
    return relationshipId;
  }

  public void setRelationshipId(Integer relationshipId) {
    this.relationshipId = relationshipId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Integer getUser1() {
    return user1;
  }

  public void setUser1(Integer user1) {
    this.user1 = user1;
  }


  public Integer getUser2() {
    return user2;
  }

  public void setUser2(Integer user2) {
    this.user2 = user2;
  }

  @Override
  public String toString() {
    return "TRelationship{" +
            "relationshipId=" + relationshipId +
            ", type='" + type + '\'' +
            ", user1=" + user1 +
            ", user2=" + user2 +
            ", userName='" + userName + '\'' +
            ", nickName='" + nickName + '\'' +
            ", avatar='" + avatar + '\'' +
            ", summary='" + summary + '\'' +
            '}';
  }
}
