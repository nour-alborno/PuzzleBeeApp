package com.devtaghreed.puzzlebeeapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.devtaghreed.puzzlebeeapp.Room_Database.RiddleData;
import com.devtaghreed.puzzlebeeapp.databinding.FragmentRiddleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiddleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiddleFragment extends Fragment {

    public interface  onSendData{
        void sendData(int points, int currentPos);
    }

    onSendData onSendData;






    private static final String Arg_riddleData = "object";

    String userAns ;
    int point;
    int id;

    private RiddleData riddleData;

    private String correctAns;
    int duration;

    CountDownTimer timer;
    public RiddleFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RiddleFragment newInstance(RiddleData riddleData) {
        RiddleFragment fragment = new RiddleFragment();
        Bundle args = new Bundle();
        args.putSerializable(Arg_riddleData,riddleData);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            riddleData = (RiddleData) getArguments().getSerializable(Arg_riddleData);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRiddleBinding binding = FragmentRiddleBinding.inflate(inflater,container,false);

       int patternId = riddleData.getPatternId();
       binding.TvQuestion.setText(riddleData.getTitle());
        correctAns = riddleData.getCorrectAns();
             duration = riddleData.getDuration();






        switch (patternId){

            case 1:
                binding.RadioGroup.setVisibility(View.GONE);
                binding.BtnCheckAns.setVisibility(View.GONE);
                binding.ETSpace.setVisibility(View.GONE);
                binding.BtnTrue.setVisibility(View.VISIBLE);
                binding.BtnFalse.setVisibility(View.VISIBLE);


                binding.BtnFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        userAns = "false";

                        answerResponse(userAns);

                    }
                });



                binding.BtnTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAns = "true";

                 answerResponse(userAns);
                    }
                });




                break;

            case 2 :
                binding.RadioGroup.setVisibility(View.VISIBLE);
                binding.BtnCheckAns.setVisibility(View.GONE);
                binding.ETSpace.setVisibility(View.GONE);
                binding.BtnTrue.setVisibility(View.GONE);
                binding.BtnFalse.setVisibility(View.GONE);




                binding.RadioAns1.setText(riddleData.getAns1());
                binding.RadioAns2.setText(riddleData.getAns2());
                binding.RadioAns3.setText(riddleData.getAns3());
                binding.RadioAns4.setText(riddleData.getAns4());

                binding.RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                        int checked_RBtn = i;

                       int R1= binding.RadioAns1.getId();
                        int R2= binding.RadioAns2.getId();
                        int R3= binding.RadioAns3.getId();
                        int R4= binding.RadioAns4.getId();

                       if (R1 == i){
                           userAns = (String) binding.RadioAns1.getText();
                       } else if (R2 == i){
                           userAns = (String) binding.RadioAns2.getText();
                       } else if (R3 == i){
                           userAns = (String) binding.RadioAns3.getText();
                       } else {
                               userAns = (String) binding.RadioAns4.getText();
                       }

                       answerResponse(userAns);

                    }
                });



                break;

            case  3 :
                binding.RadioGroup.setVisibility(View.GONE);
                binding.BtnCheckAns.setVisibility(View.VISIBLE);
                binding.ETSpace.setVisibility(View.VISIBLE);
                binding.BtnTrue.setVisibility(View.GONE);
                binding.BtnFalse.setVisibility(View.GONE);

                binding.BtnCheckAns.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAns = binding.ETSpace.getText().toString();

                        answerResponse(userAns);
                    }
                });



                break;
        }


//    binding.BtnNext.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//          id +=1;
//
//        }
//    });

        return binding.getRoot();
    }


    void answerResponse(String userAns){
        if (correctAns.equals(userAns)){

            point = riddleData.getPoints();

            DialogCorrectFragment CF= DialogCorrectFragment.newInstance(riddleData.getHint());
            CF.show(getActivity().getSupportFragmentManager(),"dialog");




            onSendData.sendData(point, id);

        } else {
            point = 0;
            DialogIncorrectFragment ICF = DialogIncorrectFragment.newInstance(riddleData.getHint());
            ICF.show(getActivity().getSupportFragmentManager(),"dialog");

            onSendData.sendData(point, id);
        }


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        onSendData = (onSendData) context;
    }



}