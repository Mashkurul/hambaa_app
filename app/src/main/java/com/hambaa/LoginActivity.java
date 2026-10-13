package com.hambaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText emailField = findViewById(R.id.emailField);
        TextInputEditText passwordField = findViewById(R.id.passwordField);
        CheckBox rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        MaterialButton loginButton = findViewById(R.id.loginButton);
        TextView errorLabel = findViewById(R.id.errorLabel);

        emailField.setText(MockData.MOCK_EMAIL);
        passwordField.setText(MockData.MOCK_PASSWORD);
        rememberMeCheckBox.setChecked(true);
        errorLabel.setVisibility(View.GONE);

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText() != null ? emailField.getText().toString() : "";
            String password = passwordField.getText() != null ? passwordField.getText().toString() : "";

            if (MockData.validateLogin(email, password)) {
                errorLabel.setVisibility(View.GONE);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                errorLabel.setText("Invalid email or password.");
                errorLabel.setVisibility(View.VISIBLE);
            }
        });

        forgotPasswordLink.setOnClickListener(v -> Toast.makeText(this, "Password recovery will be available in a future version.", Toast.LENGTH_LONG).show());
    }
}
