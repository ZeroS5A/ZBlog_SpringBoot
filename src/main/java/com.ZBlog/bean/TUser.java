package com.ZBlog.bean;


public class TUser {

  private Integer userId;
  private String password;
  private String userName;
  private String occupation;
  private String avatar;
  private String summary;
  private String email;
  private long phone;
  private String gender;
  private String attribute;
  private String role;

  //私有添加
  private Integer blogNum;

  public Integer getBlogNum() {
    return blogNum;
  }

  public void setBlogNum(Integer blogNum) {
    this.blogNum = blogNum;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
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


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getPhone() {
    return phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "TUser{" +
            "userId=" + userId +
            ", password='" + password + '\'' +
            ", userName='" + userName + '\'' +
            ", occupation='" + occupation + '\'' +
            ", avatar='" + avatar + '\'' +
            ", summary='" + summary + '\'' +
            ", email='" + email + '\'' +
            ", phone=" + phone +
            ", gender='" + gender + '\'' +
            ", attribute='" + attribute + '\'' +
            ", role=" + role +
            '}';
  }
}
