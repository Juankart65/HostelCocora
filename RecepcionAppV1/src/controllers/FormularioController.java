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
import model.Cama;
import model.Estado;
import model.Reserva;
import model.TipoCama;
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


	
	public Reserva reservarAction() {
		
		RecepcionController recepcionController = new RecepcionController();
		Reserva reserva = recepcionController.reservaActual;
		
		System.out.println(reserva.getListaHabitaciones());
		
		System.out.println(app.hotel.getListaUsuarios() + " formulario");
		String cedula = txtCedula.getText(); // Obtener el n�mero de c�dula desde el campo de texto


		Usuario usuario = new Usuario(cedula, "", "");

		reserva.setUsuario(usuario);
		reserva.setFechaEntrada(dpFechaLlegada.getValue());
		reserva.setFechaSalida(dpFechaSalida.getValue());
		reserva.setId(App.generateRandomId());


		JOptionPane.showMessageDialog(null, "Reserva creada con exito");
		System.out.println(reserva.getId() + " creada");
		
		definirHabitacionesDisponibles(reserva);
		
		System.out.println(reserva.getListaHabitaciones());
		return reserva;
	}
	
	
	public void definirHabitacionesDisponibles(Reserva reserva) {
		if(cbxCamasAdicionales.getValue() == "Si") {
			
			for (Cama cama : app.hotel.getListaCamas()) {
				if(cama.getEstado().equals(Estado.FUNCIONANDO) && cama.getTipoCama().equals(TipoCama.INDIVIDUAL)&& reserva.getListaHabitaciones().size()==1) {
					reserva.getListaHabitaciones().get(0).getListaCamas().add(cama);

					cama.setEstado(Estado.MANTENIMIENTO);
					cama.setIdHabitacion(reserva.getListaHabitaciones().get(0).getId());
					
				}
			}
		}
	}

	@FXML
	void initialize() {
		// Configuraci�n inicial del controlador
		cbxCamasAdicionales.getItems().addAll("Si", "No");
		cbxCamasAdicionales.setPromptText("Seleccione...");

	}

}
