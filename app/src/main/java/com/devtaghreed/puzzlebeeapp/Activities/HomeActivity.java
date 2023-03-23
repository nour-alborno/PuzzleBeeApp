package com.devtaghreed.puzzlebeeapp.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devtaghreed.puzzlebeeapp.Services.MyService;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;


public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    ViewModel vm;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

  static  JobScheduler jobScheduler;
  static   JobInfo jobInfo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        intent = new Intent(HomeActivity.this, MyService.class);
        startService(intent);


        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        edit = sp.edit();

        sp.getBoolean("SoundSwitch",false);



        vm = new ViewModelProvider(this).get(ViewModel.class);


        vm.insertUserData(new UserData(1,"Player1","player1@gmail.com",0,"Male",10,"1/1/2013"));

        binding.BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, StartPlayingActivity.class));
            }
        });


        binding.BtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });


        binding.BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent1 = new Intent(getApplicationContext(), MyService.class);
                stopService(intent1);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(intent);
    }
}