package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.App;
import exceptions.ReservaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	    
	    App app = new App();
	    
		/**
		 * Metodo initialize predefinido
		 * @throws Exception 
		 */
		@FXML
		
		private void initialize() throws Exception {
			
			String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
			
			txtFecha.setText(fecha);
			
			
			Usuario userTest1 = new Usuario("10050", "323323", "juane@");
			
			
			Cama cama = new Cama( "id1", Estado.FUNCIONANDO, TipoCama.INDIVIDUAL, "id1" );
			
			
			ArrayList<Cama> arrayCamas = new ArrayList<Cama>();
			arrayCamas.add(cama);
			
			
			Habitacion habitacion =  app.hotel.crearHabitacion("01", arrayCamas, Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);
		
			
			Factura factura = new Factura("01", "14/02", "300", "315");
			app.hotel.crearReserva("1", userTest1, habitacion, factura);
			
			cargarReservasAction();
			cargarHabitacionesDisponiblesAction();
			
		}

	    @FXML
	    void crearReservaAction(ActionEvent event) {
	    	app.mostrarVentanaFormularioReserva();
	    }

	    @FXML
	    void generarFacturaAction(ActionEvent event) {
	    	JOptionPane.showMessageDialog(null, "se ha generado una factura");

	    }

	    @FXML
	    void salirAction(ActionEvent event) {
	    	System.exit(0);
	    }

		public void setAplicacion(App app) {
			this.app = app;
			
		}
		
		public void cargarReservasAction() {
			
			ArrayList<Reserva>reservas = app.hotel.getListaReservas();
			// Crear una cadena para almacenar la información de las reservas
		    StringBuilder reservaInfo = new StringBuilder();

		    // Recorrer las reservas y construir la cadena
		    for (Reserva reserva : reservas) {
		        reservaInfo.append("ID Reserva: ").append(reserva.getId()).append("\n");
		        reservaInfo.append("Nombre Cliente: ").append(reserva.getUsuario().getCedula()).append("\n");
		        reservaInfo.append("Fecha Inicio: ").append(reserva.getId()).append("\n");
		       // reservaInfo.append("Fecha Fin: ").append(reserva.getFechaSalida()).append("\n");
		        // Agregar más detalles de la reserva según sea necesario
		        reservaInfo.append("\n");  // Espacio entre reservas
		    }

		    // Establecer la cadena construida en el TextArea
		    txtReservas.setText(reservaInfo.toString());
			
			
		}
		public void cargarHabitacionesDisponiblesAction() {
			
			ArrayList<Habitacion>habitaciones = app.hotel.getListaHabitaciones();
			// Crear una cadena para almacenar la información de las reservas
			StringBuilder habitacionesInfo = new StringBuilder();
			
			// Recorrer las reservas y construir la cadena
			for (Habitacion habitacion : habitaciones) {
				if(habitacion.getDisponibilidad() == Disponibilidad.DISPONIBLE)  {
										
					habitacionesInfo.append("ID habitacion: ").append(habitacion.getId()).append("\n");
					habitacionesInfo.append("Estado: ").append(habitacion.getEstado()).append("\n");
					habitacionesInfo.append("Disponibilidad: ").append(habitacion.getDisponibilidad()).append("\n");
					habitacionesInfo.append("Tipo: ").append(habitacion.getTipoHabticacion()).append("\n");
					
					// Agregar más detalles de la reserva según sea necesario
					habitacionesInfo.append("\n"); 
					// Espacio entre reservas
				}
					
			}
			
			// Establecer la cadena construida en el TextArea
			txtcamasDisponibles.setText(habitacionesInfo.toString());
			
			
		}
}
