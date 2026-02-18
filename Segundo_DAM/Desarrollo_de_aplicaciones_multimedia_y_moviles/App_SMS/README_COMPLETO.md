# App SMS - Resumen Completo

## 📱 Descripción General
Aplicación Android educativa que incluye dos proyectos en un solo APK:
- **Proyecto 1**: Enviar SMS
- **Proyecto 2**: Recibir SMS (con Toast de notificación)

## 🏗️ Arquitectura del Proyecto

```
App_SMS/
├── MainActivity.java (Menú principal)
│   ├── Botón → Proyecto 1: SendSmsActivity
│   └── Botón → Proyecto 2: SmsReceiverActivity
│
├── Proyecto 1: ENVIAR SMS
│   ├── SendSmsActivity.java
│   ├── activity_send_sms.xml
│   └── Funcionalidades:
│       ├── Campo de número telefónico
│       ├── Campo de mensaje
│       ├── Validación de campos
│       ├── Permisos en tiempo de ejecución (SEND_SMS)
│       └── Envío mediante SmsManager
│
├── Proyecto 2: RECIBIR SMS
│   ├── SmsReceiverActivity.java (Interfaz)
│   ├── SmsReceiver.java (BroadcastReceiver)
│   ├── activity_sms_receiver.xml
│   └── Funcionalidades:
│       ├── Detección de SMS entrantes
│       ├── Extracción de datos (número, contenido)
│       ├── Toast de notificación automática
│       └── Permiso RECEIVE_SMS
│
└── AndroidManifest.xml
    ├── Permisos: SEND_SMS, RECEIVE_SMS, READ_PHONE_STATE
    ├── Activities: MainActivity, SendSmsActivity, SmsReceiverActivity
    └── BroadcastReceiver: SmsReceiver
```

## 📋 Permisos Configurados

```xml
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

## 🎯 Proyecto 1: Enviar SMS

### Componentes
- **Activity**: `SendSmsActivity`
- **Layout**: `activity_send_sms.xml`
- **API**: `SmsManager`

### Flujo
```
1. Usuario abre Proyecto 1
2. Ingresa número telefónico
3. Ingresa mensaje
4. Presiona "Enviar SMS"
5. Sistema solicita permiso si es necesario
6. Se envía SMS mediante SmsManager
7. Toast muestra resultado
8. Campos se limpian automáticamente
```

### Validaciones
✅ Campo de número no vacío
✅ Campo de mensaje no vacío
✅ Permiso SEND_SMS en tiempo de ejecución
✅ Manejo de excepciones

---

## 🎯 Proyecto 2: Recibir SMS

### Componentes
- **Activity**: `SmsReceiverActivity`
- **BroadcastReceiver**: `SmsReceiver`
- **Layout**: `activity_sms_receiver.xml`
- **Intent Filter**: `android.provider.Telephony.SMS_RECEIVED`

### Flujo
```
1. Usuario abre Proyecto 2
2. Aplicación se prepara para recibir SMS
3. Sistema operativo recibe SMS
4. Broadcast SMS_RECEIVED se dispara
5. SmsReceiver.onReceive() se ejecuta
6. Se extrae: número origen + contenido
7. Toast muestra: "SMS recibido de: [número]\nMensaje: [contenido]"
```

### Características
✅ Recepción automática en background
✅ Extracción de número origin
✅ Extracción de contenido del mensaje
✅ Toast con información completa
✅ Compatible API 23+

---

## 🔧 Configuración Técnica

### Versiones
- **Lenguaje**: Java 11
- **Gradle**: Kotlin DSL (build.gradle.kts)
- **API mínima**: 23 (Android 6.0)
- **API objetivo**: 36 (Android 15)

### Dependencias
```gradle
- androidx.appcompat:appcompat
- androidx.material:material
- androidx.activity:activity
- androidx.constraintlayout:constraintlayout
```

---

## 🧪 Testing en Emulador

### Proyecto 1: Enviar SMS
1. Abre la app → Proyecto 1
2. Ingresa número: `5551234567`
3. Escribe un mensaje
4. Presiona "Enviar SMS"
5. Verifica en emulador → Extended Controls → Telephone → SMS

### Proyecto 2: Recibir SMS
1. Abre la app → Proyecto 2
2. Mantén la pantalla abierta
3. En otra ventana del emulador: Extended Controls → Telephone → SMS
4. Envía SMS desde `+5551234567`
5. Toast aparecerá automáticamente

---

## 📁 Estructura de Archivos

```
app/src/main/
├── java/com/example/app_sms/
│   ├── MainActivity.java ..................... Menú principal
│   ├── SendSmsActivity.java ................. Envío de SMS
│   ├── SmsReceiverActivity.java ............. Interfaz receptor
│   └── SmsReceiver.java ..................... BroadcastReceiver
│
├── res/layout/
│   ├── activity_main.xml .................... Menú con dos botones
│   ├── activity_send_sms.xml ................ Formulario envío
│   └── activity_sms_receiver.xml ............ Interfaz receptor
│
├── AndroidManifest.xml ....................... Configuración app
│
└── ... (recursos, drawable, values)
```

---

## ✅ Lista de Verificación

- ✅ Todo código en **JAVA** (no Kotlin)
- ✅ Dos proyectos integrados en un APK
- ✅ Menú de selección en MainActivity
- ✅ Permisos configurados correctamente
- ✅ BroadcastReceiver registrado
- ✅ Validaciones de entrada
- ✅ Manejo de permisos en tiempo de ejecución
- ✅ Toast notifications funcionando
- ✅ Compatible con API 23+
- ✅ Sin errores de compilación

---

## 🚀 Próximos Pasos Opcionales

1. **Historial de SMS**: Guardar SMS recibidos en lista
2. **Base de datos**: Room para persistencia
3. **Notificaciones**: Push notifications en lugar de Toast
4. **Interfaz mejorada**: RecyclerView para mostrar histórico
5. **Filtrado**: Permitir solo SMS de números específicos

---

**Última actualización**: 2026-02-09
**Estado**: ✅ Completado y funcional
