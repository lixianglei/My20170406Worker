package com.example.d.my20170406worker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WebViewActivity extends Activity {
    private WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
       webView= (WebView) findViewById(R.id.WebView_Activity);
        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        getInfo(id);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
 private  void getInfo(String id){
     Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
     Retrofit_ retrofit_ = retrofit.create(Retrofit_.class);
     Call<ResponseBody> call = retrofit_.call(id);
     call.enqueue(new Callback<ResponseBody>() {
         @Override
         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
             if(response.isSuccessful()){

                 SAXParserFactory  spf=SAXParserFactory.newInstance();
                 try {
                     SAXParser saxParser=spf.newSAXParser();
                     XMLReader xmlReader=saxParser.getXMLReader();
                    DefaultHandlerTwo  defaultHandlerTwo=new DefaultHandlerTwo();
                     xmlReader.setContentHandler(defaultHandlerTwo);
                     try {
                         xmlReader.parse(new InputSource(response.body().byteStream()));
                         String url = defaultHandlerTwo.getUrl();
                         webView.loadUrl(url);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 } catch (ParserConfigurationException e) {


                 } catch (SAXException e) {
                     e.printStackTrace();
                 }

             }
         }

         @Override
         public void onFailure(Call<ResponseBody> call, Throwable t) {
             t.printStackTrace();
         }
     });
 }
    private interface Retrofit_{
        @GET("action/api/news_detail")
        Call<ResponseBody> call(@Query("id") String id);
    }
}
