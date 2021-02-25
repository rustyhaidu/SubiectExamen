package com.example.subiectexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.subiectexamen.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserEdit extends AppCompatActivity {
    private Spinner spinnerFunctie;
    private EditText nume;
    private EditText password;
    private RadioGroup radioGroup;
    private EditText timpIntrare;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        List<String> jobList = new ArrayList<>();

        jobList.add("doctor");
        jobList.add("inginer");
        jobList.add("profesor");
        jobList.add("muncitor");
        jobList.add("vanzator");
        jobList.add("programator");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                jobList);
        spinnerFunctie = (Spinner) findViewById(R.id.spinnerFunctie);
        spinnerFunctie.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        User user = (User) extras.getSerializable("user");

        String functie = user.getFunctie().toLowerCase();
        int spinnerPosition = adapter.getPosition(functie);
        spinnerFunctie.setSelection(spinnerPosition);

        RadioButton masculin = findViewById(R.id.masculin);
        RadioButton feminin = findViewById(R.id.feminin);

        String gen = user.getGen();
        if (gen.equals("feminin")) {
            feminin.setChecked(true);
        } else if (gen.equals("masculin")) {
            masculin.setChecked(true);
        }

        nume = findViewById(R.id.nume);
        password = findViewById(R.id.passwordEt);

        String numeString = user.getUsername();
        nume.setText(numeString);
        String passwordString = user.getPassword();
        password.setText(passwordString);

        timpIntrare = findViewById(R.id.timpIntrare);
        timpIntrare.setText(String.valueOf(user.getTime()));
        timpIntrare.setFocusable(false);

        String[] time = user.getTime().split(":");

        String mins = time[1];
        String hours = time[0];
        //long timeInMillis = mins * 60000L + hours * 360000L;

        timpIntrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(UserEdit.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                timpIntrare.setText(selectedHour + ":" + selectedMinute);
                            }
                        }, Integer.parseInt(hours), Integer.parseInt(mins), false);
                timePickerDialog.show();
            }
        });

        saveButton = findViewById(R.id.saveButton);
        radioGroup = findViewById(R.id.gen);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = nume.getText().toString();
                String passwordText = password.getText().toString();
                String timpIntrareText = timpIntrare.getText().toString();
                String functieText = spinnerFunctie.getSelectedItem().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String genText = radioButton.getText().toString();

                User newuser = new User(usernameText, passwordText, timpIntrareText, genText, functieText);

                boolean isFound = false;
                for (int i = 0; i < MainActivity.users.size(); i++) {
                    if (MainActivity.users.get(i).getUsername().equals(usernameText) && MainActivity.users.get(i).getPassword().equals(passwordText)) {
                       /* MainActivity.users.get(i).setUsername(usernameText);
                        MainActivity.users.get(i).setPassword(passwordText);
                        MainActivity.users.get(i).setTime(timpIntrareText);
                        MainActivity.users.get(i).setFunctie(functieText);
                        MainActivity.users.get(i).setGen(genText);*/
                        MainActivity.users.set(i, newuser);
                        isFound = true;
                    }
                }
                if (!isFound) {
                    MainActivity.users.add(newuser);
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}