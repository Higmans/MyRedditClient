package biz.lungo.myredditclient.topposts;

import android.support.annotation.NonNull;

import java.util.List;

import biz.lungo.myredditclient.common.BaseObservable;
import biz.lungo.myredditclient.common.Constants;
import biz.lungo.myredditclient.networking.RedditApi;
import biz.lungo.myredditclient.networking.posts.Data;
import biz.lungo.myredditclient.networking.posts.Post;
import biz.lungo.myredditclient.networking.response.ResponseTop;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FetchTopPostsUseCase extends BaseObservable<FetchTopPostsUseCase.Listener> {

    private RedditApi mRedditApi;
    private Scheduler mScheduler;
    private String mBefore;
    private String mAfter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public interface Listener {
        void onTopPostsFetched(@NonNull List<Post> posts);
        void onTopPostsMoreFetched(@NonNull List<Post> posts);
        void onTopPostsFetchFailed(@NonNull String errorMessage);
    }

    public FetchTopPostsUseCase(RedditApi redditApi, Scheduler scheduler) {
        mRedditApi = redditApi;
        mScheduler = scheduler;
    }

    public void loadTopPostsAndNotify() {
        final DisposableObserver<ResponseTop> disposableObserver = mRedditApi
                .fetchTopPosts(Constants.POSTS_PER_PAGE, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableObserver<ResponseTop>() {
                    @Override
                    public void onNext(ResponseTop responseTop) {
                        final Data data = responseTop.data;
                        for (Listener listener : getListeners()) {
                            listener.onTopPostsFetched(data.posts);
                        }
                        mBefore = data.before;
                        mAfter = data.after;
                    }

                    @Override
                    public void onError(Throwable e) {
                        for (Listener listener : getListeners()) {
                            listener.onTopPostsFetchFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        compositeDisposable.remove(this);
                    }
                });
        compositeDisposable.add(disposableObserver);
    }

    public void loadMoreTopPostsAndNotify() {
        final DisposableObserver<ResponseTop> disposableObserver = mRedditApi
                .fetchTopPosts(Constants.POSTS_PER_PAGE, mBefore, mAfter)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableObserver<ResponseTop>() {
                    @Override
                    public void onNext(ResponseTop responseTop) {
                        final Data data = responseTop.data;
                        for (Listener listener : getListeners()) {
                            listener.onTopPostsMoreFetched(data.posts);
                        }
                        mBefore = data.before;
                        mAfter = data.after;
                    }

                    @Override
                    public void onError(Throwable e) {
                        for (Listener listener : getListeners()) {
                            listener.onTopPostsFetchFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        compositeDisposable.remove(this);
                    }
                });
        compositeDisposable.add(disposableObserver);
    }

    @Override
    public void unregisterListener(Listener listener) {
        super.unregisterListener(listener);
    }
}