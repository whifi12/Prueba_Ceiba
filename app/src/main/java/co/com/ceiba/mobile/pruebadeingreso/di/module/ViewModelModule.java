package co.com.ceiba.mobile.pruebadeingreso.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.com.ceiba.mobile.pruebadeingreso.di.scope.ViewModelKey;
import co.com.ceiba.mobile.pruebadeingreso.util.FactoryViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.MainViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.PostViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    abstract ViewModel bindPostViewModel(PostViewModel postViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
