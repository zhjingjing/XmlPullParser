package com.example.xmlpullparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;


public class MainActivity extends Activity {
  private Button button;
  private ExpandableListView listView;
  private MyAdapter myAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
    initdata();
    
  }
  private void initdata() {
    // TODO Auto-generated method stub
    button.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        InputStream is=getinputStream("version.xml");
        List<ClassBean> list=parseFile(is);
        listView.setAdapter(new MyAdapter(MainActivity.this, list));
      }
    });
  }
  private void init() {
    // TODO Auto-generated method stub
    button=(Button) findViewById(R.id.main_btn);
    listView=(ExpandableListView) findViewById(R.id.main_list);
  }
  
  
  /**
   * 获取文件输入流
   * */
  public InputStream getinputStream(String filename){
    InputStream is=null;
    try {
     is= getAssets().open(filename);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is;
    
  }
  
  public List<ClassBean> parseFile(InputStream is){
    List<ClassBean> arraList=null;
    List<StudentBean> slist=null;
    StudentBean studentBean=null;
    ClassBean classBean=null;
    
   
    try {
      //获取xmlpull解析器
      XmlPullParser xmlPullParser=Xml.newPullParser();
      xmlPullParser.setInput(is, "utf-8");
      int type=xmlPullParser.getEventType();
      while (type!=XmlPullParser.END_DOCUMENT) {
        switch (type) {
          case XmlPullParser.START_DOCUMENT:
            arraList=new ArrayList<ClassBean>();
            break;

          case XmlPullParser.START_TAG:
              if (xmlPullParser.getName().equals("class")) {
                 classBean=new ClassBean();
                 classBean.setClasstype(xmlPullParser.getAttributeValue(0));
                 slist=new ArrayList<StudentBean>();
              }else if(xmlPullParser.getName().equals("student")){
                  studentBean=new StudentBean();
                  studentBean.setId(Integer.parseInt(xmlPullParser.getAttributeValue(0)));
                  slist.add(studentBean);  
              } else if ("name".equals(xmlPullParser.getName())) {  
                  studentBean.setName(xmlPullParser.nextText());  
              } else if ("age".equals(xmlPullParser.getName())) {  
                  studentBean.setAge(Integer.parseInt(xmlPullParser.nextText()));  
              } else if("score".equals(xmlPullParser.getName())){
                  studentBean.setScore(Double.parseDouble(xmlPullParser.nextText()));
                } 
            break;
            
          case XmlPullParser.END_TAG:  
            if ("class".equals(xmlPullParser.getName())) {  
                classBean.setList(slist);  
                arraList.add(classBean);  
                classBean = null;  
            }  
            break;  
            
        }
        type=xmlPullParser.next();
      }
      
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    return arraList;
    
  }
}
