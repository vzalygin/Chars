package com.muver.chars.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("/encrypt")
    Call<EncryptionPackage> encrypt(@Body EncryptionPackage p);
}
