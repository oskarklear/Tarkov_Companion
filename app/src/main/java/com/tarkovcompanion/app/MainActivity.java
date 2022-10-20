package com.tarkovcompanion.app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Activity State", "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity State", "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity State", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity State", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity State", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity State", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity State", "onDestroy");
    }
}