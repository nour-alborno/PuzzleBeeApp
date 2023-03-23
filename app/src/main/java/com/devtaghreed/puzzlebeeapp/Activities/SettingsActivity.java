package com.devtaghreed.puzzlebeeapp.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.devtaghreed.puzzlebeeapp.Services.JobService;
import com.devtaghreed.puzzlebeeapp.Services.MyService;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

    List<LevelStatistics> levelStatisticsList;

    ViewModel vm;

    String soundKey = "soundKEy";
    String musicKEy = "musicKEy";
    String notificationKEy = "not";

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(ViewModel.class);


        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        edit = sp.edit();


        if (sp.getBoolean("Notification", true)) {
            binding.BtnNotification.setText("Turn off");
        } else {
            binding.BtnNotification.setText("Turn on");
        }

        if (sp.getBoolean("sound", true)) {
            binding.BtnSound.setText("Turn off");
        } else {
            binding.BtnSound.setText("Turn on");
        }

        binding.BtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.BtnNotification.getText().equals("Turn off")) {
                    edit.putBoolean(notificationKEy, false);

                    edit.putBoolean("Notification", false);
                    edit.commit();
                    binding.BtnNotification.setText("Turn on");
                } else {
                    edit.putBoolean(notificationKEy, true);
                    edit.putBoolean("Notification", true);
                    edit.commit();
                }
            }
        });

        binding.BtnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(getApplicationContext(), MyService.class);
                if (binding.BtnSound.getText().equals("Turn off")) {
                    edit.putBoolean(soundKey, false);
                    startService(intent);
                    edit.putBoolean("sound", false);
                    edit.commit();
                    binding.BtnSound.setText("Turn on");
                } else {
                    stopService(intent);
                    edit.putBoolean(soundKey, true);
                    edit.putBoolean("sound", true);
                    binding.BtnSound.setText("Turn off");
                    edit.commit();
                }
            }
        });


        binding.ImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
            }
        });


        levelStatisticsList = new ArrayList<>();
        vm.getLevelStatistics().observe(this, new Observer<List<LevelStatistics>>() {
            @Override
            public void onChanged(List<LevelStatistics> levelStatistics) {
                levelStatisticsList = levelStatistics;
            }
        });

        binding.BtnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vm.DeleteAllStatistics(levelStatisticsList);
                Toast.makeText(SettingsActivity.this, "You can Restart playing", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (sp.getBoolean("Notification", false)) {

            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);


            JobInfo jobInfo = null;
            ComponentName componentName = new ComponentName(getBaseContext(), JobService.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                jobInfo = new JobInfo.Builder(111, componentName)
                        .setPeriodic((24 * 60 * 60 * 1000), jobInfo.getMinFlexMillis())
                        // .setMinimumLatency(5000)
                        .setPersisted(true)
                        .build();
            }
            jobScheduler.schedule(jobInfo);

        }

        stopService(intent);
    }
}