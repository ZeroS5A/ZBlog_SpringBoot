package com.ZBlog.bean;


import java.util.List;

public class TClassification {

  private Integer classId;
  private String name;
  private String childName;
  private String icon;
  //私定属性
  private List<TTags> tTagsList;

  public List<TTags> gettTagsList() {
    return tTagsList;
  }

  public void settTagsList(List<TTags> tTagsList) {
    this.tTagsList = tTagsList;
  }

  public Integer getClassId() {
    return classId;
  }

  public void setClassId(Integer classId) {
    this.classId = classId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getChildName() {
    return childName;
  }

  public void setChildName(String childName) {
    this.childName = childName;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

}
