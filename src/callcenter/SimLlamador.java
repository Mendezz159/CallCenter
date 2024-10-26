/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package callcenter;


import java.util.Arrays;
import java.util.Collections;

public class SimLlamador implements Runnable {
    private String[] numeros;
    private boolean llamando;
    private int index;
    private Datos acceso;

    public SimLlamador() {
        // Declaración de números de teléfono con el formato 3xx
        this.numeros = new String[]{
            "3001234567", "3019876543", "3026543210", "3031122334", "3044433221", 
            "3056789012", "3067890123", "3078901234", "3089012345", "3090123456"
        };

        // Mezcla los números para llamar aleatoriamente
        Collections.shuffle(Arrays.asList(numeros));
        this.llamando = true;
        this.index = 0;
    }

    @Override
    public void run() {
        while (llamando && index < numeros.length) {
            String numero = numeros[index++];
            hacerLlamada(numero);
            try {
                Thread.sleep(10000); // Espera 10 segundos entre llamadas
            } catch (InterruptedException e) {
                System.out.println("Llamadas interrumpidas.");
                return;
            }
        }
        System.out.println("Se han llamado todos los números.");
        llamando = false; // Detenemos el proceso una vez que se han llamado todos los números
    }

    // Método para simular la llamada
    private void hacerLlamada(String numero) {
        Datos.nuevaLlamada(numero);
    }

    // Método para detener el llamador si es necesario
    public void detenerLlamador() {
        llamando = false;
    }
}
