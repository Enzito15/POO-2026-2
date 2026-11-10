package pe.edu.upeu.proyect_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.proyect_final.implementacion.ClienteDaoImpl;
import pe.edu.upeu.proyect_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.proyect_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IClienteDao;
import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.interfaces.IReservaDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.modelo.Reserva;
import pe.edu.upeu.proyect_final.modelo.UserSession;
import pe.edu.upeu.proyect_final.utils.UtilAlerts;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservaFromController {
    private static UtilAlerts utilAlerts = new UtilAlerts();
    @FXML
    private TextField txtDniBuscar, txtNumHabBusqueda, txtTotal;
    @FXML
    private Label lblNombreCliente, lblDetalleHab;
    @FXML
    private DatePicker dpIngreso, dpSalir;

    private Cliente clienteEncontrado;
    private Habitaciones habitacionEncontrada;

    private boolean guardado = false;
    private final IClienteDao clienteDao = new ClienteDaoImpl();
    private final IHabitacionDao HabitacionDao = new HabitacionDaoImpl();
    private final IReservaDao reservaDao = new ReservaDaoImpl();

    @FXML
    private void initialize(){
        dpIngreso.valueProperty().addListener((ov,oldValue,newValue) ->{
            calcularTotal();
        });
        dpSalir.valueProperty().addListener((ov,oldValue,newValue) ->{
            calcularTotal();
        });
    }
    @FXML
    private void buscarCleiente(){
        String dni = txtDniBuscar.getText();
        if (dni.length()==8){
            clienteEncontrado = clienteDao.searchByDni(dni);
            if (clienteEncontrado !=null){
                lblNombreCliente.setText(clienteEncontrado.getNombre()+" "+clienteEncontrado.getApellidos());
                lblNombreCliente.setStyle("-fx-text-fill: green");
            }else{
                lblNombreCliente.setText("no encontrado");
                lblNombreCliente.setStyle("-fx-text-fill: red");
            }
        }
    }
    @FXML
    private void buscarhabitacion(){
        String num = txtNumHabBusqueda.getText();
        if (!num.isEmpty()){
            habitacionEncontrada = HabitacionDao.searchByNumero(num);
            if (habitacionEncontrada !=null){
                lblDetalleHab.setText(habitacionEncontrada.getTipo()+" "+habitacionEncontrada.getPrecio());
                lblDetalleHab.setStyle("-fx-text-fill: green");
            }else{
                lblDetalleHab.setText("No encontrado");
                lblDetalleHab.setStyle("-fx-text-fill: red");
            }
        }
    }
    @FXML
    private void calcularTotal(){
        if (habitacionEncontrada !=null && dpIngreso.getValue() !=null && dpSalir.getValue() !=null){
            LocalDate ingreso =dpIngreso.getValue();
            LocalDate salida = dpSalir.getValue();
            long noches = ChronoUnit.DAYS.between(ingreso,salida);
            if(noches>0){
                double precioNoche = habitacionEncontrada.getPrecio();
                double totalPrecio =noches + precioNoche;
                txtTotal.setText(String.format("%.2f",totalPrecio));
                //lamamos al calculo del total automatico
                calcularTotal();
            }else{
                txtTotal.setText("0.00");
                lblDetalleHab.setStyle("-fx-text-fill: red;");
                txtTotal.setText("0.00");
            }
        }
    }
    private void cerrar(){
        ((Stage) txtDniBuscar.getScene().getWindow()).close();
    }
    @FXML
    private void handleCancelar(){
        cerrar();
    }
    public boolean isSaved() {
        return guardado;
    }
    @FXML
    private void handleGuardar(){
        if (clienteEncontrado == null || habitacionEncontrada ==null
                || dpIngreso.getValue() == null || dpSalir.getValue()==null){
            Alert alert = utilAlerts.mostrarAlerts("Error", "Completa los campos", "ERROR");
            alert.showAndWait();
        }
        Reserva r = new Reserva();
        r.setCliente(clienteEncontrado);
        r.setHabitacion(habitacionEncontrada);
        r.setFecha_reserva(LocalDate.now());
        r.setFecha_ingreso(dpIngreso.getValue());
        r.setFecha_salida(dpSalir.getValue());
        r.setTotal(Double.parseDouble(txtTotal.getText()));
        r.setEstado("R");
        r.setUsuario(UserSession.getInstance().getUserLogin());
        if (reservaDao.validateHabitacion(r)){
            Alert alert = utilAlerts.mostrarAlerts("Habitacion Ocupada",
                    "La habitacion no esta disponible","WARNING");
            alert.showAndWait();
        }else{
            if (reservaDao.insert(r)){
                guardado = true;
                cerrar();
            }
        }
    }
}
