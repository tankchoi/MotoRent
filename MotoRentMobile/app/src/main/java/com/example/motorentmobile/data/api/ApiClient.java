package com.example.motorentmobile.data.api;

import android.content.Context;
import android.util.Base64;

import com.example.motorentmobile.util.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.13:8080/";

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {

            SharedPreferencesHelper prefs = SharedPreferencesHelper.getInstance(context);
            String username = prefs.getUsername();
            String password = prefs.getPassword();
            if (username == null || password == null) {
                return new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            String credentials = username + ":" + password;
            String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Authorization", basicAuth)
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
