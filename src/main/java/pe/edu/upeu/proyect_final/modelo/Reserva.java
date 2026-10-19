package pe.edu.upeu.proyect_final.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private final IntegerProperty idreserva = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> fecha_reserva = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fecha_ingreso = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fecha_salida = new SimpleObjectProperty<>();
    private final StringProperty estado = new SimpleStringProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();
    //asociacion de las llaves foraneas

    private final ObjectProperty<Cliente> cliente = new SimpleObjectProperty<>();
    private final ObjectProperty<Habitaciones> habitacion = new SimpleObjectProperty<>();

    public Reserva() {
    }

    public final Habitaciones getHabitacion() {
        return habitacion.get();
    }

    public final void setHabitacion(Habitaciones value) {
        habitacion.set(value);
    }

    public final ObjectProperty<Habitaciones> habitacionProperty() {
        return habitacion;
    }


    public final Cliente getCliente() {
        return cliente.get();
    }

    public final void setCliente(Cliente value) {
        cliente.set(value);
    }

    public final ObjectProperty<Cliente> clienteProperty() {
        return cliente;
    }


    public final double getTotal() {
        return total.get();
    }

    public final void setTotal(double value) {
        total.set(value);
    }

    public final DoubleProperty totalProperty() {
        return total;
    }


    public final String getEstado() {
        return estado.get();
    }

    public final void setEstado(String value) {
        estado.set(value);
    }

    public final StringProperty estadoProperty() {
        return estado;
    }


    public final LocalDate getFecha_salida() {
        return fecha_salida.get();
    }

    public final void setFecha_salida(LocalDate value) {
        fecha_salida.set(value);
    }

    public final ObjectProperty<LocalDate> fecha_salidaProperty() {
        return fecha_salida;
    }


    public final LocalDate getFecha_ingreso() {
        return fecha_ingreso.get();
    }

    public final void setFecha_ingreso(LocalDate value) {
        fecha_ingreso.set(value);
    }

    public final ObjectProperty<LocalDate> fecha_ingresoProperty() {
        return fecha_ingreso;
    }


    public final LocalDate getFecha_reserva() {
        return fecha_reserva.get();
    }

    public final void setFecha_reserva(LocalDate value) {
        fecha_reserva.set(value);
    }

    public final ObjectProperty<LocalDate> fecha_reservaProperty() {
        return fecha_reserva;
    }


    public final int getIdreserva() {
        return idreserva.get();
    }

    public final void setIdreserva(int value) {
        idreserva.set(value);
    }

    public final IntegerProperty idreservaProperty() {
        return idreserva;
    }

}
