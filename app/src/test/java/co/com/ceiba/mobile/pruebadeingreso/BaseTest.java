package co.com.ceiba.mobile.pruebadeingreso;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.Context;
import android.content.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import co.com.ceiba.mobile.pruebadeingreso.util.ValidateInternet;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseTest {


    @Mock
    protected ValidateInternet validateInternet;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public InstantTaskExecutorRule instantRule = new InstantTaskExecutorRule();
    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();


    @Before
    public void setUp() throws Exception {
        validateInternet = mock(ValidateInternet.class);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
    }


}

