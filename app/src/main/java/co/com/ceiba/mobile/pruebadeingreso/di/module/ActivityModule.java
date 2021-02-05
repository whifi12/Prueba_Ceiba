package co.com.ceiba.mobile.pruebadeingreso.di.module;

import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract PostActivity contributePostActivity();

}
