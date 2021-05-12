package com.muver.chars.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.muver.chars.R;
import com.muver.chars.ServiceLocator;
import com.muver.chars.util.OperationState;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
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
                .baseUrl("https://chars-application.herokuapp.com")
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
