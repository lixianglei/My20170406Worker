package com.example.d.my20170406worker;

import com.example.d.my20170406worker.bean.Student;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by d on 2017/4/5.
 */

public class MyDefaultDemo extends DefaultHandler {
    private String mNameStr;
    private ArrayList<Student>  mList=new ArrayList<>();
    private Student  mStu;
    public ArrayList<Student> getList(){
        return  mList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mNameStr=qName;
        if (mNameStr.equals("news")){
            if (mStu==null){
                mStu=new Student("title","body","url","id");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
          if (qName.equals("news")){
              mList.add(mStu);
              mStu=null;
          }
        mNameStr="";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String str=new String(ch,start,length);
        if(mNameStr.equals("title")){
            mStu.setTitle(str);
        }else if(mNameStr.equals("body")){
            mStu.setBody(str);
        }else if(mNameStr.equals("url")){
            mStu.setUrl(str);
        }else if(mNameStr.equals("id")){
            mStu.setId(str);
        }
    }
}
