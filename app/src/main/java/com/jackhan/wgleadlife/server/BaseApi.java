package com.jackhan.wgleadlife.server;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jackhan.wgleadlife.BaseApplication;

public abstract class BaseApi {
	static Retrofit mRetrofit;
	//static Retrofit mRetrofit_XML;

	/**
	 * gson
	 * 
	 * @author hanhuizhong
	 * @date 2016-8-11
	 * @return
	 */
	protected static Retrofit getRetrofit() {
		// TODO Auto-generated method stub

		if (mRetrofit == null) {

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();// 使用 gson coverter，统一日期请求格式

			mRetrofit = new Retrofit.Builder().baseUrl(Urls.SERVER_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(getClient()).build();
		}
		return mRetrofit;
	}

	/**
	 * xml
	 * 
	 * @author hanhuizhong
	 * @date 2016-8-11
	 * @return
	 */
	/*protected static Retrofit getRetrofit_XML() {
		// TODO Auto-generated method stub

		if (mRetrofit_XML == null) {

			mRetrofit_XML = new Retrofit.Builder().baseUrl(Urls.SERVER_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(SimpleXmlConverterFactory.create())
					.client(getClient()).build();
		}
		return mRetrofit_XML;
	}*/

	private static OkHttpClient getClient() {
		// TODO Auto-generated method stub
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(Level.BODY);

		// 缓存容量
		long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
		// 缓存路径
		String cacheFile = BaseApplication.getInstance().getCacheDir()
				+ "/http";
		Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);

		OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
				.addInterceptor(interceptor).cache(cache).build();
		return mOkHttpClient;
	}
}
