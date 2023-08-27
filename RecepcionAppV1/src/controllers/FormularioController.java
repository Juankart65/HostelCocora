package controllers;

import javax.swing.JOptionPane;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Reserva;
import model.Usuario;

public class FormularioController {

	@FXML
	private Button bntRegistrarUsuario;

	@FXML
	private Button btnSalir;

	@FXML
	private TextField txtCantidadHabitaciones;

	@FXML
	private TextField txtNombre;

	@FXML
	private ComboBox<String> cbxCamasAdicionales;

	@FXML
	private TextField txtCedula;

	@FXML
	private DatePicker dpFechaLlegada;

	@FXML
	private DatePicker dpFechaSalida;

	App app;

	@FXML
	void reservaAction(ActionEvent event) {
		reservarAction();
	}

	public DatePicker getDpFechaLlegada() {
		return dpFechaLlegada;
	}

	public void setDpFechaLlegada(DatePicker dpFechaLlegada) {
		this.dpFechaLlegada = dpFechaLlegada;
	}

	public DatePicker getDpFechaSalida() {
		return dpFechaSalida;
	}

	public void setDpFechaSalida(DatePicker dpFechaSalida) {
		this.dpFechaSalida = dpFechaSalida;
	}

	@FXML
	void salirAction(ActionEvent event) {
		app.mostrarVentanaRecepcion();
	}

	public void setAplicacion(App app) {
		this.app = app;
	}

	public void mostrarReserva(Reserva reserva) {

	}


	public Reserva reservarAction(Reserva reserva) {
	    try {
	    	
	        String cedula = txtCedula.getText(); // Obtener el número de cédula desde el campo de texto
//	        Usuario user = app.hotel.getUser(cedula); // Buscar el usuario por cédula
	        
//	        System.out.println(cedula);
	        
//	        System.out.println(app.hotel.verificarUsuarioExiste(cedula));
//	        System.out.println(user);
	        
//	        System.out.println(app.hotel.getListaUsuarios());
	        
	        if (user == null) {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Información");
	            alert.setHeaderText("Usuario no existe");
	            alert.setContentText("El usuario no ha sido encontrado, por favor cree uno.");
	            alert.showAndWait();
	        } else {
	            reserva.setUsuario(user);
	            reserva.setFechaEntrada(dpFechaLlegada.getValue());
	            reserva.setFechaSalida(dpFechaSalida.getValue());
	            
	            System.out.println(reserva.getUsuario());
	            
	            JOptionPane.showMessageDialog(null, "Reserva creada con éxito");	        }
	    } catch (Exception e) {
	        System.out.println("Algo salió mal :");
	    }
	  }
	public Reserva reservarAction() {
		
		RecepcionController recepcionController = new RecepcionController();
		Reserva reserva = recepcionController.reservaActual;
		
		System.out.println(reserva.getListaHabitaciones());
		
		System.out.println(app.hotel.getListaUsuarios() + " formulario");
		String cedula = txtCedula.getText(); // Obtener el nï¿½mero de cï¿½dula desde el campo de texto


		Usuario usuario = new Usuario(cedula, "", "");

		reserva.setUsuario(usuario);
		reserva.setFechaEntrada(dpFechaLlegada.getValue());
		reserva.setFechaSalida(dpFechaSalida.getValue());
		reserva.setId(App.generateRandomId());


		JOptionPane.showMessageDialog(null, "Reserva creada con exito");
		System.out.println(reserva.getId() + " creada");
		return reserva;
	}

	@FXML
	void initialize() {
		// Configuraciï¿½n inicial del controlador
		cbxCamasAdicionales.getItems().addAll("Sï¿½", "No");
		cbxCamasAdicionales.setPromptText("Seleccione...");

	}

}
