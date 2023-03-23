package com.devtaghreed.puzzlebeeapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.devtaghreed.puzzlebeeapp.databinding.FragmentDialogIncorrectBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogIncorrectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogIncorrectFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String hint;


    public DialogIncorrectFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DialogIncorrectFragment newInstance(String hint) {
        DialogIncorrectFragment fragment = new DialogIncorrectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, hint);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hint = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogIncorrectBinding binding = FragmentDialogIncorrectBinding.inflate(inflater,container,false);

        binding.TvHint.setText(hint);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        // To give the custom dialog the right dimentions
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }
}