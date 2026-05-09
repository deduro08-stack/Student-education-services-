package com.example.studentservices;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout loginLayout, dashboardLayout;
    EditText etUsername, etPassword;
    Button btnLogin;
    ImageView profilePic;

    ActivityResultLauncher<String> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginLayout = findViewById(R.id.loginLayout);
        dashboardLayout = findViewById(R.id.dashboardLayout);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        profilePic = findViewById(R.id.profilePic);

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {
                    if(result != null){
                        profilePic.setImageURI(result);
                    }
                }
        );

        profilePic.setOnClickListener(v -> {
            galleryLauncher.launch("image/*");
        });

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if(username.equals("student") && password.equals("1234")){

                loginLayout.setVisibility(View.GONE);
                dashboardLayout.setVisibility(View.VISIBLE);

            } else {

                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
