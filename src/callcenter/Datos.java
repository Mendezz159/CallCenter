/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package callcenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Usuario
 */
public class Datos {
    private static Telefono[] operarios;
    private static ArrayList<Persona> usuarios;
    private static Queue<Llamada> llamadasPendientes;
    private static Llamada[] llamadasEnAtencion;

    // Creación de este ejemplo concreto
    public Datos() {
        this.operarios = new Telefono[3];
        this.operarios[0] = new Telefono("Juan");
        this.operarios[1] = new Telefono("Carlos");
        this.operarios[2] = new Telefono("Bodoque");
        this.usuarios = new ArrayList<>();
        this.llamadasPendientes = new LinkedList<>();
        this.llamadasEnAtencion = new Llamada[3]; // Tres espacios para llamadas en atención
        
        Persona persona1 = new Persona("3001234567");
        persona1.setNombre("Andres");
        usuarios.add(persona1);

        Persona persona2 = new Persona("3019876543");
        persona2.setNombre("Beatriz");
        usuarios.add(persona2);

        Persona persona3 = new Persona("3026543210");
        persona3.setNombre("Camila");
        usuarios.add(persona3);

        Persona persona4 = new Persona("3031122334");
        persona4.setNombre("David");
        usuarios.add(persona4);

        Persona persona5 = new Persona("3044433221");
        persona5.setNombre("Elena");
        usuarios.add(persona5);
    }

    public static Telefono[] getOperarios() {
        return operarios;
    }

    public static ArrayList<Persona> getUsuarios() {
        return usuarios;
    }

    public static Llamada[] getLlamadasEnAtencion() {
        return llamadasEnAtencion;
    }

    // Método para agregar un usuario a través de su número de teléfono
    public static boolean agregarUsuario(String numero) {
        if (!esNumeroValido(numero)) {
            System.out.println("El número de telefono no es válido.");
            interfaz.ventana.refrescar();
            return false;
        }
        if (numeroExiste(numero)) {
            System.out.println("El número de telefono ya existe.");
            interfaz.ventana.refrescar();
            return false;
        }

        String nombre = generarNombreUnico();
        Persona nuevaPersona = new Persona(numero);
        nuevaPersona.setNombre(nombre);
        usuarios.add(nuevaPersona);
        ordenarUsuariosPorNumero();
        System.out.println("Usuario agregado: " + nombre + " con numero " + numero);
        interfaz.ventana.refrescar();
        return true;
    }

    // Método para verificar si un nombre contiene solo dígitos
    private static boolean esNombreValido(String nombre) {
        return !nombre.matches("\\d+"); // Retorna falso si el nombre contiene solo números
    }

    // Método para generar un nombre único en formato "UnknowXXXXX"
    private static String generarNombreUnico() {
        String nombre;
        do {
            nombre = "Unknow" + (int)(Math.random() * 100000);
        } while (nombreExiste(nombre));
        return nombre;
    }

    // Método para verificar si un número de teléfono contiene solo dígitos
    private static boolean esNumeroValido(String numero) {
        return numero.matches("\\d+"); // Retorna verdadero si el número contiene solo dígitos
    }

