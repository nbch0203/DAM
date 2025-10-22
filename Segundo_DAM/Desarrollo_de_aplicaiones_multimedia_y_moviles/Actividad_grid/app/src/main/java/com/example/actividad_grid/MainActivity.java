package com.example.actividad_grid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnEmpezar, btnLimpiar, btnRojo, btnVerde, btnAzul, btnMarron, btnAmarillo, btnNaranja, btnNegro, btnRosa;

    // Mapa para guardar los colores originales de los botones.
    private final Map<Integer, Integer> coloresOriginales = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            return insets;
        });

        btnEmpezar = findViewById(R.id.boton_empezar);
        btnLimpiar = findViewById(R.id.boton_limpiar);
        btnRojo = findViewById(R.id.boton_rojo);
        btnVerde = findViewById(R.id.boton_verde);
        btnAzul = findViewById(R.id.boton_azul);
        btnMarron = findViewById(R.id.boton_marron);
        btnAmarillo = findViewById(R.id.boton_amarillo);
        btnNaranja = findViewById(R.id.boton_naranja);
        btnNegro = findViewById(R.id.boton_negro);
        btnRosa = findViewById(R.id.boton_rosa);

        // Guardamos los colores originales directamente.
        guardarColoresOriginales();

        // 2. Listener para el botón empezar
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activarListenersDeColores();
            }
        });

        // 3. Listener para el botón limpiar
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurarColoresOriginales();
            }
        });
    }

    /**
     * Metodo que guarda el color de fondo original de cada botón de color.
     */
    private void guardarColoresOriginales() {
        // Usamos los códigos de color
        coloresOriginales.put(R.id.boton_rojo, Color.parseColor("#FF0000"));
        coloresOriginales.put(R.id.boton_verde, Color.parseColor("#4CAF50"));
        coloresOriginales.put(R.id.boton_naranja, Color.parseColor("#FF9800"));
        coloresOriginales.put(R.id.boton_azul, Color.parseColor("#2196F3"));
        coloresOriginales.put(R.id.boton_marron, Color.parseColor("#795548"));
        coloresOriginales.put(R.id.boton_negro, Color.parseColor("#000000"));
        coloresOriginales.put(R.id.boton_amarillo, Color.parseColor("#FFEB3B"));
        coloresOriginales.put(R.id.boton_rosa, Color.parseColor("#E91E63"));
    }

    /**
     * Metodo que restaura el color de fondo original de cada botón.
     */
    private void restaurarColoresOriginales() {
        btnRojo.setBackgroundColor(coloresOriginales.get(R.id.boton_rojo));
        btnVerde.setBackgroundColor(coloresOriginales.get(R.id.boton_verde));
        btnNaranja.setBackgroundColor(coloresOriginales.get(R.id.boton_naranja));
        btnAzul.setBackgroundColor(coloresOriginales.get(R.id.boton_azul));
        btnMarron.setBackgroundColor(coloresOriginales.get(R.id.boton_marron));
        btnAmarillo.setBackgroundColor(coloresOriginales.get(R.id.boton_amarillo));
        btnRosa.setBackgroundColor(coloresOriginales.get(R.id.boton_rosa));

        // Restauración del color de texto del botón Negro
        btnNegro.setBackgroundColor(coloresOriginales.get(R.id.boton_negro));
        btnNegro.setTextColor(Color.WHITE);
    }

    /**
     * Metodo que asigna un OnClickListener a cada botón de color.
     */
    private void activarListenersDeColores() {
        View.OnClickListener colorButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.setBackgroundColor(Color.WHITE);

                int idDelBotonPulsado = view.getId();

                if (idDelBotonPulsado == R.id.boton_negro) {

                    Button botonPulsado = (Button) view;

                    botonPulsado.setTextColor(Color.BLACK);
                }}
        };

        // Asignamos el listener a cada botón de color
        btnRojo.setOnClickListener(colorButtonListener);
        btnVerde.setOnClickListener(colorButtonListener);
        btnNaranja.setOnClickListener(colorButtonListener);
        btnAzul.setOnClickListener(colorButtonListener);
        btnMarron.setOnClickListener(colorButtonListener);
        btnNegro.setOnClickListener(colorButtonListener);
        btnAmarillo.setOnClickListener(colorButtonListener);
        btnRosa.setOnClickListener(colorButtonListener);
    }
}
