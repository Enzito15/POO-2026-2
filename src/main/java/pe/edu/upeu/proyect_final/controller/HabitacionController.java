package pe.edu.upeu.proyect_final.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pe.edu.upeu.proyect_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.utils.UtilAlerts;

public class HabitacionController {
    private static  UtilAlerts utilAlerts = new UtilAlerts();

    @FXML
    private TableView<Habitaciones> tableHabitciones;
    //Variable relativa XDD
    @FXML
    private TableColumn<Habitaciones, Number> Colnumber;
    @FXML
    private TableColumn<Habitaciones, Integer> ColIdHab;
    @FXML
    private TableColumn<Habitaciones, Integer> Colnum;
    @FXML
    private TableColumn<Habitaciones, Integer> ColnumPiso;
    @FXML
    private TableColumn<Habitaciones, Integer> ColnumHabitacion;
    @FXML
    private TableColumn<Habitaciones, String> ColTipo;
    @FXML
    private TableColumn<Habitaciones, String> ColEstado;
    @FXML
    private TableColumn<Habitaciones, Double> ColPrecio;

    private final IHabitacionDao iHabitacionDao = new HabitacionDaoImpl();
    private ObservableList<Habitaciones> listaHabitaciones;

}
