package com.jackhan.wgleadlife.server;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.utils.CommonUtils;
import rx.Observable;

import android.app.Activity;
import android.text.TextUtils;

import com.jackhan.wgleadlife.bean.BaseDataResult;
import com.jackhan.wgleadlife.bean.DataResult;
import com.jackhan.wgleadlife.bean.Movie;
import com.jackhan.wgleadlife.bean.MovieResult;
import com.jackhan.wgleadlife.bean.WeatherResult;

public class ServerApi extends BaseApi {

    private interface BaseService {
        @GET("movie")
        Observable<DataResult<MovieResult<List<Movie>>>> getMoviesObservable(
                @QueryMap Map<String, String> params);


        // @Headers("Cache-Control: public, max-age=30")
        @GET("weather")
        Observable<DataResult<WeatherResult>> getWeathersObservable(
                @QueryMap Map<String, String> params);

        @Multipart
        @POST("接口")
        Observable<BaseDataResult> saveFiles(
                @PartMap Map<String, RequestBody> params);

        // 请求 xml 格式
        // @Headers({ "Content-Type: text/xml", "Accept-Charset: utf-8" })
        // @POST("cimservice_api/service")
        // Observable<String> getXML(@Body String xml );

        @POST("接口")
        Observable<String> login(@QueryMap Map<String, String> params);
    }

    protected final static BaseService service = getRetrofit().create(
            BaseService.class);

    /*
     * 请求 xml 格式 public static Observable<String> getXML() { // TODO
     * Auto-generated method stub String xml ="xml"; return service.getXML(xml);
     * }
     */
    public static Observable<DataResult<MovieResult<List<Movie>>>> getMovies(
            String location) {
        // TODO Auto-generated method stub

        return service.getMoviesObservable(getMovies_params(location, true));
    }


    /**
     * @param location
     * @param isJSON   默认xml
     * @return
     * @author hanhuizhong
     * @date 2016-8-10
     */
    private static Map<String, String> getMovies_params(String location,
                                                        boolean isJSON) {
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<>();
        params.put("qt", "hot_movie");
        if (TextUtils.isEmpty(location))
            params.put("location", "石家庄");
        else
            params.put("location", location);
        if (isJSON)
            params.put("output", "json");
        params.put("ak", "ZqSI8jEqG2HHZjtp246dUf5XPC4phR53");
        params.put(
                "mcode",
                "DF:27:51:65:09:8B:23:76:7B:64:52:96:E9:08:76:10:49:64:21:BF;com.wugod.forestofmemory");
        return params;
    }

	/*
     * 09-18 09:52:09.013: I/MyHttpClient(6210):
	 * >>>>>>>>doPost_url:http://211.90
	 * .22.137:18080/aqyh/android/login/signLogin[user=15932697669, pwd=888888,
	 * versionName=2.1.5]
	 */

    public static Observable<String> login() {
        // TODO Auto-generated method stub

        Map<String, String> params = new HashMap<>();
        params.put("user", "15932697669");
        params.put("pwd", "888888");
        params.put("versionName", "2.1.5");
        return service.login(params);
    }

    public static Observable<DataResult<WeatherResult>> getWeathers(
            String location) {
        // TODO Auto-generated method stub

        Map<String, String> params = new HashMap<>();
        if (TextUtils.isEmpty(location))
            params.put("location", "石家庄");
        else
            params.put("location", location);
        params.put("output", "json");
        params.put("ak", "ZqSI8jEqG2HHZjtp246dUf5XPC4phR53");
        params.put(
                "mcode",
                "DF:27:51:65:09:8B:23:76:7B:64:52:96:E9:08:76:10:49:64:21:BF;com.wugod.forestofmemory");
        return service.getWeathersObservable(params);
    }

    public static Observable<BaseDataResult> saveFiles(Activity activity,
                                                       Map<String, String> params, List<File> files) {
        // TODO Auto-generated method stub

        return service.saveFiles(CommonUtils.updateFilesParams(activity,
                params, files));
    }

}
