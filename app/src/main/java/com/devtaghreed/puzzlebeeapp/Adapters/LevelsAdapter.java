package com.devtaghreed.puzzlebeeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devtaghreed.puzzlebeeapp.Listeners.Listeners;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.databinding.CustomItemLevelListBinding;

import java.util.List;


public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.levelHV> {

    List<LevelData> levelData;

    Listeners listeners;


    public LevelsAdapter(List<LevelData> levelData, Listeners listeners) {
        this.levelData = levelData;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public levelHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemLevelListBinding customitem=CustomItemLevelListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new levelHV(customitem);
    }

    @Override
    public void onBindViewHolder(@NonNull levelHV holder, int position) {

        LevelData level = levelData.get(position);

       // holder.level.setText(getString(R.string.level).concat(String.valueOf(level.getIdLevel())  ));
        holder.level.setText(String.valueOf(level.getIdLevel())  );
        holder.points.setText(String.valueOf(level.getRequiredPoints()));

//        if(RiddlesActivity.userPoints >= level.getRequiredPoints()){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listeners.getLevelID(level.getIdLevel());
//                }
//            });
//            holder.status.setText("open");
//        } else {
//            holder.status.setText("closed");
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.getLevelID(level.getIdLevel());
            }
        });


    }

    @Override
    public int getItemCount() {
        return levelData.size();
    }

    class levelHV extends RecyclerView.ViewHolder{

        TextView level,status, points;

        public levelHV(@NonNull CustomItemLevelListBinding binding) {
            super(binding.getRoot());

            level = binding.TvLevelnum;
            status =binding.TvStatus;
            points = binding.TvPointsRequired;
        }
    }
}
