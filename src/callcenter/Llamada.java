/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package callcenter;

/**
 *
 * @author Usuario
 */
class Llamada implements Runnable {
    private boolean enLinea;
    private Telefono operario;
    private Persona usuario;
    private String estado;

    public void run() {
        try {
            while (enLinea) {
                Thread.sleep(2000); // Pausa de 2 segundos
                if(enLinea){
                    System.out.println("La llamada de " + usuario.getNombre() + " esta en " + estado);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("La llamada de " + usuario.getNombre() + " ha sido cortada");
            e.printStackTrace();
        }
    }

    public void colgar() {
        System.out.println("La llamada de " + usuario.getNombre() + " ha finalizado");
        this.enLinea = false;
        this.operario = null;
    }

    public void enEspera() {
        System.out.println("La llamada de " + usuario.getNombre() + " ha sido puesta en espera");
        this.estado = "Espera";
        this.operario = null;
    }

    public void contestar(Telefono operario) {
        this.operario = operario;
        this.estado = "En curso";
        System.out.println("La llamada de " + usuario.getNombre() + " ha sido redirigida a " + operario.getEmpleado());
    }

    public Llamada(Persona usuario) {
        System.out.println("Llamada de " + usuario.getNombre() + " recibida");
        this.enLinea = true;
        this.usuario = usuario;
        this.enEspera();
        new Thread(this).start(); // Iniciar el hilo para ejecutar run()
    }

    public Llamada(Persona usuario, Telefono operario) {
        System.out.println("Llamada de " + usuario.getNombre() + " recibida");
        this.enLinea = true;
        this.usuario = usuario;
        this.contestar(operario);
        new Thread(this).start(); // Iniciar el hilo para ejecutar run()
    }

    public Telefono getOperario() {
        return operario;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public boolean isEnLinea() {
        return enLinea;
    }

    public String getEstado() {
        return estado;
    }
}
