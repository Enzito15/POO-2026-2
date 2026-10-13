package pe.edu.upeu.proyect_final.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.proyect_final.HelloApplication;
import pe.edu.upeu.proyect_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.utils.UtilAlerts;

import java.util.Optional;

public class HabitacionController {
    private static  UtilAlerts utilAlerts = new UtilAlerts();

    @FXML
    private TableView<Habitaciones> tableHabitaciones;
    //Variable relativa XDD
    @FXML
    private TableColumn<Habitaciones, Number> Colnumber;
    @FXML
    private TableColumn<Habitaciones, Integer> ColIdHab;
    @FXML
    private TableColumn<Habitaciones, String> Colnum;
    @FXML
    private TableColumn<Habitaciones, String> ColnumPiso;
    @FXML
    private TableColumn<Habitaciones, String> ColnumHabitacion;
    @FXML
    private TableColumn<Habitaciones, String> ColTipo;
    @FXML
    private TableColumn<Habitaciones, Double> ColPrecio;
    @FXML
    private TableColumn<Habitaciones, String> ColEstado;


    private final IHabitacionDao iHabitacionDao = new HabitacionDaoImpl();
    private ObservableList<Habitaciones> listaHabitaciones;

    public void initialize() {
        Colnumber.setCellFactory(colum -> new TableCell<>() {
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

        ColIdHab.setCellValueFactory(cellData -> cellData.getValue().idhabitacionProperty().asObject());
        Colnum.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
        ColnumPiso.setCellValueFactory(cellData -> cellData.getValue().numeroPisoProperty());
        ColnumHabitacion.setCellValueFactory(cellData -> cellData.getValue().numeroHabitacionProperty());
        ColTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        ColPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        ColEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        //LLamar al metodo
        cargarDatos();

    }

    private void cargarDatos() {
        listaHabitaciones = FXCollections.observableArrayList(iHabitacionDao.listAll());
        tableHabitaciones.setItems(listaHabitaciones);
    }


    @FXML
    private void handleNew() {
        //System.out.println("Abriendo el formulario");
        //showForm(null);
    }
/*
    private void showForm(Cliente c) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/pe/edu/upeu/proyect_final/view/cliente-form.fxml"));
            Parent root = loader.load();
            ClienteFormControler controler = loader.getController();
            controler.setCliente(c);
            Stage stage = new Stage();
            stage.setTitle(c==null ?"nuevo cliente": "editar cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controler.isSaved()){
                Habitaciones habitacionprocesada = controler.getCliente();
                if (c==null){
                    boolean result = iHabitacionDao.insert(clienteProcesado);
                    if (result){
                        Alert alert = utilAlerts.mostrarAlerts("success", "registro sastisfactorio", "CONFIRMATION");
                        alert.show();
                    }else{
                        Alert alert = utilAlerts.mostrarAlerts("ERROR", "ERROR", "ERROR");
                        alert.show();
                    }
                }else{
                    boolean result = clienteDao.update (clienteProcesado);
                    if (result){
                        Alert alert = utilAlerts.mostrarAlerts("success", "registro sastisfactorio", "WARNING");
                        alert.show();
                    }else{
                        Alert alert = utilAlerts.mostrarAlerts("ERROR", "Error", "ERROR");
                        alert.show();
                    }
                }
                cargarDatos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

 */

}
