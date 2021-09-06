package com.example.flightticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.flightticket.API.APIResponseClasses.APIResponse;
import com.example.flightticket.API.RetrofitClient;
import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.Place;

//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}