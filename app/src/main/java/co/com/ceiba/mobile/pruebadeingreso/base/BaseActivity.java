package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.ProgressDialog;
import android.util.Log;

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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
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

    protected void configureDagger() {
        AndroidInjection.inject(this);
    }
}
