/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package callcenter;

/**
 *
 * @author Usuario
 */
public class Telefono {
    
    private String Empleado;
    public boolean Disponible;

    public Telefono(String Empleado) {
        this.Empleado = Empleado;
        this.Disponible = true;
    }

    public String getEmpleado() {
        return Empleado;
    }
    
    
}
