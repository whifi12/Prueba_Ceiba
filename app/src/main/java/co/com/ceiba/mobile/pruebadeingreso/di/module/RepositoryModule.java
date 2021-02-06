package co.com.ceiba.mobile.pruebadeingreso.di.module;

import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.db.AppDatabase;
import co.com.ceiba.mobile.pruebadeingreso.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.rest.PostServices;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class RepositoryModule {

    @Provides
    public UserRepository providerUserRepositories(UserServices userServices, AppDatabase appDatabase){
        return new UserRepository(userServices,appDatabase);
    }

    @Provides
    public PostRepository providerPostRepository(PostServices postServices, AppDatabase appDatabase){
        return new PostRepository(postServices,appDatabase);
    }


}
