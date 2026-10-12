package pe.edu.upeu.proyect_final.modelo;

import javafx.beans.property.*;

public class Habitaciones {
    private final IntegerProperty idhabitacion = new SimpleIntegerProperty();
    private final IntegerProperty numero = new SimpleIntegerProperty(this, "numero");
    private final IntegerProperty numeroPiso = new SimpleIntegerProperty(this, "numeroPiso");
    private final IntegerProperty numeroHabitacion = new SimpleIntegerProperty(this, "numeroHabitacion");
    private final StringProperty tipo = new SimpleStringProperty(this, "tipo");
    private final StringProperty estado = new SimpleStringProperty(this, "estado");
    private final DoubleProperty precio = new SimpleDoubleProperty(this, "precio");

    public Habitaciones() {
    }

    public Habitaciones(int idhabitacion, int numero, int numeroPiso, int numeroHabitacion, String tipo, String estado, Double precio){
    setIdhabitacion(idhabitacion);
    setNumero(numero);
    setNumeroPiso(numeroPiso);
    setIdhabitacion(numeroHabitacion);
    setTipo(tipo);
    setEstado(estado);
    setPrecio(precio);
    }

    public final double getPrecio() {
        return precio.get();
    }

    public final void setPrecio(double value) {
        precio.set(value);
    }

    public final DoubleProperty precioProperty() {
        return precio;
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


    public final String getTipo() {
        return tipo.get();
    }

    public final void setTipo(String value) {
        tipo.set(value);
    }

    public final StringProperty tipoProperty() {
        return tipo;
    }


    public final int getNumeroHabitacion() {
        return numeroHabitacion.get();
    }

    public final void setNumeroHabitacion(int value) {
        numeroHabitacion.set(value);
    }

    public final IntegerProperty numeroHabitacionProperty() {
        return numeroHabitacion;
    }


    public final int getNumeroPiso() {
        return numeroPiso.get();
    }

    public final void setNumeroPiso(int value) {
        numeroPiso.set(value);
    }

    public final IntegerProperty numeroPisoProperty() {
        return numeroPiso;
    }


    public final int getNumero() {
        return numero.get();
    }

    public final void setNumero(int value) {
        numero.set(value);
    }

    public final IntegerProperty numeroProperty() {
        return numero;
    }


    public final int getIdhabitacion() {
        return idhabitacion.get();
    }

    public final void setIdhabitacion(int value) {
        idhabitacion.set(value);
    }

    public final IntegerProperty idhabitacionProperty() {
        return idhabitacion;
    }


}
