package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ReservasClienteController {

	@FXML
	private Button btnGenerarFactura;

	@FXML
	private Button btnSalir;

	@FXML
	private TextArea txtcamasDisponibles;

	@FXML
	private Button btnCrearReserva;

	@FXML
	private Label txtFecha;

	App app = new App();

	/**
	 * Metodo initialize predefinido
	 */
	@FXML
	private void initialize() {
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
		txtFecha.setText(fecha);
	}

	@FXML
	void crearReservaAction(ActionEvent event) {

	}

	@FXML
	void generarFacturaAction(ActionEvent event) {

	}

	@FXML
	void salirAction(ActionEvent event) {
		app.mostrarVentanaLogin();
	}

	public void setAplicacion(App app) {
		this.app = app;

	}

}
