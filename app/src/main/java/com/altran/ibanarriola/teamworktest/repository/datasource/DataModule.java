package com.altran.ibanarriola.teamworktest.repository.datasource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class DataModule {

    private static final String BASE_URL_DEV = "http://yat.teamwork.com/";

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_DEV).client(okHttpClient).build();
    }

    @Provides
    @Singleton
    ApiDataSource providesApiDataSource(Retrofit retrofit) {
        return retrofit.create(ApiDataSource.class);
    }
}
