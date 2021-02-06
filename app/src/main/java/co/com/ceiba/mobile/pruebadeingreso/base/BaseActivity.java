package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public void showAlertDialog(int title, int message, boolean cancelable, int textPositiveButton, DialogInterface.OnClickListener onClickListenerPositiveButton, int textNegativeButton, DialogInterface.OnClickListener onClickListenerNegativeButton, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        if (view != null) {
            builder.setView(view);
        }
        builder.setMessage(message);
        builder.setCancelable(cancelable);

        if (onClickListenerPositiveButton != null) {
            builder.setPositiveButton(textPositiveButton, onClickListenerPositiveButton);
        }
        if (onClickListenerNegativeButton != null) {
            builder.setNegativeButton(textNegativeButton, onClickListenerNegativeButton);
        }
        alertDialog = builder.show();

    }

    public void createProgressDialog() {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setCancelable(false);
    }

    public void showProgressDIalog(final int text) {
        runOnUiThread(() -> {
            try {
                progressDialog.setMessage(getResources().getString(text));
                progressDialog.show();
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }
        });
    }

    public void dismissProgressDialog() {
        this.progressDialog.dismiss();
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    protected void configureDagger() {
        AndroidInjection.inject(this);
    }
}
