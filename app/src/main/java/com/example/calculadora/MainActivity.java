package com.example.calculadora;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imageButton1;
    private TextView numero;
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
        imageButton1 = findViewById(R.id.imageButton1);
        numero = findViewById(R.id.numero);
        imageButton1.setOnClickListener(this);
        numero.setOnClickListener(this);

    }
        public void onClick(View view){
            int id = view.getId();
            if (id == imageButton1.getId()){
                int i = Integer.parseInt((String) numero.getText());
                numero.setText(i+1);
            }
        }
    private void startMovingButton() {
        CountDownTimer crono = new CountDownTimer(10000, 1000) {
            @Override
            public void onFinish() {
                imageButton1.setVisibility(View.INVISIBLE); // Ocultarlo al final
            }

            @Override
            public void onTick(long millisUntilFinished) {
                imageButton1.setVisibility(View.INVISIBLE); // Ocultamos
                moveButtonToRandomPosition();               // Movemos
                imageButton1.setVisibility(View.VISIBLE);   // Mostramos
            }
        };
        crono.start();
    }
    private void moveButtonToRandomPosition() {
        int btnWidth = imageButton1.getWidth();
        int btnHeight = imageButton1.getHeight();

        int maxX = screenWidth - btnWidth;
        int maxY = screenHeight - btnHeight;

        float randomX = random.nextInt(Math.max(maxX, 1));
        float randomY = random.nextInt(Math.max(maxY, 1));

        imageButton1.setX(randomX);
        imageButton1.setY(randomY);
    }
    }

