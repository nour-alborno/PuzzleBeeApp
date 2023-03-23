package com.devtaghreed.puzzlebeeapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devtaghreed.puzzlebeeapp.Adapters.LevelsAdapter;
import com.devtaghreed.puzzlebeeapp.Listeners.Listeners;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;
import com.devtaghreed.puzzlebeeapp.databinding.ActivityStartPlayingBinding;

import java.util.List;


public class StartPlayingActivity extends AppCompatActivity {


    ActivityStartPlayingBinding binding;

    ViewModel vm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //startService(new Intent(this,BackgroundMusicService.class));
        vm = new ViewModelProvider(this).get(com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel.class);






       vm.getLevels ().observe(this, new Observer<List<LevelData>>() {
            @Override
            public void onChanged(List<LevelData> levelData) {


                LevelsAdapter adapter = new LevelsAdapter(levelData ,new Listeners() {
                    @Override
                    public void getLevelID(int id) {
                        Intent x = new Intent(StartPlayingActivity.this, RiddlesActivity.class);
                        x.putExtra("Id",id);
                        startActivity(x);
                    }
                });
                binding.RvLevelsList.setAdapter(adapter);
                binding.RvLevelsList.setLayoutManager(new LinearLayoutManager(StartPlayingActivity.this,
                        RecyclerView.VERTICAL,false));
            }
        });



    }

}