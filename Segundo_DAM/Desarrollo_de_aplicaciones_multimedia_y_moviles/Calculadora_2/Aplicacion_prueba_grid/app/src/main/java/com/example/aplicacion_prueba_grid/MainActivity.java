package com.example.aplicacion_prueba_grid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText texto1;
    private Button numero1, numero2, numero3, numero4, numero5, numero6, numero7, numero8, numero9;
    private Button sumar, restar, resolver, borrar, cambiar_color, resetear_color;
    private int valor_actual, valor_anterior;
    private String operacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        texto1 = findViewById(R.id.texto1);
        numero1 = findViewById(R.id.boton_1);
        numero2 = findViewById(R.id.boton_2);
        numero3 = findViewById(R.id.boton_3);
        numero4 = findViewById(R.id.boton_4);
        numero5 = findViewById(R.id.boton_5);
        numero6 = findViewById(R.id.boton_6);
        numero7 = findViewById(R.id.boton_7);
        numero8 = findViewById(R.id.boton_8);
        numero9 = findViewById(R.id.boton_9);
        sumar = findViewById(R.id.boton_sumar);
        restar = findViewById(R.id.boton_restar);
        resolver = findViewById(R.id.boton_resolver);
        borrar = findViewById(R.id.boton_borrar);
        cambiar_color = findViewById(R.id.boton_cambiar_color);
        resetear_color = findViewById(R.id.boton_reset_color);

        resetea_colores(numero1);
        resetea_colores(numero2);
        resetea_colores(numero3);
        resetea_colores(numero4);
        resetea_colores(numero5);
        resetea_colores(numero6);
        resetea_colores(numero7);
        resetea_colores(numero8);
        resetea_colores(numero9);
        resetea_colores(cambiar_color);
        resetea_colores(borrar);
        resetea_colores(resetear_color);
        resetea_colores(restar);
        resetea_colores(resolver);
        resetea_colores(sumar);







        // Configurar listeners para botones numéricos
        configurarBotonNumerico(numero1, "1");
        configurarBotonNumerico(numero2, "2");
        configurarBotonNumerico(numero3, "3");
        configurarBotonNumerico(numero4, "4");
        configurarBotonNumerico(numero5, "5");
        configurarBotonNumerico(numero6, "6");
        configurarBotonNumerico(numero7, "7");
        configurarBotonNumerico(numero8, "8");
        configurarBotonNumerico(numero9, "9");

        // Botón sumar
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoActual = texto1.getText().toString();
                if (!textoActual.isEmpty()) {
                    valor_anterior = Integer.parseInt(textoActual);
                    operacion = "+";
                    texto1.setText("");
                }
            }
        });

        // Botón restar
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoActual = texto1.getText().toString();
                if (!textoActual.isEmpty()) {
                    valor_anterior = Integer.parseInt(textoActual);
                    operacion = "-";
                    texto1.setText("");
                }
            }
        });

        // Botón resolver
        resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = texto1.getText().toString();
                if (!textoActual.isEmpty() && !operacion.isEmpty()) {
                    valor_actual = Integer.parseInt(textoActual);
                    int resultado = 0;

                    switch (operacion) {
                        case "+":
                            resultado = valor_anterior + valor_actual;
                            break;
                        case "-":
                            resultado = valor_anterior - valor_actual;
                            break;
                    }

                    texto1.setText(String.valueOf(resultado));
                    operacion = "";
                }
            }
        });

        // Botón borrar
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto1.setText("");
                valor_actual = 0;
                valor_anterior = 0;
                operacion = "";
            }
        });

        // Botón cambiar color
        cambiar_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               numero1.setBackgroundColor(Color.RED);
                numero2.setBackgroundColor(Color.RED);
                numero3.setBackgroundColor(Color.RED);
                numero4.setBackgroundColor(Color.RED);
                numero5.setBackgroundColor(Color.RED);
                numero6.setBackgroundColor(Color.RED);
                numero7.setBackgroundColor(Color.RED);
                numero8.setBackgroundColor(Color.RED);
                numero9.setBackgroundColor(Color.RED);


            }
        });

        // Botón resetear color
        resetear_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetea_colores(numero1);
                resetea_colores(numero2);
                resetea_colores(numero3);
                resetea_colores(numero4);
                resetea_colores(numero5);
                resetea_colores(numero6);
                resetea_colores(numero7);
                resetea_colores(numero8);
                resetea_colores(numero9);

            }
        });




    }

    // Metodo auxiliar para configurar botones numéricos
    private void configurarBotonNumerico(Button boton, final String numero) {
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoActual = texto1.getText().toString();
                texto1.setText(textoActual + numero);
            }
        });
    }


    private void resetea_colores(Button boton){
        boton.setBackgroundColor(Color.WHITE);
    }
}