package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import application.App;
import exceptions.ReservaException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Cama;
import model.Disponibilidad;
import model.Estado;
import model.Factura;
import model.Habitacion;
import model.Hotel;
import model.Reserva;
import model.TipoCama;
import model.TipoHabitacion;
import model.Usuario;
import persistencia.Persistencia;

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

	@FXML
	private ListView<Habitacion> lvHabitaciones = new ListView<Habitacion>();

	@FXML
	private ListView<Reserva> lvReservas = new ListView<Reserva>();

	private List<String> selectedIndices = new ArrayList<>();

	private ServerSocket serverSocket;
	private boolean isServerRunning = false;
	
	public Reserva reservaActual = new Reserva();
	
	

	public Reserva getReservaActual() {
		return reservaActual;
	}

	public void setReservaActual(Reserva reservaActual) {
		this.reservaActual = reservaActual;
	}

	App app = new App();

	/**
	 * Metodo initialize predefinido
	 * 
	 * @throws Exception
	 */
	@FXML

	private void initialize() throws Exception {

		// Fecha
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
		txtFecha.setText(fecha);

		// Start the socket server on a specific port (e.g., 12345)
		startServer(7777);

		inicializarDatos();
//		iniciarSalvarDatosPrueba();
//		cargarDatosDesdeArchivos();
		cargarReservasAction();

		cargarHabitacionesDisponiblesAction();

	}

	private void iniciarSalvarDatosPrueba() {

		try {
			Persistencia.guardarUsuarios(app.hotel.getListaUsuarios());
			Persistencia.guardarReservas(app.hotel.getListaReservas());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarDatosDesdeArchivos() {

		try {

			Persistencia.cargarDatosArchivos(app.hotel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void inicializarDatos() {

		/*
		 * 
		 */

		Usuario userTest1 = new Usuario("1005", "3122459406", "juan@gmail.com");
		Usuario userTest2 = new Usuario("1006", "3122459406", "juan@gmail.com");


		try {
			app.hotel.crearUsuario(userTest1);
			app.hotel.crearUsuario(userTest2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("------------------");
		System.out.println(app.hotel.getListaUsuarios());

		Cama cama = new Cama("id1", Estado.FUNCIONANDO, TipoCama.INDIVIDUAL, "id1");

		ArrayList<Cama> arrayCamas = new ArrayList<Cama>();
		arrayCamas.add(cama);

		try {
			Habitacion habitacion2 = app.hotel.crearHabitacion("03", arrayCamas, Estado.FUNCIONANDO,
					Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);
			Habitacion habitacion = app.hotel.crearHabitacion("01", arrayCamas, Estado.FUNCIONANDO,
					Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);
			Habitacion habitacion1 = app.hotel.crearHabitacion("02", arrayCamas, Estado.FUNCIONANDO,
					Disponibilidad.DISPONIBLE, TipoHabitacion.DOBLE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Factura factura = new Factura("01", "14/02", "300", "315");
		Reserva reserva = new Reserva(userTest1, null, factura);
		try {
			app.hotel.crearReserva(reserva);
		} catch (ReservaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Empresa inicializada " + app.hotel);

	}

	@FXML
	void generarFacturaAction(ActionEvent event) {

		JOptionPane.showMessageDialog(null, "se ha generado una factura/n");

	}

	private void startServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			isServerRunning = true;

			new Thread(() -> {
				while (isServerRunning) {
					try {
						Socket clientSocket = serverSocket.accept();
						// Handle the client socket in a separate method or class
						handleClientSocket(clientSocket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Server started on port " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleClientSocket(Socket clientSocket) {
		try {
			// Configurar flujos de entrada y salida para comunicarse con el cliente
			ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());

//	            // Leer el mensaje del cliente
//	            String mensajeDelCliente = objectIn.readLine();

			// Procesar el mensaje y obtener la lista de objetos
			List<Habitacion> listaDeHabitaciones = obtenerListaDeHabitaciones();

			// Enviar la lista de objetos al cliente
			try {
				ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
				objectOut.writeObject(listaDeHabitaciones);
				objectOut.flush(); // Importante: asegurarse de que los datos se envíen inmediatamente
				objectOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Cerrar flujos y el socket del cliente
			objectIn.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Habitacion> obtenerListaDeHabitaciones() {
		List<Habitacion> habitacionesDisponibles = new ArrayList<Habitacion>();

		for (Habitacion habitacion : app.hotel.getListaHabitaciones()) {
			if (habitacion != null && habitacion.getDisponibilidad().equals(Disponibilidad.DISPONIBLE)) {
				habitacionesDisponibles.add(habitacion);
			}
		}

		return habitacionesDisponibles;
	}

	private void stopServer() {
		isServerRunning = false;
		try {
			if (serverSocket != null) {
				serverSocket.close();
				System.out.println("Server stopped");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void salirAction(ActionEvent event) {
		stopServer();
		System.exit(0);
	}

	public void setAplicacion(App app) {
		this.app = app;

	}

	public void cargarReservasAction() {

		ArrayList<Reserva> reservas = app.hotel.getListaReservas();
		ObservableList<Reserva> reservasObservableList = FXCollections.observableArrayList(reservas);

		lvReservas.setItems(reservasObservableList);

		// Configurar el CellFactory para mostrar los detalles de la reserva
		lvReservas.setCellFactory(param -> new ListCell<Reserva>() {
			@Override
			protected void updateItem(Reserva reserva, boolean empty) {
				super.updateItem(reserva, empty);

				if (empty || reserva == null) {
					setText(null);
				} else {
					setText("ID Reserva: " + reserva.getId() + "\nNombre Cliente: " + reserva.getUsuario().getCedula()
							+ "\nFecha Inicio: 2023-08-27" + "\nFecha Inicio: 2023-08-30");
				}
			}
		});

		// Configurar el evento de selección del ListView
		lvReservas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetallesReserva(newValue));
		System.out.println("Ok");
	}

	private Object mostrarDetallesReserva(Reserva newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cargarHabitacionesDisponiblesAction() {

		ArrayList<Habitacion> habitacion = app.hotel.getListaHabitaciones();
		ObservableList<Habitacion> reservasObservableList = FXCollections.observableArrayList(habitacion);

		lvHabitaciones.setItems(reservasObservableList);

		// Configurar el CellFactory para mostrar los detalles de la reserva
		lvHabitaciones.setCellFactory(param -> new ListCell<Habitacion>() {
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
		lvHabitaciones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Configurar el evento de selección del ListView
		lvHabitaciones.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends Habitacion> observable, Habitacion oldValue, Habitacion newValue) -> {
					selectedIndices.clear();
					for (Habitacion habitacionSelected : lvHabitaciones.getSelectionModel().getSelectedItems()) {
						selectedIndices.add(habitacionSelected.getId());
					}
				});
		

	}

	@FXML
	void crearReservaAction(ActionEvent event) {
		Reserva newReserva = new Reserva();

		for (String indice : selectedIndices) {
			for (Habitacion habitacion : app.hotel.getListaHabitaciones()) {
				if (habitacion != null && habitacion.getId().equals(indice)) {
					
					reservaActual.getListaHabitaciones().add(habitacion);
					
					habitacion.setDisponibilidad(Disponibilidad.OCUPADO);
				}
			}
		}

		app.mostrarVentanaFormularioReserva(reservaActual, this);
		lvReservas.setDisable(false);
		
		try {
			Persistencia.cargarDatosArchivos(app.hotel);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}