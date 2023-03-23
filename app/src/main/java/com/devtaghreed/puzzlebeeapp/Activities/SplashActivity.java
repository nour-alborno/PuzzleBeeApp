package com.devtaghreed.puzzlebeeapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devtaghreed.puzzlebeeapp.R;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.Room_Database.RiddleData;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;
import com.devtaghreed.puzzlebeeapp.databinding.ActivitySlpashBinding;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {

    ViewModel vm;

    ActivitySlpashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySlpashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(ViewModel.class);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.s6);
        binding.icon.setAnimation(animation);

        try {
            JSONArray jsonArray = new JSONArray(jsonAsset());


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int levelNo = jsonObject.getInt("level_no");
                int unlockPoints = jsonObject.getInt("unlock_points");

                   vm.insertLevelData(new LevelData(levelNo,unlockPoints));

                JSONArray questionJsonArray = jsonObject.getJSONArray("questions");

                   Log.d("test", questionJsonArray.getString(0));

            }

            parcQuestion(jsonAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 2000);
    }


    String jsonAsset() {
        String json = "";

        try {

            InputStream data = getAssets().open("puzzleGameData.json");

            int size = data.available();

            byte[] buffer = new byte[size];

            data.read(buffer);
            json = new String(buffer, "UTF-8");

            data.close();
        } catch (IOException e) {
            e.printStackTrace();

            return "Not Found";
        }
        return json;
    }

    void parcQuestion( String jsonAsset){
        try {
            JSONArray jsonArray = new JSONArray(jsonAsset());


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int levelNo = jsonObject.getInt("level_no");
                int unlockPoints = jsonObject.getInt("unlock_points");


                JSONArray questionJsonArray = jsonObject.getJSONArray("questions");

                Log.d("test", questionJsonArray.getString(0));

                for (int j = 0; j < questionJsonArray.length(); j++) {
                    JSONObject jsonObject1 = questionJsonArray.getJSONObject(j);

                    int QId = jsonObject1.getInt("id");
                    String title = jsonObject1.getString("title");
                    String answer_1 = jsonObject1.getString("answer_1");
                    String answer_2 = jsonObject1.getString("answer_2");
                    String answer_3 = jsonObject1.getString("answer_3");
                    String answer_4 = jsonObject1.getString("answer_4");
                    String trueAns = jsonObject1.getString("true_answer");
                    int points = jsonObject1.getInt("points");
                    int duration = jsonObject1.getInt("duration");

                    JSONObject patternJsonobj = jsonObject1.getJSONObject("pattern");
                    int patternId = patternJsonobj.getInt("pattern_id");
                    String patternName = patternJsonobj.getString("pattern_name");

                    String hint = jsonObject1.getString("hint");

                    //    vm.insertQPattern(new QuestionPattern(patternId,patternName));

                    vm.insertRiddleData(new RiddleData(QId,patternId,levelNo,points,title,answer_1,answer_2,answer_3,
                            answer_4,trueAns,duration,hint, patternName));



                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}