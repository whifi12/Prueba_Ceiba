package co.com.ceiba.mobile.pruebadeingreso.util;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

public class BindingAdapters {

    @BindingAdapter("mutableText")
    public static void Text(final TextView textView, final MutableLiveData<String> date) {
        date.observeForever(s -> {
            if(date.getValue() != null) {
                textView.setText(date.getValue());
            }
        });
    }

}
