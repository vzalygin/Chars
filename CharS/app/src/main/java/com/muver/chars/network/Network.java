package com.muver.chars.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.muver.chars.util.OperationState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    static Network instance;

    Retrofit retrofit;
    API api;

    private Network() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(API.class);
    }

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    public void execute(EncryptionPackage p, Handler handler) {
        Call<EncryptionPackage> call = api.encrypt(p);
        call.enqueue(new Callback<EncryptionPackage>() {
            @Override
            public void onResponse(Call<EncryptionPackage> call, Response<EncryptionPackage> response) {
                Message msg = new Message();
                msg.obj = response.body();
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<EncryptionPackage> call, Throwable t) {
                t.printStackTrace();
                p.setState(OperationState.ServerUnavaliable);
                Message msg = new Message();
                msg.obj = p;
                handler.sendMessage(msg);
            }
        });
    }
}
