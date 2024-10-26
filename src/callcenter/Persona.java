/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package callcenter;

/**
 *
 * @author Usuario
 */
public class Persona {
    
    private String nombre;
    private String numero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public Persona(String numero) {
        this.numero = numero;
    }
    
    
}
