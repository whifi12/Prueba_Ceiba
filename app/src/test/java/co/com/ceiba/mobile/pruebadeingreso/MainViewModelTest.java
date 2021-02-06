package co.com.ceiba.mobile.pruebadeingreso;

import android.content.res.Resources;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.MainViewModel;
import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class MainViewModelTest extends BaseTest {


    private MainViewModel mainViewModel;

    @Mock
    UserRepository userRepository;


     private List<User> users;

     @InjectMocks
     User user;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        users = new ArrayList<>();
        userRepository = mock(UserRepository.class);
        mainViewModel = Mockito.spy(new MainViewModel(userRepository,validateInternet));
    }

    @Test
    public void testLoadUsersIsSuccessfulDB(){
        when(userRepository.getUsersDB()).thenReturn(users);
        mainViewModel.loadUsers();
        assertTrue(mainViewModel.getProgress().getValue());
        verify(mainViewModel).validateService(users);
    }

    @Test
    public void testValidateServiceDataBaseIsEmpty(){
        mainViewModel.validateService(users);
        verify(mainViewModel).validateInternetToGetUsers();
    }

    @Test
    public void testValidateServiceDataBaseIsNotEmpty(){
        loadData();
        mainViewModel.validateService(users);
        assertEquals(users,mainViewModel.getList().getValue());
        assertFalse(mainViewModel.getProgress().getValue());
    }

    @Test
    public void testValidateInternetIsConnectedIsTrue(){
        loadData();
        when(validateInternet.isConnected()).thenReturn(true);
        when(userRepository.getService()).thenReturn(Observable.just(users));
        mainViewModel.validateInternetToGetUsers();
        verify(mainViewModel).getUsersService();
    }

    @Test
    public void testValidateInternetIsConnectedIsFalse(){
        loadData();
        when(validateInternet.isConnected()).thenReturn(false);
        mainViewModel.validateInternetToGetUsers();
        assertEquals(mainViewModel.getError().getValue().getTitle(),R.string.error_title);
        assertEquals(mainViewModel.getError().getValue().getText(),R.string.internet_error);
    }

    @Test
    public void testLoadServiceIsSuccessful(){
        loadData();
        when(userRepository.getService()).thenReturn(Observable.just(users));
        mainViewModel.getUsersService();
        verify(mainViewModel).loadData(users);
        verify(mainViewModel).saveData(users);
    }


    private void loadData(){
        user.setId("1");
        user.setName("nombre");
        user.setPhone("312313");
        user.setEmail("email@email.com");
        users.add(user);
    }

}
