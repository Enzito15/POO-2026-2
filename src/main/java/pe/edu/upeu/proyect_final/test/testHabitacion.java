package pe.edu.upeu.proyect_final.test;

import pe.edu.upeu.proyect_final.implementacion.ClienteDaoImpl;
import pe.edu.upeu.proyect_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IClienteDao;
import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;

import java.util.List;

public class testHabitacion {
    static IHabitacionDao hl = new HabitacionDaoImpl();
    public static void listar() {
        List<Habitaciones> lista = hl.listAll();
        if (lista != null && !lista.isEmpty()) {
            for (Habitaciones habitaciones : lista) {
                System.out.println("Id_Habitacion: " + habitaciones.getIdhabitacion());
                System.out.println("Numero: " + habitaciones.getNumero());
                System.out.println("numeroPiso: " + habitaciones.getNumeroPiso());
                System.out.println("numeroHabitacion: " + habitaciones.getNumeroHabitacion());
                System.out.println("Tipo: " + habitaciones.getTipo());
                System.out.println("precio: " + habitaciones.getPrecio());
                System.out.println("estado: " + habitaciones.getEstado());

            }
        } else {
            System.out.println("No hay registros");

        }
    }
    public static void incertar() {
        Habitaciones h = new Habitaciones();
        h.setNumero(2);
        h.setNumeroHabitacion(100);
        h.setNumeroPiso(1);
        h.setTipo("vip");
        h.setPrecio(200.0);
        h.setEstado("Disponible");

        boolean result = hl.insert(h);
        if (result) {
            System.out.println("incert correcto");
        } else {
            System.out.println("incert incorrecto");
        }
    }

    public static void main(String[] args) {
        System.out.println("*************************");
        incertar();
        System.out.println("*************************");
        listar();
    }
}
