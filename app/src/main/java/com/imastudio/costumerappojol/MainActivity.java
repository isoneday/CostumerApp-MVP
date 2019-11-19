package com.imastudio.costumerappojol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.costumerappojol.view.MapsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onGoride(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void onHistory(View view) {
    }
}
