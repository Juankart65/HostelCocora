package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FormularioController {

    @FXML
    private Button bntRegistrarUsuario;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<?> cbxCantidadHabitacionesDobles;

    @FXML
    private ImageView imgHabitación;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<?> cbxCantidadHabitaciones;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;
    
    App app = new App();

    @FXML
    private ComboBox<?> cbxCantidadHabitacionesSencillos;

    @FXML
    void registrarUsuarioAction(ActionEvent event) {

    }

    @FXML
    void salirAction(ActionEvent event) {
    	app.mostrarVentanaRecepcion();
    }

	public void setAplicacion(App app) {
		
		this.app = app;
		
	}

}


