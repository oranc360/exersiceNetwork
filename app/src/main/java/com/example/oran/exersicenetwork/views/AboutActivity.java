package com.example.oran.exersicenetwork.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.oran.exersicenetwork.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }


    private void toWebSite(String webPage) {
        Uri uri = Uri.parse(webPage);
        Intent intent = new Intent(Intent.ACTION_VIEW , uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    public void toResources(View view) {
        switch (view.getId()){
            case R.id.tvPixabay:
                toWebSite("https://pixabay.com/");
                break;
            case R.id.tvRetrofit:
                toWebSite("http://square.github.io/retrofit/");
                break;
            case R.id.tvPicasso:
                toWebSite("http://square.github.io/picasso/");
                break;
        }
    }

    public void toSearchActivity(View view) {
        onBackPressed();
    }
}
