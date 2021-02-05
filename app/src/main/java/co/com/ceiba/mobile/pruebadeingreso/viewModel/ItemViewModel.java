package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.com.ceiba.mobile.pruebadeingreso.models.User;

public class ItemViewModel extends ViewModel {

    public MutableLiveData<String> name;
    public MutableLiveData<String> phone;
    public MutableLiveData<String> email;

    public ItemViewModel() {
        this.name = new MutableLiveData<>();
        this.phone = new MutableLiveData<>();
        this.email = new MutableLiveData<>();
    }

    public void setData(User user){
        name.setValue(user.getName());
        phone.setValue(user.getPhone());
        email.setValue(user.getEmail());
    }
}
