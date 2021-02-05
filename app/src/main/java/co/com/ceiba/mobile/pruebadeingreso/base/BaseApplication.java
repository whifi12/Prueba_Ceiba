package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;


public class BaseApplication extends MultiDexApplication implements HasActivityInjector, HasFragmentInjector  {

    private static Context context;


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        this.initDagger();
    }


    private void initDagger(){
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}
