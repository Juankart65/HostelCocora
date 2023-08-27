package controllers;


import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Hotel;
import model.Reserva;

public class FormularioController {

    @FXML
    private Button btnSalir;

    @FXML
    private DatePicker dpFechaLlegada;

    @FXML
    private Button bntReservar;

    @FXML
    private TextField txtCantidadHabitaciones;

    @FXML
    private DatePicker dpFechaSalida;

    @FXML
    private ComboBox<String> cbxCamasAdicionales = new ComboBox<String>();
    
    App app = new App();

    @FXML
    void reservarAction(ActionEvent event) {
    	crearReserva();
    	app.mostrarVentanaReservas();
    }

    @FXML
    void initialize() {
        // Configuración inicial del controlador
        cbxCamasAdicionales.getItems().addAll("Sí", "No");
        cbxCamasAdicionales.setPromptText("Seleccione...");
        
        
    }
    @FXML
    void salirAction(ActionEvent event) {
    	app.mostrarVentanaReservas();
    }


	public void setAplication(App app) {
		this.app = app;
		
	}

	public Reserva crearReserva() {
		Reserva reserva  = new Reserva();
		
		reserva.setUsuario(Hotel.getUsuarioActual());
		
		System.out.println(reserva);
		return reserva;
		
		
	}


	public void mostrarReserva(Reserva reserva) {
		System.out.println(reserva.getListaHabitaciones().size());
		txtCantidadHabitaciones.setText(String.valueOf(reserva.getListaHabitaciones().size()));
		
	}



}
