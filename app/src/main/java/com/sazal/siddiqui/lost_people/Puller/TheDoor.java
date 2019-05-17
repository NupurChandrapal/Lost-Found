package com.sazal.siddiqui.lost_people.Puller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sazal on 2017-10-15.
 */

public class TheDoor {

    private static final String ROOT_URL = "http://alliancemichenery.com/Dragon/lostFound/api/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static TheRoad driveOnRoad() {
        return getRetrofitInstance().create(TheRoad.class);
    }
}
