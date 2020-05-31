package com.ZBlog.bean;


public class TTags {

  private long tagsId;
  private String tagName;
  private long classification;
  private String createUser;


  public long getTagsId() {
    return tagsId;
  }

  public void setTagsId(long tagId) {
    this.tagsId = tagId;
  }


  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }


  public long getClassification() {
    return classification;
  }

  public void setClassification(long classification) {
    this.classification = classification;
  }


  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  @Override
  public String toString() {
    return "TTags{" +
            "tagsId=" + tagsId +
            ", tagName='" + tagName + '\'' +
            ", classification=" + classification +
            ", createUser='" + createUser + '\'' +
            '}';
  }
}
