package pe.edu.upeu.proyect_final.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.proyect_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IReservaDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Reserva;
import pe.edu.upeu.proyect_final.utils.UtilAlerts;

import java.time.LocalDate;
import java.util.Optional;

public class ReservaController {
    private static UtilAlerts utilAlerts = new UtilAlerts();

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
        ColEstado.setCellFactory(cl -> new TableCell<Reserva, String>(){
            @Override
            protected void updateItem(String estado, boolean empty){
                super.updateItem(estado,empty);
                if (empty || estado == null){
                    setText(null);
                    setStyle("");
                }else{
                    setText(estado);
                    switch (estado){
                        case "Reserva"-> setStyle("-fx-text-fill: #1B5E20;-fx-font-weight: bold;");
                        case "Ocupado"-> setStyle("-fx-text-fill: #B71C1C;-fx-font-weight: bold;");
                        case "Terminado"-> setStyle("-fx-text-fill: #0D47A1;-fx-font-weight: bold;");
                        case "Cancelada"-> setStyle("-fx-text-fill: #424242;-fx-font-weight: bold;");
                        default -> setStyle("");
                    }
                }
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        listReserva = FXCollections.observableArrayList(reservaDao.rlistAll());
        tableReserva.setItems(listReserva);
    }
    @FXML
    private void handleNew(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pe/edu/upeu/proyect_final/view/reserva-form.fxml"));
            Parent root = loader.load();
            ReservaFromController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Nueva Reserva");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.isSaved()){

            }
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = utilAlerts.mostrarAlerts("Error", "No se pudo abrir el programa", "ERROR");
        }
    }
    @FXML
    private void handleCheckIn(){
        updateStatus("O","Confirmar Check-In", "Desea Marcar la Habitacion como ocupada?");
    }
    @FXML
    private void handleCheckOut(){
        updateStatus("T","Confirmar Check-Out", "Desea Finalizar la Reserva?");
    }
    @FXML
    private void handleCancel(){
        updateStatus("C","Confirmar Cancelacion", "Estas seguro de CANCELAR esta reserva?");
    }
    private void updateStatus(String nuevoEstado, String titulo, String mensaje){
        Reserva select = tableReserva.getSelectionModel().getSelectedItem();
        if (select == null){
            Alert alert = utilAlerts.mostrarAlerts("Seleccion Requerida", "Seleccione una reserva de la tabla", "WARNING");
            alert.showAndWait();
            return;
        }
        Alert confirm = utilAlerts.mostrarAlerts(titulo,mensaje, "CONFIRMATION");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            select.setEstado(nuevoEstado);
            if (reservaDao.updateStatus(select)){
                cargarDatos();
            }
        }

    }



}
