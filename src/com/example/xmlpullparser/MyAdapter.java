package com.example.xmlpullparser;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter{
  public Context context;
  public List<ClassBean> list;
  
  public MyAdapter(Context context,List<ClassBean> list){
    this.context=context;
    this.list=list;
  }

  @Override
  public int getGroupCount() {
    // TODO Auto-generated method stub
    if (list!=null) {
      return list.size();
    }else{
      return 0;
    }
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    // TODO Auto-generated method stub
    int size=0;
    List<StudentBean> slist=list.get(groupPosition).getList();
    if (slist!=null) {
      size=slist.size();
    }
    return size;
  }

  @Override
  public Object getGroup(int groupPosition) {
    // TODO Auto-generated method stub
    
    return list.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    // TODO Auto-generated method stub
    List<StudentBean> slist=list.get(groupPosition).getList();
    StudentBean studentBean=null;
    if (slist!=null) {
      studentBean=slist.get(childPosition);
    }
    return studentBean;
  }

  @Override
  public long getGroupId(int groupPosition) {
    // TODO Auto-generated method stub
    return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    // TODO Auto-generated method stub
    return childPosition;
  }

  @Override
  public boolean hasStableIds() {
    // TODO Auto-generated method stub
    return true;
  }
  private TextView getTextView1() {  
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(  
            ViewGroup.LayoutParams.WRAP_CONTENT,  
            ViewGroup.LayoutParams.WRAP_CONTENT);  
    lp.setMargins(0, 0, 20, 0);  
    TextView tv = new TextView(context);  
    tv.setLayoutParams(lp);  
    tv.setGravity(Gravity.CENTER_VERTICAL);  
    tv.setPadding(50, 0, 0, 0);  
    tv.setTextSize(25); 
    tv.setTextColor(Color.RED);
    return tv;  
}  

private TextView getTextView2() {  
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(  
            ViewGroup.LayoutParams.WRAP_CONTENT,  
            ViewGroup.LayoutParams.WRAP_CONTENT);  
    lp.setMargins(20, 0, 50, 0);  
    TextView tv = new TextView(context);  
    tv.setLayoutParams(lp);  
    tv.setGravity(Gravity.CENTER_VERTICAL);  
    tv.setPadding(36, 0, 0, 0);  
    tv.setTextSize(18);  
    return tv;  
}  

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    LinearLayout ll = new LinearLayout(context);  
    ll.setOrientation(LinearLayout.HORIZONTAL);  
    TextView tv1 = getTextView1();  
    tv1.setText(list.get(groupPosition).getClasstype());  
    ll.addView(tv1);  
    return ll;  
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
      View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    LinearLayout ll = new LinearLayout(context);  
    ll.setOrientation(LinearLayout.HORIZONTAL);  
    List<StudentBean> sList = list.get(groupPosition).getList();  
    
    if (sList != null && sList.size() > 0  
        && sList.get(childPosition).getId() != 0) {  
    TextView tv = getTextView2();  
    tv.setText(sList.get(childPosition).getId()+"");  
    ll.addView(tv);  
    }  
    
    if (sList != null && sList.size() > 0  
            && sList.get(childPosition).getName() != null) {  
        TextView tv1 = getTextView2();  
        tv1.setText(sList.get(childPosition).getName());  
        ll.addView(tv1);  
    }  

    if (sList != null && sList.size() > 0  
            && sList.get(childPosition).getAge() != 0) {  
        TextView tv2 = getTextView2();  
        tv2.setText(sList.get(childPosition).getAge()+"");  
        ll.addView(tv2);  
    }  
    if (sList != null && sList.size() > 0  
        && sList.get(childPosition).getScore() != 0) {  
    TextView tv2 = getTextView2();  
    tv2.setText(sList.get(childPosition).getScore()+"");  
    ll.addView(tv2);  
    }  
    return ll;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    // TODO Auto-generated method stub
    return true;
  }
  
  


}
