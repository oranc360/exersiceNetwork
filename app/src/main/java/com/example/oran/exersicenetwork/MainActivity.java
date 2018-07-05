package com.example.oran.exersicenetwork;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oran.exersicenetwork.network.Hits;
import com.example.oran.exersicenetwork.network.ImageSearch;
import com.example.oran.exersicenetwork.network.PixabayService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Retrofit retrofit;
    private PixabayService service;

    //views
    private EditText searchEditText;
    private RecyclerView hitsRecyclerView;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        initRetrofit();

        //init LinearLayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        hitsRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void clickSearch(View view) {
        //let the user know something is happening
        tvStatus.setText(R.string.searching);

        //create the call
        Call<ImageSearch> imageSearchResultCall = service.searchImage(searchEditText.getText().toString() , PixabayService.IMAGE_TYPE_ALL);

        //make the call
        imageSearchResultCall.enqueue(new Callback<ImageSearch>() {
            @Override
            public void onResponse(@NonNull Call<ImageSearch> call, @NonNull Response<ImageSearch> response) {
                if (response.code() == 200){
                    tvStatus.setText(R.string.found);
                    ArrayList<Hits> hits = (ArrayList<Hits>) response.body().getHits();
                    HitsAdapter hitsAdapter = new HitsAdapter(hits);
                    hitsRecyclerView.setAdapter(hitsAdapter);
                }else {
                    tvStatus.setText(R.string.error_code);
                    tvStatus.setTextColor(Color.RED);
                }
            }
            @Override
            public void onFailure(Call<ImageSearch> call, Throwable t) {
                tvStatus.setText(R.string.not_right);
            }
        });


    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(PixabayService.uri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PixabayService.class);
    }

    private void findViewById() {
        searchEditText = findViewById(R.id.etSearch);
        tvStatus = findViewById(R.id.tvStatus);
        hitsRecyclerView = findViewById(R.id.recyclerView);
    }
}
