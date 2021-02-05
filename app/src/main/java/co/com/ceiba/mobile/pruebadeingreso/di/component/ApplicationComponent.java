package co.com.ceiba.mobile.pruebadeingreso.di.component;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule;
import co.com.ceiba.mobile.pruebadeingreso.di.module.RepositoryModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        ApplicationModule.class,
        RepositoryModule.class

})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication> {
    }

    void inject(BaseApplication app);

}
