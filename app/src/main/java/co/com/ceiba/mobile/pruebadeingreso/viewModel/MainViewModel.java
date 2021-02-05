package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IUserBL;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;

public class MainViewModel extends ViewModel {

    private IUserBL userRepository;

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
