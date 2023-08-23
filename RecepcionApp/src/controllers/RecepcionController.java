package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class RecepcionController {
	
	 @FXML
	    private Button btnGenerarFactura;

	    @FXML
	    private Button btnSalir;

	    @FXML
	    private TextArea txtReservas;

	    @FXML
	    private TextArea txtcamasDisponibles;

	    @FXML
	    private Button btnCrearReserva;

	    @FXML
	    private Label txtFecha;
	    
	    App app = new App();

	    @FXML
	    void crearReservaAction(ActionEvent event) {
	    	app.mostrarVentanaFormularioReserva();
	    }

	    @FXML
	    void generarFacturaAction(ActionEvent event) {

	    }

	    @FXML
	    void salirAction(ActionEvent event) {
	    	System.exit(0);
	    }

		public void setAplicacion(App app) {
			this.app = app;
			
		}
}
