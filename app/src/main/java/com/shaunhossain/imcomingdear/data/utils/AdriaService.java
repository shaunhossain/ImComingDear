package com.shaunhossain.imcomingdear.data.utils;

import com.shaunhossain.imcomingdear.data.models.Message;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by adriaboschsaez on 29/11/2017.
 */

public interface AdriaService {

    @GET("todo/")
    Observable<List<Message>> getMessages();

    @GET("todo/")
    Call<Message> getMessage();

    @POST("todo/")
    Call<Message> postMessages(@Body Message message);
}

