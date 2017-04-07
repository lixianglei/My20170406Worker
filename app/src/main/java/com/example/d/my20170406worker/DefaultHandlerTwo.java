package com.example.d.my20170406worker;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by d on 2017/4/6.
 */

public class DefaultHandlerTwo extends DefaultHandler {
    private String mNameStr;
    private String url;
    public String getUrl() {
        return url;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
             mNameStr=qName;

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        mNameStr="";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String str=new String(ch,start,length);
        if (mNameStr.equals("url")){
               this.url=str;
        }
    }
}
