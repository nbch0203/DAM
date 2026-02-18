package com.example.ejercicio3;
// Nixon Bolivar Cruz Hidalgo
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonEjecutarSQL = findViewById(R.id.btnExecuteSQL);
        Button botonConsultar = findViewById(R.id.btnQuery);
        Button botonSalir = findViewById(R.id.btnExit);

        botonEjecutarSQL.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ExecuteSQLActivity.class));
        });

        botonConsultar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, QueryActivity.class));
        });

        botonSalir.setOnClickListener(v -> {
            finish();
        });
    }

}