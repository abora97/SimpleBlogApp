package com.abora.rxjavatest.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.abora.rxjavatest.data.PostsClient;
import com.abora.rxjavatest.pojo.PostModel;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


import io.reactivex.schedulers.Schedulers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();



    public void getPosts() {

        // change from Observable to single
        // data call oneTime will use single :D
        Single<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> postsMutableLiveData.setValue((o)), e -> Log.d(TAG, "getPosts: " + e)));



//        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(List<PostModel> value) {
//
//                postsMutableLiveData.setValue(value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                posts.setValue(e.getMessage());
//                Log.d(TAG, "onError: "+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };


//        Observer observer=new Observer() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object value) {
//               // postsMutableLiveData.postValue((List<PostModel>) value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

//        PostsClient.getINSTANCE().getPosts().enqueue(new Callback<List<PostModel>>() {
//            @Override
//            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                postsMutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//                posts.setValue(t.getMessage());
//            }
//        });
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        // to clear all Observable Observer connection
        compositeDisposable.clear();
    }
}
