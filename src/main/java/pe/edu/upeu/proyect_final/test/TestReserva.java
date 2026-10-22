package pe.edu.upeu.proyect_final.test;

import pe.edu.upeu.proyect_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.interfaces.IReservaDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class TestReserva {
    static IReservaDao rl = new ReservaDaoImpl();

    public static void incertar() {
        Reserva r = new Reserva();
        Cliente c = new Cliente();
        Habitaciones h = new Habitaciones();

        r.setFecha_reserva(LocalDate.now());
        r.setFecha_ingreso(LocalDate.parse("2026-11-07"));
        r.setFecha_salida(LocalDate.parse("2026-11-10"));
        r.setEstado("Activa");
        r.setTotal(160.00);

        c.setIdcliente(1);
        r.setCliente(c);

        h.setIdhabitacion(1);
        r.setHabitacion(h);

        boolean result = rl.insert(r);
        if (result) {
            System.out.println("Reserva Succes");
        } else {
            System.out.println("reserva error");
        }
    }
    public static void Listar(){
        List<Reserva> list = rl.rlistAll();
        if (list !=null && !list.isEmpty()){
            for (Reserva res : list){
                System.out.println("ID_RESERVA: " +res.getIdreserva());
                System.out.println("Fecha_Ingreso: " +res.getFecha_ingreso());
                System.out.println("Fecha_Salida: " +res.getFecha_salida());
                System.out.println("HAB_NUM: " +res.getHabitacion().getNumero());
                System.out.println("PRECIO: " +res.getHabitacion().getPrecio());
                System.out.println("CLIENTE: " +res.getCliente().getNombre());
                System.out.println("TOTAL: S/ " +res.getTotal());
                System.out.println("ESTADO: " +res.getEstado());
                System.out.println("------------------------------------------");
            }
        }else{
            System.out.println("no hay registros");
        }
    }
    public static void updateStatus(){
        Reserva r = new Reserva();

        r.setEstado("O");
        r.setIdreserva(1);

        boolean result = rl.updateStatus(r);
        if (result){
            System.out.println("Estatus update succes");
        }else{
            System.out.println("Estatus update error");

        }
    }

    public static void main(String[] args) {
        //incertar();
        Listar();
        System.out.println("*************************");
        updateStatus();
        System.out.println("*************************");
        Listar();

    }
}
