package com.devtaghreed.puzzlebeeapp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.databinding.CustomItemStatisticsBinding;

import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.SVH> {

 List<LevelStatistics> levelStatistics;

    public StatisticsAdapter(List<LevelStatistics> levelStatistics) {
        this.levelStatistics = levelStatistics;
    }

    @NonNull
    @Override
    public SVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemStatisticsBinding binding = CustomItemStatisticsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SVH holder, int position) {

        LevelStatistics LS = levelStatistics.get(position);

        holder.points.setText("Points: "+String.valueOf(LS.getLevelPoints()));
        holder.correct.setText("No.of correct ans: "+String.valueOf(LS.getNumCorrectAns()));
        holder.incorrect.setText("No. of incorrect ans: "+String.valueOf(LS.getNumIncorrectAns()));
        holder.lNum.setText("Level no.: "+String.valueOf(LS.getLevelId()));
        holder.qNo.setText("Total Questions: "+String.valueOf(LS.getNumOfRiddles()));
    }

    @Override
    public int getItemCount() {
        return levelStatistics.size();
    }

    class SVH extends RecyclerView.ViewHolder{

        TextView lNum, qNo, correct, incorrect, points;

        public SVH(@NonNull CustomItemStatisticsBinding binding) {
            super(binding.getRoot());

            lNum = binding.TvLevelNum;
            qNo = binding.TvLevelQ;
            correct =binding.TvCorrectQ;
            incorrect = binding.TvIncorrectQ;
            points =binding.TvLevelPoints;
        }
    }
}
