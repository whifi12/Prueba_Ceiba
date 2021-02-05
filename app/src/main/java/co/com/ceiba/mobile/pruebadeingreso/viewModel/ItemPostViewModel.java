package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;

public class ItemPostViewModel extends ViewModel {

    public MutableLiveData<String> title;
    public MutableLiveData<String> body;

    public ItemPostViewModel() {
        this.title = new MutableLiveData<>();
        this.body = new MutableLiveData<>();
    }

    public void setData(Post user){
        title.setValue(user.getTitle());
        body.setValue(user.getBody());
    }

}
