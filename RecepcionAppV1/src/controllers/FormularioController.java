package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import persistencia.Persistencia;

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
	
	RecepcionController recepcionController;
	
	public Reserva reserva = new Reserva(); // Crear una nueva instancia de Reserva

	@FXML
	void reservaAction(ActionEvent event) {

		reserva = reservarAction();
		app.mostrarVentanaRecepcion();
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
	
	List<Reserva> reservas = new ArrayList<Reserva>();

	public Reserva reservarAction() {
		
		RecepcionController recepcionController = new RecepcionController();
		Reserva reserva = recepcionController.reservaActual;
		
		System.out.println(app.hotel.getListaUsuarios() + " formulario");
		String cedula = txtCedula.getText(); // Obtener el numero de cedula desde el campo de texto

		Usuario usuario = new Usuario(cedula, "", "");

		reserva.setUsuario(usuario);
		reserva.setFechaEntrada(dpFechaLlegada.getValue());
		reserva.setFechaSalida(dpFechaSalida.getValue());
		reserva.setId(App.generateRandomId());
		reserva.setListaHabitaciones(recepcionController.getReservaActual().getListaHabitaciones());
		reservas.add(reserva);


		JOptionPane.showMessageDialog(null, "Reserva creada con exito");
		System.out.println(reserva.getId() + " creada");
		
		try {
			Persistencia.guardarReservas(reservas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserva;
		
	}

	@FXML
	void initialize() {
		// Configuraci�n inicial del controlador
		cbxCamasAdicionales.getItems().addAll("Si", "No");
		cbxCamasAdicionales.setPromptText("Seleccione...");

	}

	public void setController(RecepcionController recepcionController) {
		this.recepcionController = recepcionController;
		
	}

}
