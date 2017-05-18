package com.example.xmlpullparser;

import java.util.List;

public class ClassBean {
  public String classtype;
  public List<StudentBean> list;
  
  
  public String getClasstype() {
    return classtype;
  }
  public void setClasstype(String classtype) {
    this.classtype = classtype;
  }
  public List<StudentBean> getList() {
    return list;
  }
  public void setList(List<StudentBean> list) {
    this.list = list;
  }
  
  
}
