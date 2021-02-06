package co.com.ceiba.mobile.pruebadeingreso;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.PostViewModel;
import io.reactivex.Observable;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostViewModelTest extends BaseTest{


    private PostViewModel postViewModel;

    @Mock
    PostRepository postRepository;

    private List<Post> posts;

    String id = "1";

    @InjectMocks
    Post post;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        posts = new ArrayList<>();
        postRepository = mock(PostRepository.class);
        postViewModel = Mockito.spy(new PostViewModel(postRepository,validateInternet));
    }

    @Test
    public void testLoadPostsIsSuccessful(){
        when(postRepository.getPostDB(id)).thenReturn(posts);
        postViewModel.loadPosts(id);
        assertTrue(postViewModel.getProgress().getValue());
        verify(postViewModel).validateService(posts,id);
    }


    @Test
    public void testValidateServiceDataBaseIsEmpty(){
        postViewModel.validateService(posts,id);
        verify(postViewModel).validateInternetToGetPost(id);
    }

    @Test
    public void testValidateServiceDataBaseIsNotEmpty(){
        loadData();
        postViewModel.validateService(posts,id);
        assertEquals(posts,postViewModel.getPosts().getValue());
        assertFalse(postViewModel.getProgress().getValue());
    }

    @Test
    public void testValidateInternetIsConnectedIsTrue(){
        loadData();
        when(validateInternet.isConnected()).thenReturn(true);
        when(postRepository.getPost(id)).thenReturn(Observable.just(posts));
        postViewModel.validateInternetToGetPost(id);
        verify(postViewModel).getPost(id);
    }

    @Test
    public void testValidateInternetIsConnectedIsFalse(){
        loadData();
        when(validateInternet.isConnected()).thenReturn(false);
        postViewModel.validateInternetToGetPost(id);
        assertEquals(postViewModel.getError().getValue().getTitle(),R.string.error_title);
        assertEquals(postViewModel.getError().getValue().getText(),R.string.internet_error);
    }

    @Test
    public void testLoadServiceIsSuccessful(){
        loadData();
        when(postRepository.getPost(id)).thenReturn(Observable.just(posts));
        postViewModel.getPost(id);
        verify(postViewModel).loadData(posts);
        verify(postViewModel).saveData(posts);
    }


    private void loadData(){
        post.setId("1");
        post.setUserId("1");
        post.setTitle("Title");
        post.setBody("Description");
        posts.add(post);
    }

}
