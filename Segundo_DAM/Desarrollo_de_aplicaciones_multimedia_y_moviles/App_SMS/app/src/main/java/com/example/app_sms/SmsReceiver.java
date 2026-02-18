package com.example.app_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

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

                        // Mostrar Toast con información del SMS
                        String mensaje = "SMS recibido de: " + senderNumber + "\nMensaje: " + messageBody;
                        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();

                        // Abortar el broadcast para evitar que llegue a otras apps
                        abortBroadcast();
                    }
                }
            }
        }
    }
}
