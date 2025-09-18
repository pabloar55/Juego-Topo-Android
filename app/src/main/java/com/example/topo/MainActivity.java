package com.example.topo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
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
    private ImageButton play;

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
        imageButton1.setVisibility(View.INVISIBLE);
        numero.setOnClickListener(this);
        play= findViewById(R.id.play);
        play.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == imageButton1.getId()) {
            int i = Integer.parseInt((String) numero.getText())+1;
            CharSequence texto = String.valueOf(i);
            numero.setText(texto);
        }
        if(id== play.getId()){
            play.setVisibility(View.INVISIBLE);
            imageButton1.setVisibility(View.VISIBLE);
            resetCounter();
            startMovingButton();
        }
    }

    private void startMovingButton() {
        CountDownTimer crono = new CountDownTimer(25000, 1000) {
            @Override
            public void onFinish() {
                imageButton1.setVisibility(View.INVISIBLE);// Ocultarlo al final
                play.setVisibility(View.VISIBLE);
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
        WindowManager wm = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int maxX = metrics.widthPixels-imageButton1.getWidth();
        int maxY = metrics.heightPixels-imageButton1.getHeight();
        double randomX = Math.random()*maxX;
        double randomY = Math.random()*maxY;
        imageButton1.setX((float) randomX);
        imageButton1.setY((float) randomY);
    }
    private void resetCounter(){
        numero.setText("0");
    }
}

