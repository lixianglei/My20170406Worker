package com.example.d.my20170406worker;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by d on 2017/4/5.
 */
//网络接口
public interface RetrofitInterface {
    @GET("action/api/news_list")
    Call<ResponseBody> login(@Query("catalog") String catalog, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
    @GET("action/api/search_list?catalog=blog")
    Call<ResponseBody> getSerarch(@Query("catalog") String catalog, @Query("content") String content, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);
}
