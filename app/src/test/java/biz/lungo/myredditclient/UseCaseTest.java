package biz.lungo.myredditclient;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import biz.lungo.myredditclient.common.di.CompositionRoot;
import biz.lungo.myredditclient.networking.posts.Post;
import biz.lungo.myredditclient.topposts.FetchTopPostsUseCase;
import io.reactivex.schedulers.Schedulers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class UseCaseTest {

    private CompositionRoot mCompositionRoot;
    private MockWebServer mServer;

    @Before
    public void setUp() {
        mCompositionRoot = new CompositionRoot();
    }

    @After
    public void tearUp() {
        try {
            mServer.shutdown();
        } catch (IOException ignored) {}
    }

    @Test
    public void testFetchTopPostsUseCase() {
        MockWebServer server = startServer();
        final String responseString = TestUtils.getFileContents("Top_first_page_response.json");
        server.enqueue(new MockResponse().setBody(responseString));
        final FetchTopPostsUseCase fetchTopPostsUseCase = mCompositionRoot.getFetchTopPostsUseCase(Schedulers.io());
        final DummyListener listener = new DummyListener();
        fetchTopPostsUseCase.registerListener(listener);
        fetchTopPostsUseCase.loadTopPostsAndNotify();
        fetchTopPostsUseCase.unregisterListener(listener);
    }

    @Test
    public void testFetchMoreTopPostsUseCase() {
        MockWebServer server = startServer();
        final String responseString = TestUtils.getFileContents("Top_second_page_response.json");
        server.enqueue(new MockResponse().setBody(responseString));
        final FetchTopPostsUseCase fetchTopPostsUseCase = mCompositionRoot.getFetchTopPostsUseCase(Schedulers.io());
        final DummyListener listener = new DummyListener();
        fetchTopPostsUseCase.registerListener(listener);
        fetchTopPostsUseCase.loadMoreTopPostsAndNotify();
        fetchTopPostsUseCase.unregisterListener(listener);
    }

    private MockWebServer startServer() {
        mServer = new MockWebServer();
        try {
            mServer.start(59494);
        } catch (IOException ignored) {}
        mServer.url("");
        return mServer;
    }

    private class DummyListener implements FetchTopPostsUseCase.Listener {
        @Override
        public void onTopPostsFetched(@NonNull List<Post> posts) {}
        @Override
        public void onTopPostsMoreFetched(@NonNull List<Post> posts) {}
        @Override
        public void onTopPostsFetchFailed(@NonNull String errorMessage) {}
    }

}
