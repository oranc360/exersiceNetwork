package com.example.oran.exersicenetwork.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oran.exersicenetwork.HitsAdapter;
import com.example.oran.exersicenetwork.R;
import com.example.oran.exersicenetwork.network.pojo.Hits;
import com.example.oran.exersicenetwork.network.pojo.ImageSearch;
import com.example.oran.exersicenetwork.network.PixabayService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private PixabayService pixabayService;

    //views
    private EditText etSearch;
    private RecyclerView rvHits;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById();
        initRetrofit();

        //init LinearLayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvHits.setLayoutManager(mLayoutManager);



    }

    public void searchClick(View view) {
        //let the user know something is happening
        tvStatus.setText(R.string.searching);

        //create the call
        Call<ImageSearch> imageSearchResultCall = pixabayService.searchImage(etSearch.getText().toString() , PixabayService.IMAGE_TYPE_ALL);

        //make the call
        imageSearchResultCall.enqueue(new Callback<ImageSearch>() {
            @Override
            public void onResponse(@NonNull Call<ImageSearch> call, @NonNull Response<ImageSearch> response) {
                if (response.code() == 200){
                    tvStatus.setText(R.string.found);
                    ArrayList<Hits> hits = (ArrayList<Hits>) response.body().getHits();
                    HitsAdapter hitsAdapter = new HitsAdapter(hits);
                    rvHits.setAdapter(hitsAdapter);
                }else {
                    tvStatus.setText(R.string.error_code);
                }
            }
            @Override
            public void onFailure(Call<ImageSearch> call, Throwable t) {
                tvStatus.setText(R.string.not_right);
            }
        });


    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PixabayService.uri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pixabayService = retrofit.create(PixabayService.class);
    }

    private void findViewById() {
        etSearch = findViewById(R.id.etSearch);
        tvStatus = findViewById(R.id.tvStatus);
        rvHits = findViewById(R.id.recyclerView);
    }

    public void toAboutAvtivity(View view) {
        Intent intent = new Intent(this , AboutActivity.class);
        startActivity(intent);
    }
}
