# Proyecto 2: Receptor de SMS

## Descripción
Aplicación que detecta y muestra un mensaje Toast cuando llega un SMS al emulador Android.

## Componentes Implementados

### 1. **SmsReceiver.java** (BroadcastReceiver)
- **Propósito**: Detectar SMS entrantes
- **Funcionalidades**:
  - Escucha el broadcast `android.provider.Telephony.SMS_RECEIVED`
  - Extrae información del SMS (número origen, contenido)
  - Compatible con Android 6.0 (API 23) en adelante
  - Muestra Toast con los detalles del SMS recibido
  
**Flujo:**
```
SMS llega al emulador
    ↓
Sistema dispara broadcast SMS_RECEIVED
    ↓
SmsReceiver.onReceive() captura el evento
    ↓
Extrae PDU (Protocol Data Unit) del Bundle
    ↓
Convierte PDU a SmsMessage
    ↓
Obtiene número origen y contenido
    ↓
Muestra Toast: "SMS recibido de: [número]\nMensaje: [contenido]"
```

### 2. **SmsReceiverActivity.java**
- Activity principal para el Proyecto 2
- Proporciona interfaz informativa
- Contiene instrucciones para probar la recepción de SMS

### 3. **Permisos Requeridos** (AndroidManifest.xml)
```xml
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

### 4. **BroadcastReceiver Registrado** (AndroidManifest.xml)
```xml
<receiver
    android:name=".SmsReceiver"
    android:exported="true"
    android:permission="android.permission.RECEIVE_SMS">
    <intent-filter>
        <action android:name="android.provider.Telephony.SMS_RECEIVED" />
    </intent-filter>
</receiver>
```

## Cómo Probar

### En el Emulador:

1. **Inicia la app y selecciona "Proyecto 2 - Recibir SMS"**

2. **Abre Extended Controls del emulador:**
   - Android Studio → Emulator → Extended Controls
   - O: Presiona Ctrl+Shift+O (en el emulador)

3. **Envía un SMS de prueba:**
   - Selecciona "Telephone" en Extended Controls
   - Elige la pestaña "SMS"
   - Llena:
     - **From**: +5551234567 (o cualquier número)
     - **Message**: Tu mensaje de prueba
   - Presiona "Send Message"

4. **Resultado:**
   - Se mostrará un Toast automáticamente
   - El Toast dirá: "SMS recibido de: +5551234567\nMensaje: Tu mensaje de prueba"

## Consideraciones Técnicas

### Compatibilidad
- **API mínima**: 23 (Android 6.0)
- **API objetivo**: 36 (Android 15)
- Usa `Build.VERSION.SDK_INT` para compatibilidad con diferentes versiones

### Seguridad
- El BroadcastReceiver requiere permiso `android.permission.RECEIVE_SMS`
- Los permisos se validan en tiempo de ejecución

### Limitaciones
- En emulador: Solo funciona con SMS simulados desde Extended Controls
- En dispositivo real: Recibe SMS reales del operador
- El receptor funciona aunque la app esté cerrada (por ser BroadcastReceiver)

## Archivos Creados/Modificados

- ✅ `SmsReceiver.java` - BroadcastReceiver para detectar SMS
- ✅ `SmsReceiverActivity.java` - Activity de la interfaz
- ✅ `activity_sms_receiver.xml` - Layout de la interfaz
- ✅ `AndroidManifest.xml` - Permisos y registro del receiver
- ✅ `MainActivity.java` - Actualizado con navegación

## Código Ejemplo - SmsReceiver.java

```java
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format");
                
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            smsMessage = SmsMessage.createFromPdu((byte[]) pdu, format);
                        } else {
                            smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        }
                        
                        String mensaje = "SMS recibido de: " + 
                                        smsMessage.getOriginatingAddress() + 
                                        "\nMensaje: " + 
                                        smsMessage.getMessageBody();
                        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
```

---

**Lenguaje:** Java 11
**Framework:** Android / AndroidX
