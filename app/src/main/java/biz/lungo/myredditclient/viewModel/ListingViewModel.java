package biz.lungo.myredditclient.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import biz.lungo.myredditclient.app.MRCApplication;
import biz.lungo.myredditclient.model.Data;
import biz.lungo.myredditclient.model.Post;
import biz.lungo.myredditclient.model.ResponseTop;
import biz.lungo.myredditclient.network.RedditInterface;
import biz.lungo.myredditclient.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingViewModel extends ViewModel {
    private MutableLiveData<List<Post>> mData;
    private boolean firstPage;

    public LiveData<List<Post>> getData() {
        if (mData == null) {
            mData = new MutableLiveData<>();
            mData.setValue(Collections.<Post>emptyList());
            final String after = MRCApplication.getInstance().getPreferences().getString(Constants.PREFS_KEY_AFTER, null);
            final String before = MRCApplication.getInstance().getPreferences().getString(Constants.PREFS_KEY_BEFORE, null);
            final int count = MRCApplication.getInstance().getPreferences().getInt(Constants.PREFS_KEY_COUNT, 0);
            loadPosts(Constants.POSTS_PER_PAGE, after, before, count);
        }
        return mData;
    }

    @SuppressWarnings("SameParameterValue")
    private void loadPosts(int limit, String before, String after, final int count) {
        firstPage = before == null && after == null;
        final RedditInterface service = MRCApplication.getInstance().getRedditInterface();
        final Call<ResponseTop> call = service.fetchTopPosts(limit, before, after, count);
        call.enqueue(new Callback<ResponseTop>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTop> call, @NonNull Response<ResponseTop> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final Data data = response.body().data;
                    mData.setValue(data.posts);
                    final SharedPreferences preferences = MRCApplication.getInstance().getPreferences();
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Constants.PREFS_KEY_AFTER, data.after);
                    editor.putString(Constants.PREFS_KEY_BEFORE, data.before);
                    editor.putInt(Constants.PREFS_KEY_COUNT, count);
                    editor.apply();
                } else {
                    //TODO
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTop> call, @NonNull Throwable t) {
                //TODO
            }
        });
    }

    public void loadNext() {
        final String after = MRCApplication.getInstance().getPreferences().getString(Constants.PREFS_KEY_AFTER, null);
        int count = MRCApplication.getInstance().getPreferences().getInt(Constants.PREFS_KEY_COUNT, 0);
        if (count >= Constants.POSTS_SHOW_LIMIT - Constants.POSTS_PER_PAGE) {
            return;
        }
        count += 10;
        loadPosts(Constants.POSTS_PER_PAGE, null, after, count);
    }

    public void loadPrevious() {
        if (firstPage) {
            return;
        }
        final String before = MRCApplication.getInstance().getPreferences().getString(Constants.PREFS_KEY_BEFORE, null);
        int count = MRCApplication.getInstance().getPreferences().getInt(Constants.PREFS_KEY_COUNT, 0);
        count -= 10;
        loadPosts(Constants.POSTS_PER_PAGE, before, null, count);
    }
}
