package com.example.d.my20170406worker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.example.d.my20170406worker.bean.Student;
import com.example.d.my20170406worker.search.SearchActivity;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends Activity {
    private PullToRefreshRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private int pageIndex;
    private Button mBtn;
    private List<Student>  data=new ArrayList<>();
    private SharedPreferences mShared;
    private SharedPreferences.Editor  mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn= (Button) findViewById(R.id.Main_SearchBtn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        mShared=getSharedPreferences("data",MODE_PRIVATE);
        mEditor=mShared.edit();
        pageIndex=mShared.getInt("Index",1);
        getInfo("1",String.valueOf(pageIndex),"10");
        pageIndex++;
        mEditor.putInt("Index",pageIndex);
        mEditor.commit();
        mRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.Main_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         mAdapter=new MyAdapter(MainActivity.this,data);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        //是否开启下拉刷新功能
        mRecyclerView.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        mRecyclerView.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        mRecyclerView.displayLastRefreshTime(true);
        //设置刷新回调
       mRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
           @Override
           public void onRefresh() {
               mRecyclerView.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mRecyclerView.setRefreshComplete();
                       //模拟没有数据的情况
                       data.clear();
                      getInfo("1",String.valueOf(pageIndex),"10");
                       pageIndex++;
                     mEditor.putInt("Index",pageIndex);
                       mEditor.commit();
                   }
               },2000);
           }
           @Override
           public void onLoadMore() {
                 mRecyclerView.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         mRecyclerView.setLoadMoreComplete();
                         getInfo("1",String.valueOf(pageIndex),"5");
                         pageIndex++;
                         mEditor.putInt("Index",pageIndex);
                         mEditor.commit();
                     }
                 },2000);
           }
       });
    }
    //请求网络数据对其进行解析
    private void getInfo(String catalog, String pageIndex, String pageSize) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ResponseBody> login = retrofitInterface.login(catalog,pageIndex,pageSize);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String str = response.body().string();
                        StringReader reader = new StringReader(str);
                        InputSource source = new InputSource(reader);
                        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                        try {
                            SAXParser saxParser = saxParserFactory.newSAXParser();
                            XMLReader xmlReader = saxParser.getXMLReader();
                            MyDefaultDemo defaultDemo = new MyDefaultDemo();
                            xmlReader.setContentHandler(defaultDemo);
                            xmlReader.parse(source);
                            ArrayList<Student> list = defaultDemo.getList();
                            if (list!=null){
                                data.addAll(list);
                            }
                            if (mAdapter!=null){
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                       Log.e("Error",t+"");
            }
        });
    }
    //适配器
    class MyAdapter extends BaseAdapter<Student> {
        public MyAdapter(Context context, List<Student> stu) {
            super(context, R.layout.view_item, stu);
        }
        @Override
        public void convert(ViewHolder holder, final Student student) {
            holder.setText(R.id.View_Name, student.getTitle());
            holder.setText(R.id.View_Sex, student.getBody());
            holder.setText(R.id.View_Url, student.getUrl());
            holder.setOnclickListener(R.id.View_Item,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
                    intent.putExtra("id",student.getId());
                    startActivity(intent);
                }
            });
        }
    }

}
