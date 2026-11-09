package pe.edu.upeu.proyect_final.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pe.edu.upeu.proyect_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IReservaDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Reserva;

import java.time.LocalDate;

public class ReservaController {
    @FXML
    private TableView<Reserva> tableReserva;
    @FXML
    private TableColumn<Cliente, Number> ColNumero;
    @FXML
    private TableColumn<Reserva, Integer> ColId;
    @FXML
    private TableColumn<Reserva, String> ColCliente;
    @FXML
    private TableColumn<Reserva, String> ColHabitacion;
    @FXML
    private TableColumn<Reserva, LocalDate> ColIngreso;
    @FXML
    private TableColumn<Reserva, LocalDate> ColSalida;
    @FXML
    private TableColumn<Reserva, Double> ColTotal;
    @FXML
    private TableColumn<Reserva, String> ColEstado;

    private final IReservaDao reservaDao = new ReservaDaoImpl();
    private ObservableList<Reserva> listReserva;

    @FXML
    private void initialize() {
        ColNumero.setCellFactory(colum -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }

        });
        ColId.setCellValueFactory(cd -> cd.getValue().idreservaProperty().asObject());
        ColCliente.setCellValueFactory(cd -> cd.getValue().getCliente().nombreProperty());
        ColHabitacion.setCellValueFactory(cd -> cd.getValue().getHabitacion().numeroProperty());
        ColIngreso.setCellValueFactory(cd -> cd.getValue().fecha_ingresoProperty());
        ColSalida.setCellValueFactory(cd -> cd.getValue().fecha_salidaProperty());
        ColTotal.setCellValueFactory(cd -> cd.getValue().totalProperty().asObject());
        ColEstado.setCellValueFactory(cd -> {
                    String estadoDB = cd.getValue().getEstado();
                    String estadoTexto = switch (estadoDB) {
                        case "R" -> "Reserva";
                        case "O" -> "Ocupado";
                        case "T" -> "Terminado";
                        case "C" -> "Cancelada";
                        default -> "Desconocido";
                    };
                    return new SimpleStringProperty(estadoTexto);
                }
        );
        cargarDatos();
    }

    private void cargarDatos() {
        listReserva = FXCollections.observableArrayList(reservaDao.rlistAll());
        tableReserva.setItems(listReserva);
    }
}
