package com.example.assignment2_nitya_patel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText player1Text, player2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        player1Text = findViewById(R.id.Player1Text);
        player2Text = findViewById(R.id.Player2Text);

        Button playButton = findViewById(R.id.PlayButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String player1Name = player1Text.getText().toString().trim();
        String player2Name = player2Text.getText().toString().trim();

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill in both player names", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("PLAYER1_NAME", player1Name);
            intent.putExtra("PLAYER2_NAME", player2Name);
            startActivity(intent);
            finish();
        }
    }

}