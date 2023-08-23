package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioSesionController {

	App app = new App();

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private Button btnSalir;

	@FXML
	private Button btnRegistrarse;

	@FXML
	private TextField txtCuenta;

	@FXML
	private PasswordField txtContrasenia;

	@FXML
	void iniciarSesionEvent(ActionEvent event) {
		app.mostrarVentanaReservas();
	}

	@FXML
	void registrarseEvent(ActionEvent event) {
		app.mostrarVentanaRegistrarse();
	}

	@FXML
	void salirEvent(ActionEvent event) {
		System.exit(0);
	}

	public void setAplicacion(App app) {
		this.app = app;

	}

}
