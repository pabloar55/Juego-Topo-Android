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
        toperas = new ArrayList<>();
        int[] id= {R.id.topera1,R.id.topera2, R.id.topera3, R.id.topera4,
                R.id.topera5,R.id.topera6, R.id.topera7,R.id.topera8,R.id.topera9,
                R.id.topera10,R.id.topera11, R.id.topera12 };
        for (int i = 0 ; i<12; i++){
           toperas.add(findViewById(id[i]));
        }
        topo = findViewById(R.id.topo);
        topoaturdido = findViewById(R.id.topoaturdido);
        topo.setVisibility(View.INVISIBLE);
        topoaturdido.setVisibility(View.INVISIBLE);
        numero = findViewById(R.id.numero);
        topo.setOnClickListener(this);
        tiempoTexto = findViewById(R.id.tiempo);
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

