package com.abora.rxjavatest.data;


import com.abora.rxjavatest.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
        //change from Call to observable
        // reactivex
    Single<List<PostModel>> getPosts();
}
