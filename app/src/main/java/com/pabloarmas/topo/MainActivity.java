package com.pabloarmas.topo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton topo;
    private ImageButton topoaturdido;
    private TextView numero;
    private ImageButton play;
    private ImageView topera1;
    private ImageView topera2;
    private ImageView topera3;
    private ImageView topera4;
    private ImageView topera5;
    private ImageView topera6;
    private ImageView topera7;
    private ImageView topera8;
    private ImageView topera9;
    private ImageView topera10;
    private ImageView topera11;
    private ImageView topera12;
    private ArrayList<ImageView> toperas;
    int[] posicionTopo = new int[2];
    private TextView tiempoTexto;
    private int tiempo = 30;

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
        tiempoTexto = findViewById(R.id.tiempo);
        topera1 = findViewById(R.id.topera1);
        topera2 = findViewById(R.id.topera2);
        topera3 = findViewById(R.id.topera3);
        topera4 = findViewById(R.id.topera4);
        topera5 = findViewById(R.id.topera5);
        topera6 = findViewById(R.id.topera6);
        topera7 = findViewById(R.id.topera7);
        topera8 = findViewById(R.id.topera8);
        topera9 = findViewById(R.id.topera9);
        topera10 = findViewById(R.id.topera10);
        topera11 = findViewById(R.id.topera11);
        topera12 = findViewById(R.id.topera12);
        toperas = new ArrayList<>();
        toperas.add(topera1);
        toperas.add(topera2);
        toperas.add(topera3);
        toperas.add(topera4);
        toperas.add(topera5);
        toperas.add(topera6);
        toperas.add(topera7);
        toperas.add(topera8);
        toperas.add(topera9);
        toperas.add(topera10);
        toperas.add(topera11);
        toperas.add(topera12);
        topo = findViewById(R.id.topo);
        topoaturdido = findViewById(R.id.topoaturdido);
        numero = findViewById(R.id.numero);
        topo.setOnClickListener(this);
        topo.setVisibility(View.INVISIBLE);
        topoaturdido.setVisibility(View.INVISIBLE);
        tiempoTexto.setVisibility(View.INVISIBLE);
        play = findViewById(R.id.play);
        play.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == topo.getId()) {
            topoaturdido.setX(posicionTopo[0]);
            topoaturdido.setY(posicionTopo[1]);
            topoaturdido.setVisibility(View.VISIBLE);
            topo.setVisibility(View.INVISIBLE);
            int i = Integer.parseInt((String) numero.getText()) + 1;
            CharSequence texto = String.valueOf(i);
            numero.setText(texto);
        }
        if (id == play.getId()) {
            play.setVisibility(View.INVISIBLE);
            topo.setVisibility(View.VISIBLE);
            tiempoTexto.setVisibility(View.VISIBLE);
            resetCounter();
            startMovingButton();
        }
    }

    private void startMovingButton() {
        CountDownTimer crono = new CountDownTimer(tiempo * 1000, 1000) {
            @Override
            public void onFinish() {
                topo.setVisibility(View.INVISIBLE);
                topoaturdido.setVisibility(View.INVISIBLE);
                tiempoTexto.setVisibility(View.INVISIBLE);
                tiempo = 30;
                play.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                tiempoTexto.setText(String.valueOf(tiempo--));
                topo.setVisibility(View.INVISIBLE);
                topoaturdido.setVisibility(View.INVISIBLE);
                mueveTopo();
                topo.setVisibility(View.VISIBLE);
            }
        };
        crono.start();
    }

    private void mueveTopo() {
        int numeroRamdom = (int) (Math.random() * 12);
        toperas.get(numeroRamdom).getLocationOnScreen(posicionTopo);
        topo.setX((float) posicionTopo[0]);
        topo.setY((float) posicionTopo[1]);
    }

    private void resetCounter() {
        numero.setText("0");
    }
}

