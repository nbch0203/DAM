package com.example.ejercicio2;
// Nixon Bolivar Cruz Hidalgo
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etDatabaseName;
    private EditText etSQL;
    private TextView tvResult;
    private DatabaseHelper currentDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDatabaseName = findViewById(R.id.etDatabaseName);
        etSQL = findViewById(R.id.etSQL);
        tvResult = findViewById(R.id.tvResult);

        Button btnCreate = findViewById(R.id.btnCreateDB);
        Button btnExecute = findViewById(R.id.btnExecuteSQL);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearBaseDatos();
            }
        });

        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarSQL();
            }
        });
    }

    private void crearBaseDatos() {
        String dbName = etDatabaseName.getText().toString().trim();

        if (dbName.isEmpty()) {
            mostrarMensaje("Por favor, ingresa un nombre para la BD");
            return;
        }

        try {
            currentDatabase = new DatabaseHelper(this, dbName);
            currentDatabase.getReadableDatabase();
            mostrarMensaje("Base de datos '" + dbName + "' creada correctamente");
        } catch (Exception e) {
            mostrarMensaje("Error al crear BD: " + e.getMessage());
        }
    }

    private void ejecutarSQL() {
        if (currentDatabase == null) {
            mostrarMensaje("Primero debes crear una base de datos");
            return;
        }

        String sql = etSQL.getText().toString().trim();

        if (sql.isEmpty()) {
            mostrarMensaje("Por favor, ingresa una sentencia SQL");
            return;
        }

        String resultado = currentDatabase.ejecutarSQL(sql);
        mostrarMensaje(resultado != null ? resultado : "Error desconocido");
        etSQL.getText().clear();
    }

    private void mostrarMensaje(String mensaje) {
        tvResult.setText(mensaje);
    }
}
