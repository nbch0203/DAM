package com.example.app_sms;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private TextView messagesTextView;
    private BroadcastReceiver smsReceiver;
    private StringBuilder messagesBuilder;

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

        // Inicializar vistas
        messagesTextView = findViewById(R.id.messagesTextView);
        messagesBuilder = new StringBuilder();

        // Solicitar permisos necesarios
        requestSmsPermissions();

        // Registrar BroadcastReceiver dinámico
        registerSmsReceiver();
    }

    private void requestSmsPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private void registerSmsReceiver() {
        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction() != null &&
                    intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                    Bundle bundle = intent.getExtras();

                    if (bundle != null) {
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        String format = bundle.getString("format");

                        if (pdus != null) {
                            for (Object pdu : pdus) {
                                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu, format);

                                String senderNumber = smsMessage.getOriginatingAddress();
                                String messageBody = smsMessage.getMessageBody();

                                // Actualizar el TextView con el mensaje recibido
                                updateMessagesDisplay(senderNumber, messageBody);

                                // Mostrar Toast
                                Toast.makeText(context, "SMS recibido de: " + senderNumber, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(smsReceiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(smsReceiver, filter);
        }
    }

    private void updateMessagesDisplay(String senderNumber, String messageBody) {
        // Si es el primer mensaje, limpiar el texto por defecto
        if (messagesBuilder.length() == 0) {
            messagesTextView.setText("");
        }

        // Agregar nuevo mensaje al StringBuilder
        if (messagesBuilder.length() > 0) {
            messagesBuilder.append("\n\n---\n\n");
        }
        messagesBuilder.append("De: ").append(senderNumber).append("\n");
        messagesBuilder.append("Mensaje: ").append(messageBody);

        // Actualizar el TextView
        messagesTextView.setText(messagesBuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desregistrar el receiver cuando se cierre la actividad
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso SMS concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso SMS denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
