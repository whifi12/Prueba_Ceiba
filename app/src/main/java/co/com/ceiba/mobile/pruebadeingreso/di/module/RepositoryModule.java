package co.com.ceiba.mobile.pruebadeingreso.di.module;

import co.com.ceiba.mobile.pruebadeingreso.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.rest.PostServices;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class RepositoryModule {

    @Provides
    public UserRepository providerUserRepositories(UserServices userServices){
        return new UserRepository(userServices);
    }

    @Provides
    public PostRepository providerPostRepository(PostServices postServices){
        return new PostRepository(postServices);
    }


}
