package controllers;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import application.App;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import model.Habitacion;
import model.Reserva;

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

	@FXML
	private Button btnCargarHabitaciones;

	@FXML
	private ListView<Habitacion> lvHabitacionesDisponibles = new ListView<Habitacion>();
	private List<String> selectedIndices = new ArrayList<>();

	App app;
	
	private Reserva reserva = new Reserva();
	
	public Reserva reservaActual = new Reserva();

	// Server connection parameters
	private static final String SERVER_IP = "localhost"; // Server IP address
	private static final int SERVER_PORT = 7777; // Server port

	private List<Habitacion> habitacionesDisponibles = new ArrayList<Habitacion>();

	/**
	 * Metodo initialize predefinido
	 */
	@FXML
	private void initialize() {
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
		txtFecha.setText(fecha);
		System.out.println("Esperando...");
		setHabitacionesDisponibles(sendMessageAndReceiveList(""));
		cargarHabitacionesDisponiblesAction();
	}

    @FXML
    void crearReservaAction(ActionEvent event) {

    	Reserva newReserva = new Reserva();
    	
    	for (String indice : selectedIndices) {
			for (Habitacion habitacion : habitacionesDisponibles) {
				if(habitacion != null && habitacion.getId().equals(indice)) {
					System.out.println("1");
					reservaActual.getListaHabitaciones().add(habitacion);
					System.out.println(habitacion);
				}
			}
		}
    	
    	newReserva = app.mostrarVentanaCrearReserva(reservaActual);
    	app.hotel.getListaReservas().add(newReserva);
    }
    
	@FXML
	void salirAction(ActionEvent event) {
		app.mostrarVentanaLogin();
	}

	public void setAplicacion(App app) {
		this.app = app;

	}

	private List<Habitacion> sendMessageAndReceiveList(String message) {
		List<Habitacion> receivedList = new ArrayList<>();

		try {
			// Connect to the server
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);

			// Setup output stream
			ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());

			// Send the message to the server
			objectOut.writeObject(message);
			objectOut.flush(); // Asegurarse de que los datos se envíen al servidor

			// Setup input stream
			ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

			// Receive the list from the server
			try {
				System.out.println("Recibiendo...");
				receivedList = (List<Habitacion>) objectIn.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Close the streams and socket
			objectIn.close();
			objectOut.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return receivedList;
	}

	public void cargarHabitacionesDisponiblesAction() {

		selectedIndices.clear();
		List<Habitacion> habitacion = getHabitacionesDisponibles();
		ObservableList<Habitacion> reservasObservableList = FXCollections.observableArrayList(habitacion);

		lvHabitacionesDisponibles.setItems(reservasObservableList);

		// Configurar el CellFactory para mostrar los detalles de la reserva
		lvHabitacionesDisponibles.setCellFactory(param -> new ListCell<Habitacion>() {
			@Override
			protected void updateItem(Habitacion habitacion, boolean empty) {
				super.updateItem(habitacion, empty);

				if (empty || habitacion == null) {
					setText(null);
				} else {
					setText("ID Habitacion: " + habitacion.getId() + "\nEstado: " + habitacion.getEstado()
							+ "\nDisponibilidad: " + habitacion.getDisponibilidad() + "\nTipo de habitacion: "
							+ habitacion.getTipoHabticacion());
				}
			}
		});

		// Configurar el modo de selección múltiple
		lvHabitacionesDisponibles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Configurar el evento de selección del ListView
		lvHabitacionesDisponibles.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Habitacion> observable, Habitacion oldValue, Habitacion newValue) -> {
					for (Habitacion habitacionSelected : lvHabitacionesDisponibles.getSelectionModel()
							.getSelectedItems()) {
						selectedIndices.add(habitacionSelected.getId());
						System.out.println(habitacionSelected.getId());
					}
				});
	}

	public List<Habitacion> getHabitacionesDisponibles() {
		return habitacionesDisponibles;
	}

	public void setHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
		this.habitacionesDisponibles = habitacionesDisponibles;
	}

}
