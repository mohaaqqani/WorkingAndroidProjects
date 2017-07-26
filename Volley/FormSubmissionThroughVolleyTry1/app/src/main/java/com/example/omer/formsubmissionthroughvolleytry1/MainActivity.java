package com.example.omer.formsubmissionthroughvolleytry1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.omer.formsubmissionthroughvolleytry1.Models.Doctor;
import com.example.omer.formsubmissionthroughvolleytry1.helper.WebServiceCall;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static Doctor    doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctor  =   new Doctor();

        final AppCompatEditText codeInput   =   (AppCompatEditText) findViewById(R.id.code_input);
        final AppCompatEditText nameInput   =   (AppCompatEditText) findViewById(R.id.name_input);
        AppCompatButton submitButton    =   (AppCompatButton)   findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((codeInput.getText().toString().equalsIgnoreCase(""))    ||
                        (nameInput.getText().toString().equalsIgnoreCase(""))){
                    if (codeInput.getText().toString().equalsIgnoreCase("")){
                        codeInput.setError("ERROR");
                        codeInput.setHint("DoctorCode");
                    }
                    if (nameInput.getText().toString().equalsIgnoreCase("")){
                        nameInput.setError("ERROR");
                        nameInput.setHint("DoctorName");
                    }
                    Toast.makeText(MainActivity.this, "No Field Could be Empty", Toast.LENGTH_SHORT).show();
                }   else {
                    Log.d(TAG, "onClick: code : ["+codeInput.getText().toString().trim()+"] name : ["+nameInput.getText().toString().trim()+"]");
                    doctor.setCode(codeInput.getText().toString().trim());
                    doctor.setName(nameInput.getText().toString().trim());

                    RequestQueue    requestQueue    = Volley.newRequestQueue(getApplicationContext());
                    WebServiceCall.AddDoctor(doctor,requestQueue);
                }
            }
        });
    }
}
