package com.sazal.siddiqui.lost_people.Puller;

import com.sazal.siddiqui.lost_people.Model.LostFound;
import com.sazal.siddiqui.lost_people.Model.LostFoundData;
import com.sazal.siddiqui.lost_people.Model.Upload;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sazal on 2017-10-15.
 */

public interface TheRoad {
    @FormUrlEncoded
    @POST("backup.php")
    Call<LostFound> getAllLostFound(@Field("data_request") String something);

    @Multipart
    @POST("upload.php")
    Call<Upload> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);

    @FormUrlEncoded
    @POST("ins.php")
    Call<String> insertInfo(@Field("name") String name, @Field("height") String height, @Field("bodyColor") String bodyColor, @Field("bodyMark") String bodyMark, @Field("dress") String dress, @Field("_date") String _date, @Field("foundContact") String contact, @Field("foundAddress") String address, @Field("note") String note, @Field("lostPlace") String place, @Field("prize") String prize, @Field("isLost") short isLost, @Field("pictureUrl") String url);
}
