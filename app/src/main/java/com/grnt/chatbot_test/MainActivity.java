package com.grnt.chatbot_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnListentoMusic, btnRecomtoMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnListentoMusic = findViewById(R.id.btnListentoMusic);
        btnRecomtoMusic = findViewById(R.id.btnRecomtoMusic);
        init();
    }

    void init(){
        btnListentoMusic.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchVoiceActivity.class)));
        btnRecomtoMusic.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ChatBotActivity.class)));
    }
}