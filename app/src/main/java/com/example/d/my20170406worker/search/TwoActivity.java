package com.example.d.my20170406worker.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.d.my20170406worker.App;
import com.example.d.my20170406worker.R;
import com.example.d.my20170406worker.RetrofitInterface;
import com.example.d.my20170406worker.bean.Student;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TwoActivity extends Activity {
    private ListView mListview;
    private ArrayList<Student> stuList = new ArrayList<>();
    private ListAdapter adapter;
    private PullToRefreshRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_search);
        mListview = (ListView) findViewById(R.id.Search_mList);
        mRecyclerView= (PullToRefreshRecyclerView) findViewById(R.id.Two_RecyclerView);
        Intent intent =getIntent();
        String name = intent.getStringExtra("search");
        getRetrofit("blog", name, "3", "20");

    }

    public void getRetrofit(String catalog, String content, String pageIndex, String pageSize) {
        Retrofit retrofit = App.getRetrofit();
        RetrofitInterface anInterface = retrofit.create(RetrofitInterface.class);
        Call<ResponseBody> call = anInterface.getSerarch(catalog, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        StringReader reader = new StringReader(string);
                        InputSource source = new InputSource(reader);
                        SAXParserFactory spf = SAXParserFactory.newInstance();
                        SAXParser parser = spf.newSAXParser();
                        XMLReader xr = parser.getXMLReader();
                        SearchDefault handler = new SearchDefault();
                        xr.setContentHandler(handler);
                        xr.parse(source);
                        ArrayList<Student> mList = handler.getmList();
                        for (Student student : mList) {
                            Log.i("sd", student.toString());
                        }
                        adapter = new ListAdapter(mList);
                        mListview.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    class ListAdapter extends BaseAdapter {
        private ArrayList<Student> mList;

        public ListAdapter(ArrayList<Student> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.isEmpty() ? 0 : mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            hondler mH;
            if (convertView == null) {
                mH = new hondler();
                convertView = LayoutInflater.from(TwoActivity.this).inflate(R.layout.search_item, null);
                mH.mText1 = (TextView) convertView.findViewById(R.id.Search_mTextOne);
                mH.mText2 = (TextView) convertView.findViewById(R.id.Search_mTextTwo);
                convertView.setTag(mH);
            } else {
                mH = (hondler) convertView.getTag();
            }
            Student stu = mList.get(position);
            mH.mText1.setText(stu.getDescription());
            mH.mText2.setText(stu.getAuthor());
            return convertView;
        }

        class hondler {
            private TextView mText1, mText2;

        }
    }
}
