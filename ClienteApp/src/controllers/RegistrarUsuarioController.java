package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarUsuarioController {

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private Button btnAceptarUsuario;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCrearNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnCancelarUsuario;

    @FXML
    private TextField txtIdentificacion;
    
    App app = new App();


    @FXML
    void aceptarUsuarioEvent(ActionEvent event) {

    }


    @FXML
    void cancelarUsuarioEvent(ActionEvent event) {
    	app.mostrarVentanaLogin();
    }


	public void setAplicacion(App app) {
		this.app = app;
		
	}


}
