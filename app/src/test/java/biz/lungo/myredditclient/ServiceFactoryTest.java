package biz.lungo.myredditclient;

import org.junit.Assert;
import org.junit.Test;

import biz.lungo.myredditclient.model.ResponseTop;
import biz.lungo.myredditclient.network.ServiceFactory;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ServiceFactoryTest {

    @Test
    public void testRedditInterface() {
        final Call<ResponseTop> call = ServiceFactory
                .create("https://example.com")
                .fetchTopPosts(100, null, "after", 0);
        Assert.assertTrue(call.request().isHttps());
        Assert.assertEquals("GET", call.request().method().toUpperCase());
        Assert.assertEquals("https://example.com/top.json?limit=100&after=after&count=0", call.request().url().toString());
        final RequestBody body = call.request().body();
        Assert.assertNull(body);
    }
}
