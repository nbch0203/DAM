package com.example.Ejercicio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/test")
public class TestConcurrenciaController {
	
    // ✅ SEGURO: Esta clase gestiona el bloqueo de hilos internamente
    private AtomicInteger contadorSeguro = new AtomicInteger(0);
    
    @GetMapping("/contar-seguro")
    public String contarBien() throws InterruptedException {
        // incrementAndGet() es una operación atómica (indivisible)
        int valorFinal = contadorSeguro.incrementAndGet();
        Thread.sleep(5000); 
        return "Visitante número (Seguro): " + valorFinal;
    }

	
	 // ⚠️ PELIGRO: Variable compartida por todos los clientes
    private int contadorInseguro = 0;

    @GetMapping("/contar-inseguro")
    public String contarMal() throws InterruptedException {
        // Leemos el valor
        int valorActual = contadorInseguro;
        
        // Simulamos un pequeño retardo en el procesamiento
        Thread.sleep(5000); 
        
        // Escribimos el valor
        contadorInseguro = valorActual + 1;
        
        return "Visitante número: " + contadorInseguro;
    }


    @GetMapping("/lento")
    public String simulacionProcesoPesado() throws InterruptedException {
        // 1. Identificamos QUÉ hilo está ejecutando esto
        String nombreHilo = Thread.currentThread().getName();
        long idHilo = Thread.currentThread().getId();

        System.out.println("🟢 INICIO - Hilo: " + nombreHilo + " (ID: " + idHilo + ")");

        // 2. Simulamos una tarea pesada (Dormimos el hilo 5 segundos)
        Thread.sleep(10000);

        System.out.println("🔴 FIN    - Hilo: " + nombreHilo + " (ID: " + idHilo + ")");

        return "Proceso terminado por: " + nombreHilo;
    }
}
