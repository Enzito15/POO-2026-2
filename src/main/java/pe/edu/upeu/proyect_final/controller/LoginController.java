package pe.edu.upeu.proyect_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.proyect_final.implementacion.UsuarioDaoImpl;
import pe.edu.upeu.proyect_final.interfaces.IUsuarioDao;
import pe.edu.upeu.proyect_final.modelo.UserSession;
import pe.edu.upeu.proyect_final.modelo.Usuario;
import pe.edu.upeu.proyect_final.utils.UtilAlerts;

import java.io.IOException;

public class LoginController {
    private static UtilAlerts utilAlerts = new UtilAlerts();
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    private final IUsuarioDao usuarioDao = new UsuarioDaoImpl();

    @FXML
    private void handleLogin() {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            Alert alert = utilAlerts.mostrarAlerts("Campos Vacios",
                    "Porfavor ingrese el usuario y la clave", "WARNING");
            alert.showAndWait();
            return;
        }
        Usuario u = usuarioDao.login(user, pass);
        if (u !=null){
            Alert alert = utilAlerts.mostrarAlerts("Acceso Concedido",
                    "Bienvenido, "+u.getNombre()+"!","INFORMATION");
            alert.showAndWait();
            //Guardar el Usuario
            UserSession.getInstance().setUserLogin(u);
            navigateToHome();
        }else{
            Alert alert = utilAlerts.mostrarAlerts("Acceso Denegado",
                    "Credenciales Incorrectas","INFORMATION");
            alert.showAndWait();
        }
    }
    private void navigateToHome(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pe/edu/upeu/proyect_final/view/main-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)  txtUser.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);//Propiedad para que el sistema Maximize
            stage.setTitle("Sistema Hotelero - DashBoard");
            stage.show();
        }catch (IOException e){
            System.err.println("Error al cargar el home"+e.getMessage());
            e.printStackTrace();
        }
    }
}
