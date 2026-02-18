package com.example.ejercicio3;
// Nixon Bolivar Cruz Hidalgo
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExecuteSQLActivity extends AppCompatActivity {

    private EditText campoNombreBaseDatos;
    private EditText campoSQL;
    private TextView vistaResultado;
    private DatabaseHelper baseDatosActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_sql);

        campoNombreBaseDatos = findViewById(R.id.etDatabaseName);
        campoSQL = findViewById(R.id.etSQL);
        vistaResultado = findViewById(R.id.tvResult);

        Button botonCrearBD = findViewById(R.id.btnCreateDB);
        Button botonEjecutarSQL = findViewById(R.id.btnExecuteSQL);
        Button botonVolver = findViewById(R.id.btnBack);

        botonCrearBD.setOnClickListener(v -> crearBaseDatos());
        botonEjecutarSQL.setOnClickListener(v -> ejecutarSQL());
        botonVolver.setOnClickListener(v -> finish());
    }

    private void crearBaseDatos() {
        String nombreBD = campoNombreBaseDatos.getText().toString().trim();

        if (nombreBD.isEmpty()) {
            mostrarMensaje("Por favor, ingresa un nombre para la BD");
            return;
        }

        try {
            baseDatosActual = new DatabaseHelper(this, nombreBD);
            baseDatosActual.getReadableDatabase();
            mostrarMensaje("Base de datos '" + nombreBD + "' creada correctamente");
        } catch (Exception e) {
            mostrarMensaje("Error al crear BD: " + e.getMessage());
        }
    }

    private void ejecutarSQL() {
        if (baseDatosActual == null) {
            mostrarMensaje("Primero debes crear una base de datos");
            return;
        }

        String sentenciaSQL = campoSQL.getText().toString().trim();

        if (sentenciaSQL.isEmpty()) {
            mostrarMensaje("Por favor, ingresa una sentencia SQL");
            return;
        }

        String resultado = baseDatosActual.ejecutarSQL(sentenciaSQL);
        mostrarMensaje(resultado);
        campoSQL.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        vistaResultado.setText(mensaje);
    }
}