    // Método para verificar si un nombre ya existe en la lista de usuarios
    private static boolean nombreExiste(String nombre) {
        for (Persona persona : usuarios) {
            if (persona.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si un número de teléfono ya existe utilizando búsqueda binaria
    private static boolean numeroExiste(String numero) {
        return buscarIndicePorNumero(numero) >= 0;
    }

    // Método para buscar el índice de una persona por número de teléfono usando búsqueda binaria
    private static int buscarIndicePorNumero(String numero) {
        int inicio = 0;
        int fin = usuarios.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            String numeroActual = usuarios.get(medio).getNumero();
            int comparacion = numeroActual.compareTo(numero);
            if (comparacion == 0) {
                return medio; // Número encontrado
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1; // Número no encontrado
    }

    // Método para editar el nombre de una persona
    public static boolean editarNombrePersona(String antiguoNombre, String nuevoNombre) {
        if (!esNombreValido(nuevoNombre)) {
            System.out.println("El nuevo nombre no puede ser solo numeros.");
            interfaz.ventana.refrescar();
            return false;
        }
        if (nombreExiste(nuevoNombre)) {
            System.out.println("El nombre " + nuevoNombre + " ya existe.");
            interfaz.ventana.refrescar();
            return false;
        }
        for (Persona persona : usuarios) {
            if (persona.getNombre().equals(antiguoNombre)) {
                persona.setNombre(nuevoNombre);
                System.out.println("Nombre actualizado a: " + nuevoNombre);
                interfaz.ventana.refrescar();
                return true;
            }
        }
        System.out.println("No se encontro a la persona con el nombre: " + antiguoNombre);
        interfaz.ventana.refrescar();
        return false;
    }

    // Método para eliminar una persona por nombre o número
    public static boolean eliminarPersona(String identificador) {
        if (esNumeroValido(identificador)) {
            // Usar búsqueda binaria para encontrar la persona si el identificador es un número
            int indice = buscarIndicePorNumero(identificador);
            if (indice >= 0) {
                Persona persona = usuarios.remove(indice);
                System.out.println("Persona eliminada: " + persona.getNombre());
                interfaz.ventana.refrescar();
                return true;
            }
        } else {
            // Buscar por nombre si el identificador no es un número
            for (int i = 0; i < usuarios.size(); i++) {
                Persona persona = usuarios.get(i);
                if (persona.getNombre().equals(identificador)) {
                    usuarios.remove(i);
                    System.out.println("Persona eliminada: " + persona.getNombre());
                    interfaz.ventana.refrescar();
                    return true;
                }
            }
        }
        System.out.println("No se encontro a la persona con el identificador: " + identificador);
        interfaz.ventana.refrescar();
        return false;
    }

    // Método para ordenar la lista de usuarios por número de teléfono
    private static void ordenarUsuariosPorNumero() {
        Collections.sort(usuarios, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return p1.getNumero().compareTo(p2.getNumero());
            }
        });
    }

    // Mostrar los usuarios registrados
    public static void mostrarUsuarios() {
        System.out.println("Usuarios registrados:");
        for (Persona persona : usuarios) {
            System.out.println("- Nombre: " + persona.getNombre() + ", Numero: " + persona.getNumero());
        }
    }
    
    public static String cantidadEnCola() {
        int cantidadEnCola = llamadasPendientes.size();
        return String.valueOf(cantidadEnCola);
    }

    // Método para manejar una nueva llamada
    public static void nuevaLlamada(String numero) {
        Persona persona;
        int indicePersona = buscarIndicePorNumero(numero);
        if (indicePersona >= 0) {
            persona = usuarios.get(indicePersona);
        } else {
            agregarUsuario(numero);
            persona = usuarios.get(buscarIndicePorNumero(numero));
        }

        // Buscar un teléfono disponible
        for (int i = 0; i < operarios.length; i++) {
            if (operarios[i].Disponible) {
                for (int j = 0; j < llamadasEnAtencion.length; j++) {
                    if (llamadasEnAtencion[j] == null) {
                        Llamada llamada = new Llamada(persona, operarios[i]);
                        llamadasEnAtencion[j] = llamada;
                        operarios[i].Disponible = false;
                        System.out.println("Llamada en curso con " + operarios[i].getEmpleado());
                        interfaz.ventana.refrescar();
                        return;
                    }
                }
            }
        }

        // Si no hay teléfonos disponibles, agregar la llamada a la cola de pendientes
        Llamada llamadaPendiente = new Llamada(persona);
        llamadasPendientes.add(llamadaPendiente);
        System.out.println("Llamada en espera: " + persona.getNombre());
        interfaz.ventana.refrescar();
    }

    // Método para colgar una llamada
    public static void colgarLlamada(Telefono telefono) {
        for (int i = 0; i < llamadasEnAtencion.length; i++) {
            Llamada llamada = llamadasEnAtencion[i];
            if (llamada != null && llamada.getOperario() == telefono) {
                llamada.colgar();
                llamadasEnAtencion[i] = null;
                telefono.Disponible = true;
                redireccionarLlamadas();
                interfaz.ventana.refrescar();
                return;
            }
        }
        System.out.println("No se encontro una llamada asociada al telefono " + telefono.getEmpleado());
        interfaz.ventana.refrescar();
    }

    // Método para redireccionar llamadas en la cola
    private static void redireccionarLlamadas() {
        if (llamadasPendientes.isEmpty()) {
            return;
        }
        for (int i = 0; i < operarios.length; i++) {
            if (operarios[i].Disponible) {
                for (int j = 0; j < llamadasEnAtencion.length; j++) {
                    if (llamadasEnAtencion[j] == null) {
                        Llamada llamada = llamadasPendientes.poll();
                        if (llamada != null) {
                            llamada.contestar(operarios[i]);
                            llamadasEnAtencion[j] = llamada;
                            operarios[i].Disponible = false;
                            System.out.println("Llamada redirigida a " + operarios[i].getEmpleado());
                        }
                        return;
                    }
                }
            }
        }
    }
}

