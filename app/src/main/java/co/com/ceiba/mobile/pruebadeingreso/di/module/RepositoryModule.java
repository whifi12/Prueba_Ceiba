package co.com.ceiba.mobile.pruebadeingreso.di.module;

import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class RepositoryModule {

    @Provides
    public UserRepository providerTestRepositories(UserServices userServices){
        return new UserRepository(userServices);
    }


}
