package com.example.ejercicio3;
// Nixon Bolivar Cruz Hidalgo
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends AppCompatActivity {

    private EditText campoNombreBaseDatos;
    private EditText campoConsulta;
    private TextView vistaResultados;
    private DatabaseHelper baseDatosActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        campoNombreBaseDatos = findViewById(R.id.etDatabaseName);
        campoConsulta = findViewById(R.id.etQuery);
        vistaResultados = findViewById(R.id.tvResults);

        Button botonSeleccionarBD = findViewById(R.id.btnSelectDB);
        Button botonEjecutarConsulta = findViewById(R.id.btnExecuteQuery);
        Button botonVolver = findViewById(R.id.btnBack);

        botonSeleccionarBD.setOnClickListener(v -> seleccionarBaseDatos());
        botonEjecutarConsulta.setOnClickListener(v -> ejecutarConsulta());
        botonVolver.setOnClickListener(v -> finish());
    }

    private void seleccionarBaseDatos() {
        String nombreBD = campoNombreBaseDatos.getText().toString().trim();

        if (nombreBD.isEmpty()) {
            mostrarResultados("Por favor, ingresa el nombre de la BD");
            return;
        }

        try {
            baseDatosActual = new DatabaseHelper(this, nombreBD);
            baseDatosActual.getReadableDatabase();
            mostrarResultados("Base de datos '" + nombreBD + "' seleccionada correctamente");
        } catch (Exception e) {
            mostrarResultados("Error al seleccionar BD: " + e.getMessage());
        }
    }

    private void ejecutarConsulta() {
        if (baseDatosActual == null) {
            mostrarResultados("Primero debes seleccionar una base de datos");
            return;
        }

        String consulta = campoConsulta.getText().toString().trim();

        if (consulta.isEmpty()) {
            mostrarResultados("Por favor, ingresa una consulta SQL");
            return;
        }

        try {
            String resultado = baseDatosActual.ejecutarConsulta(consulta);
            mostrarResultados(resultado);
            campoConsulta.setText("");
        } catch (Exception e) {
            mostrarResultados("Error al ejecutar consulta: " + e.getMessage());
        }
    }

    private void mostrarResultados(String mensaje) {
        vistaResultados.setText(mensaje);
    }
}
