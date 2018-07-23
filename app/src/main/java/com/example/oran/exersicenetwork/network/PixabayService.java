package com.example.oran.exersicenetwork.network;

import com.example.oran.exersicenetwork.network.pojo.ImageSearch;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayService {

    /* base search image url */
    String uri = "https://pixabay.com/api/";

    /* api key */
    String apiKey = "8778484-bfadc211ed8b5b893e15b1ef4";
    String keyQery = uri + "?key=" + apiKey;

    /* search image parameters */
    String IMAGE_TYPE = "image_type";
    String QERY = "q";

    /* image_type parameters */
    String IMAGE_TYPE_ALL = "all";

    /* those an other image typets */
//    String IMAGE_TYPE_PHOTO = "photo";
//    String IMAGE_TYPE_ILLUSTRATION = "illustration";
//    String IMAGE_TYPE_VECTOR = "vector";

    @GET(keyQery)
    retrofit2.Call<ImageSearch> searchImage(@Query(QERY) String searchImage , @Query(IMAGE_TYPE) String imageType);

}
