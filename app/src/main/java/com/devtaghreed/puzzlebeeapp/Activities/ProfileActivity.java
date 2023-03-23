package com.devtaghreed.puzzlebeeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devtaghreed.puzzlebeeapp.Adapters.StatisticsAdapter;
import com.devtaghreed.puzzlebeeapp.AppUtility;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;
import com.devtaghreed.puzzlebeeapp.Room_Database.ViewModel;
import com.devtaghreed.puzzlebeeapp.databinding.ActivityProfileBinding;

import java.util.Calendar;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    int age;

    ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        vm = new ViewModelProvider(this).get(ViewModel.class);

        //Setting Data
        vm.getUserData().observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                Log.d("test", userData.toString());
                binding.EtName.setText(userData.getName());
                binding.EtEmail.setText(userData.getEmail());
                binding.EtDB.setText(userData.getDoB());
                binding.SpCountry.setSelection(userData.getCounty());

                String g = userData.getGender();

                if (g.equalsIgnoreCase("Male")) {
                    binding.RbMale.setChecked(true);
                } else if (g.equalsIgnoreCase("Female")) {
                    binding.RbFemale.setChecked(true);
                }



            }
        });

        // Disabling elements until the user presses Edit
        binding.EtName.setEnabled(false);
        binding.EtEmail.setEnabled(false);
        binding.EtDB.setEnabled(false);
        binding.RbFemale.setEnabled(false);
        binding.RbMale.setEnabled(false);
        binding.SpCountry.setEnabled(false);
        binding.BtnSave.setVisibility(View.GONE);

// Setting the Date Picker
        binding.EtDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog
                        .newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                binding.EtDB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                age = now.get(Calendar.YEAR) - year;
                            }},
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show(getSupportFragmentManager(), " null ");
            }
        });

// enabling Editing
        binding.BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.EtName.setEnabled(true);
                binding.EtEmail.setEnabled(true);
                binding.EtDB.setEnabled(true);
                binding.RbFemale.setEnabled(true);
                binding.RbMale.setEnabled(true);
                binding.SpCountry.setEnabled(true);
                binding.BtnSave.setVisibility(View.VISIBLE);


            }
        });

// Updating data
        binding.BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting data and validation

                String name = binding.EtName.getText().toString();
                String email = binding.EtEmail.getText().toString();
                String gender = "";
                int county = binding.SpCountry.getSelectedItemPosition();
                String DoB = binding.EtDB.getText().toString();

                if (binding.RbFemale.isChecked()) {
                    gender = "Female";
                } else if (binding.RbMale.isChecked()) {
                    gender = "Male";
                }



//                Toast.makeText(ProfileActivity.this, "Values are: \n "
//                        + name + "\n" + email + "\n" + DoB + "\n" + gender, Toast.LENGTH_LONG).show();


                //    checking that the email is email Format
                if (!AppUtility.email_validation(email)) {
                    binding.EtEmail.setError("Email is badly formatted");
                } else {

                    if (!name.isEmpty() & !email.isEmpty() & !DoB.isEmpty()) {
                        vm.updateUserData(new UserData(1, name, email, county, gender, age, DoB));

                        Toast.makeText(ProfileActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        binding.EtName.setEnabled(false);
                        binding.EtEmail.setEnabled(false);
                        binding.EtDB.setEnabled(false);
                        binding.RbFemale.setEnabled(false);
                        binding.RbMale.setEnabled(false);
                        binding.SpCountry.setEnabled(false);
                        binding.BtnSave.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(ProfileActivity.this, "All data is required", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });



  // Getting Playing History
        vm.getLevelStatistics().observe(this, new Observer<List<LevelStatistics>>() {
            @Override
            public void onChanged(List<LevelStatistics> levelStatistics) {

                StatisticsAdapter adapter = new StatisticsAdapter(levelStatistics);
                binding.RV.setAdapter(adapter);
                binding.RV.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
            }
        });

    }



}