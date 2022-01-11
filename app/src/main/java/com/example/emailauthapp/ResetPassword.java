package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    EditText userPassword,userConfirmPassword;
    Button savePasswordBtn;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_password);

        userPassword = findViewById(R.id.NewUserPassword);
        userConfirmPassword = findViewById(R.id.NewConfirmPass);
        user = FirebaseAuth.getInstance().getCurrentUser();


        savePasswordBtn = findViewById(R.id.resertPasswordBtn);
        savePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userPassword.getText().toString().isEmpty()){
                    userPassword.setError("Required Field");
                    return;
                }
                if(userConfirmPassword.getText().toString().isEmpty()){
                    userConfirmPassword.setError("required Field!");
                    return;
                }

                if(!userPassword.getText().toString().equals(userConfirmPassword.getText().toString())){
                    userConfirmPassword.setError("Password Do Not match");
                }

                user.updatePassword(userPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ResetPassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this ,e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });


    }
}