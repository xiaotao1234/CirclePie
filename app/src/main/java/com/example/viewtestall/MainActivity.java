package com.example.viewtestall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    CirclePieView circlePieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.startAnimtor);
        circlePieView = findViewById(R.id.circle_pie);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circlePieView.initAnimator();
            }
        });
    }
}
