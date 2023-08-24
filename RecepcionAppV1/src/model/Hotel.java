package model;

import java.util.ArrayList;

import exceptions.ReservaException;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class Hotel {

	private String nombre;

	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private ArrayList<Reserva> listaReservas = new ArrayList<>();
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private Alert alert;
	private DialogPane dialogPane;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Habitacion> getListaHabitaciones() {
		return listaHabitaciones;
	}

	public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
	}

	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Hotel() {
		super();
	}

	// -------------CRUD Habitacion-------------------------------------------------

	public Habitacion crearHabitacion(String id, ArrayList<Cama> listaCamas, Estado estado,
			Disponibilidad disponibilidad, TipoHabitacion tipoHabitacion) throws Exception {

		Habitacion habitacion = null;
		boolean habitacionExiste = false;

		habitacionExiste = verificarHabitacionExiste(id);

		if (habitacionExiste) {
			throw new Exception("la habitacion " + id + "no se ha podido crear debido a que ya existe");
		} else {
			habitacion = new Habitacion();
			habitacion.setId(id);
			habitacion.setListaCamas(listaCamas);
			habitacion.setEstado(estado);
			habitacion.setDisponibilidad(disponibilidad);
			habitacion.setTipoHabticacion(tipoHabitacion);

			listaHabitaciones.add(habitacion);
		}
		return habitacion;

	}

	public Habitacion obtenerHabitacion(String id) {

		Habitacion habitacionEncontada = null;
		if (id != null) {
			for (Habitacion habitacion : listaHabitaciones) {
				if (habitacion.getId().equals(id))
					habitacionEncontada = habitacion;
			}

		}
		return habitacionEncontada;

	}

	public void updateHabitacion(String id, Habitacion newHabitacion) {

		Habitacion habitacionEncontada = obtenerHabitacion(id);
		if (habitacionEncontada != null) {
			habitacionEncontada.setId(newHabitacion.getId());
			habitacionEncontada.setListaCamas(newHabitacion.getListaCamas());
			habitacionEncontada.setDisponibilidad(newHabitacion.getDisponibilidad());
			habitacionEncontada.setEstado(newHabitacion.getEstado());
			habitacionEncontada.setTipoHabticacion(newHabitacion.getTipoHabticacion());
		}

	}

	public void deleteHabitacion(String id) {

		if (id != null) {
			for (Habitacion habitacion : listaHabitaciones) {
				if (habitacion.getId().equals(id))
					listaHabitaciones.remove(habitacion);
				System.out.println("habitacion eliminada correctamente");
			}

		}

	}

	public boolean verificarHabitacionExiste(String id) {
		for (Habitacion habitacion : listaHabitaciones) {
			if (habitacion.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}
	
	//--------------------FIN-----------------------------
	
	
	//------------------CRUD Reserva------------------------------
	
	public Reserva crearReserva(String id, Usuario usuario, Habitacion habitacion, Factura factura) throws ReservaException {
		
		Reserva reserva = null;
		boolean reservaExiste = false;
		
		reservaExiste = verificarReservaExiste(id);
		
		if(reservaExiste == true) {
			
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Reserva no creada");
			alert.setHeaderText("La reserva con el id # " + id + " no ha sido creada porque ya existe");
			
			dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
			dialogPane.getStyleClass().add("dialogs");

			alert.showAndWait();
			
			throw new ReservaException("La reserva con el id # " + id + " no se puede guardar porque ya existe");
			

		} else {
			reserva = new Reserva();
			reserva.setId(id);
			reserva.setUsuario(usuario);
			reserva.setHabitacion(habitacion);
			getListaReservas().add(reserva);
		}
		
		return reserva;
	}

	private boolean verificarReservaExiste(String id) {

		for (Reserva reserva : getListaReservas()) {
			if(reserva.getId().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Reserva obtenerReserva(String id) {

		Reserva reservaEncontrada = null;
		if (id != null) {
			for (Reserva reserva : getListaReservas()) {
				if (reserva.getId().equals(id))
					reservaEncontrada = reserva;
			}

		}
		return reservaEncontrada;

	}

	public void updateReserva(String id, Reserva newReserva) {

		Reserva reservaEncontrada = obtenerReserva(id);
		if (reservaEncontrada != null) {
			reservaEncontrada.setId(newReserva.getId());
			reservaEncontrada.setHabitacion(newReserva.getHabitacion());
			reservaEncontrada.setUsuario(newReserva.getUsuario());
			reservaEncontrada.setFactura(newReserva.getFactura());
		}

	}

	public void deleteReserva(String id) {

		if (id != null) {
			for (Reserva reserva : getListaReservas()) {
				if (reserva.getId().equals(id))
					getListaReservas().remove(reserva);
				System.out.println("Reserva eliminada correctamente");
			}

		}

	}
	
	
	//------------------FIN-----------------------------------------
	
}
