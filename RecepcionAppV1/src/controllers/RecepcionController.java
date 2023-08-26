package controllers;

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
import model.Reserva;
import model.TipoCama;
import model.TipoHabitacion;
import model.Usuario;

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

	    
	    App app = new App();
	    
		/**
		 * Metodo initialize predefinido
		 * @throws Exception 
		 */
		@FXML
		
		private void initialize() throws Exception {
			
			//Fecha
			String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
			txtFecha.setText(fecha);
			
			/*
			 * 
			 */
			Usuario userTest1 = new Usuario("10050", "323323", "juane@");
			Cama cama = new Cama( "id1", Estado.FUNCIONANDO, TipoCama.INDIVIDUAL, "id1" );
			
			
			ArrayList<Cama> arrayCamas = new ArrayList<Cama>();
			arrayCamas.add(cama);
			
			
			Habitacion habitacion =  app.hotel.crearHabitacion("01", arrayCamas, Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);
			Habitacion habitacion1 =  app.hotel.crearHabitacion("02", arrayCamas, Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.DOBLE);
			Habitacion habitacion2 =  app.hotel.crearHabitacion("03", arrayCamas, Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);

		
			
			Factura factura = new Factura("01", "14/02", "300", "315");
			Reserva reserva = new Reserva("01", userTest1, null, factura, "", "");
			app.hotel.crearReserva(reserva);
			
			
			cargarReservasAction();
			cargarHabitacionesDisponiblesAction();
			
		}


	    @FXML
	    void generarFacturaAction(ActionEvent event) {
	    	JOptionPane.showMessageDialog(null, 
	    			"se ha generado una factura/n"
	
	
	    			);

	    }

	    @FXML
	    void salirAction(ActionEvent event) {
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
		                setText("ID Reserva: " + reserva.getId() +
		                        "\nNombre Cliente: " + reserva.getUsuario().getCedula() +
		                        "\nFecha Inicio: " + reserva.getFechaEntrada());
		            }
		        }
		    });

		    // Configurar el evento de selección del ListView
		    lvReservas.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldValue, newValue) -> mostrarDetallesReserva(newValue));
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
		                setText("ID Habitacion: " + habitacion.getId() +
		                        "\nEstado: " + habitacion.getEstado() +
		                        "\nDisponibilidad: " + habitacion.getDisponibilidad() +
		                        "\nTipo de habitacion: " + habitacion.getTipoHabticacion());
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
	    	Reserva reserva = null;
	    	
//	    	for (String indice : selectedIndices) {
//				for (Habitacion habitacion : app.hotel.getListaHabitaciones()) {
//					if(habitacion != null && habitacion.getId().equals(indice)) {
//						reserva.getListaHabitaciones().add(habitacion);
//					}
//				}
//			}
	    	
	    	app.mostrarVentanaFormularioReserva(reserva);
	    }
	    
	    

}
