package com.muver.chars.network;

import com.muver.chars.util.OperationState;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    static Network instance;

    Retrofit retrofit;
    API api;

    private Network() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080/")
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

    public EncryptionPackage execute(EncryptionPackage p) {
        try {
            return api.encrypt(p).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            p.setState(OperationState.ServerUnavaliable);
            return p;
        }
    }
}
