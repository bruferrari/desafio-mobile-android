package com.ferrarib.nexaaschallenge.data.source.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bruno Ferrari on 20 October 2016.
 */


public class ServiceFactory {

    private static Object INSTANCE;

    public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        INSTANCE = retrofit.create(clazz);
        return (T) INSTANCE;
    }

    public static Object getINSTANCE() {
        return INSTANCE;
    }
}