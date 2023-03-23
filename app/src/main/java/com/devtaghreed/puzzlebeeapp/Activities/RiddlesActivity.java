package com.devtaghreed.puzzlebeeapp.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.devtaghreed.puzzlebeeapp.Adapters.ViewpagerAdapter;
import com.devtaghreed.puzzlebeeapp.Services.JobService;
import com.devtaghreed.puzzlebeeapp.R;
import com.devtaghreed.puzzlebeeapp.Fragments.RiddleFragment;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.RiddleData;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;
import com.devtaghreed.puzzlebeeapp.databinding.ActivityRiddlesBinding;


import java.util.ArrayList;
import java.util.List;


public class RiddlesActivity extends AppCompatActivity implements RiddleFragment.onSendData {

    ActivityRiddlesBinding binding;

    ViewModel vm;

    ArrayList<Fragment> fragments;
    List<RiddleData> riddleDataList;

    int LevelPoints ;
   static int userPoints;

    int correct, incorrect;
    MediaPlayer mediaPlayer;
    int numOfQ;
    int levelId;

    CountDownTimer timer;
    boolean isCounterRunning  = true;

    SharedPreferences sp;
    SharedPreferences.Editor edit;
    int id;

    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRiddlesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



//        if (sp.getBoolean("sound", true)){

            mediaPlayer = MediaPlayer.create(this, R.raw.backgroud_audio);
            mediaPlayer.setLooping(true);
   //     }




        fragments = new ArrayList<>();


        Intent x = getIntent();
         levelId = x.getIntExtra("Id", 0);

        sp = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        edit = sp.edit();

        // setting level num.
        binding.TvLevenum.setText("Level no.: ".concat(String.valueOf(levelId)));

id = 0;

        vm = new ViewModelProvider(this).get(ViewModel.class);


        vm.getAllQuestions(levelId).observe(this, new Observer<List<RiddleData>>() {
            @Override
            public void onChanged(List<RiddleData> riddleData) {

                riddleDataList =riddleData;
                 numOfQ = 0;


edit.putInt("LId",riddleData.get(0).getLevelId());

                for (int i = 0; i < riddleData.size(); i++) {


                        fragments.add(RiddleFragment.newInstance(riddleData.get(i))) ;

                        numOfQ += 1;

                  //   size = riddleData.size();

                }

                // setting num of riddles in this level
                 binding.TvRiddlesnum.setText("Riddles no.: ".concat(String.valueOf(numOfQ)));


                // setting the adapter
                ViewpagerAdapter adapter = new ViewpagerAdapter(RiddlesActivity.this,fragments);
                binding.ViewPager.setAdapter(adapter);
                binding.ViewPager.setUserInputEnabled(false);

                int LastGame = sp.getInt("LastGame",0);
                int Lid=sp.getInt("LId",0);
                Log.d("bbbb",LastGame+"");
                if (levelId == Lid){
                binding.ViewPager.setCurrentItem(LastGame);
            }}
        });

        binding.ViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                timer = new CountDownTimer( 1000*10,1) {
                    @Override
                    public void onTick(long l) {
                        //  l is the time left
                        int timeLeft = (int) l;

                        int min = timeLeft / 6000;
                        int sec = timeLeft % 6000 / 1000;
                        String timeLeftString = "" + min;
                        timeLeftString += ":";
                        if (sec<10) timeLeftString +="0";
                        timeLeftString += sec;

                        binding.TvTimer.setText(timeLeftString);

                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(RiddlesActivity.this, "Time Over", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.ViewPager.setCurrentItem(position+1);
                            }
                        },4000);

                    }
                }.start();

            }
        });


        binding.BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = binding.ViewPager.getCurrentItem();
                binding.ViewPager.setCurrentItem(currentPosition+1);
                LevelPoints -=3;
                isCounterRunning = false;

            }
        });

       // Log.d("test",numOfQ+"\n"+ correct+"\n"+incorrect+"\n"+LevelPoints);



    vm.getLevelStatistics().observe(this, new Observer<List<LevelStatistics>>() {
        @Override
        public void onChanged(List<LevelStatistics> levelStatistics) {

            for (int i = 0; i < levelStatistics.size(); i++) {

                userPoints += levelStatistics.get(i).getLevelPoints();
                binding.TvPoints.setText(String.valueOf(userPoints));
            }
        }
    });


    }



    @Override
    protected void onPause() {
        super.onPause();
        vm.insertStatistics(new LevelStatistics(levelId,numOfQ,correct,incorrect,LevelPoints));
    }



    @Override
    public void sendData(int points, int currentPos) {
        LevelPoints += points;


        if (points > 0){
            correct +=1;
           timer.cancel();
           int current = binding.ViewPager.getCurrentItem();
           binding.ViewPager.setCurrentItem(current+1);

           if (sp.getBoolean("sound",true)){
            mediaPlayer= MediaPlayer.create(RiddlesActivity.this,R.raw.achievement_bell);
            mediaPlayer.start();}

        }else {
            incorrect +=1;
               timer.cancel();
            int current = binding.ViewPager.getCurrentItem();
               binding.ViewPager.setCurrentItem(current +1);

            if (sp.getBoolean("sound", true)){
            mediaPlayer=MediaPlayer.create(RiddlesActivity.this,R.raw.lose);
            mediaPlayer.start();}
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sp.getBoolean("Notification",false)) {

            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);


            JobInfo jobInfo = null;
            ComponentName componentName = new ComponentName(getBaseContext(), JobService.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                jobInfo = new JobInfo.Builder(111, componentName)
                        .setPeriodic((24 * 60 * 60 * 1000), jobInfo.getMinFlexMillis())
                        //.setMinimumLatency(5000)
                        .setPersisted(true)
                        .build();
            }
            jobScheduler.schedule(jobInfo);

        }

        edit.putInt("LastGame", binding.ViewPager.getCurrentItem());
        edit.commit();
    }
}