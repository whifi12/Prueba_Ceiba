package co.com.ceiba.mobile.pruebadeingreso.di.module;


import android.app.Application;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
        ViewModelModule.class
})
public class ApplicationModule {

    @Singleton
    @Provides
    public Application providerApplication(BaseApplication app) {
        return app;
    }

    @Provides
    @Singleton
    public Retrofit providerRetrofit() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(interceptor).build();


        return new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient.build())
                .build();
    }

    @Provides
    @Singleton
    public UserServices providerPostApi(Retrofit retrofit) {
        return retrofit.create(UserServices.class);
    }

}
