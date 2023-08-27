package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.CamaException;
import exceptions.ReservaException;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private ArrayList<Reserva> listaReservas = new ArrayList<>();
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private ArrayList<Cama> listaCamas = new ArrayList<>();

	public ArrayList<Cama> getListaCamas() {
		return listaCamas;
	}

	public void setListaCamas(ArrayList<Cama> listaCamas) {
		this.listaCamas = listaCamas;
	}

	private Alert alert;
	private DialogPane dialogPane;

	public String getNombre() {
		return nombre;
	}

	public Hotel(String nombre) {
		super();
		this.nombre = nombre;

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
		crearHabitaciones();
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

	// --------------------FIN-----------------------------

	// ------------------CRUD Reserva------------------------------

	public Reserva crearReserva(Reserva newReserva) throws ReservaException {

		Reserva reserva = null;
		boolean reservaExiste = false;

		reservaExiste = verificarReservaExiste(newReserva.getId());

		if (reservaExiste == true) {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Reserva no creada");
			alert.setHeaderText("La reserva con el id # " + newReserva.getId() + " no ha sido creada porque ya existe");

			dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
			dialogPane.getStyleClass().add("dialogs");

			alert.showAndWait();

			throw new ReservaException(
					"La reserva con el id # " + newReserva.getId() + " no se puede guardar porque ya existe");

		} else {
			reserva = new Reserva();
			reserva.setId(newReserva.getId());
			reserva.setUsuario(newReserva.getUsuario());
			reserva.setListaHabitaciones(newReserva.getListaHabitaciones());
			getListaReservas().add(reserva);
		}

		return reserva;
	}

	private boolean verificarReservaExiste(String id) {

		for (Reserva reserva : getListaReservas()) {
			if (reserva.getId().equals(id)) {
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
			reservaEncontrada.setListaHabitaciones(newReserva.getListaHabitaciones());
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

	// ------------------FIN-----------------------------------------

	// ------------------CRUD Camas------------------------------

	public Cama crearCama(Cama newCama) throws CamaException {

		Cama cama = null;
		boolean CamaExiste = false;

		CamaExiste = verificarReservaExiste(newCama.getId());

		if (CamaExiste == true) {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cama no creada");
			alert.setHeaderText("La Cama con el id # " + newCama.getId() + " no ha sido creada porque ya existe");

			dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
			dialogPane.getStyleClass().add("dialogs");

			alert.showAndWait();

			throw new CamaException("La Cama con el id # " + newCama.getId() + " no se puede guardar porque ya existe");

		} else {
			cama = new Cama();
			cama.setId(newCama.getId());
			cama.setEstado(newCama.getEstado());
			cama.setTipoCama(newCama.getTipoCama());
			cama.setIdHabitacion(newCama.getIdHabitacion());
			getListaCamas().add(cama);
		}

		return cama;
	}

	private boolean verificarCamaExiste(String id) {

		for (Cama cama : getListaCamas()) {
			if (cama.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}

	public Cama obtenerCama(String id) {

		Cama camaEncontrada = null;
		if (id != null) {
			for (Cama cama : getListaCamas()) {
				if (cama.getId().equals(id))
					camaEncontrada = cama;
			}

		}
		return camaEncontrada;
	}

	public void updateCama(String id, Cama newCama) {

		Cama camaEncontrada = obtenerCama(id);
		if (camaEncontrada != null) {
			camaEncontrada.setId(newCama.getId());
			camaEncontrada.setEstado(newCama.getEstado());
			camaEncontrada.setTipoCama(newCama.getTipoCama());
			camaEncontrada.setIdHabitacion(newCama.getIdHabitacion());
		}

	}

	public void deleteCama(String id) {

		if (id != null) {
			for (Cama cama : getListaCamas()) {
				if (cama.getId().equals(id))
					getListaCamas().remove(cama);
				System.out.println("Reserva eliminada correctamente");
			}

		}

	}

	// ------------------FIN-----------------------------------------

	public void crearHabitaciones() {

		for (int i = 1; i <= 10; i++) {
			Habitacion doble = new Habitacion("D" + i, new ArrayList<>(), // La lista de camas se agregará según tus
																			// necesidades
					Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.DOBLE);

			getListaHabitaciones().add(doble);

			Habitacion sencilla = new Habitacion("S" + i, new ArrayList<>(), // La lista de camas se agregará según
																				// tus necesidades
					Estado.FUNCIONANDO, Disponibilidad.DISPONIBLE, TipoHabitacion.SENCILLA);

			getListaHabitaciones().add(sencilla);

		}
	}

	// ---------------------------------CRUD
	// USER-----------------------------------------------

	public Usuario crearUsuario(Usuario usuario) throws Exception {

		Usuario user = new Usuario();
		boolean userExist = false;

		userExist = verificarUsuarioExiste(usuario.getCedula());

		if (userExist) {
			throw new Exception("El usuario " + usuario.getCedula() + "no se ha podido crear debido a que ya existe");
		} else {

			user.setCedula(usuario.getCedula());
			user.setEmail(usuario.getEmail());
			user.setTelefono(usuario.getTelefono());

			listaUsuarios.add(user);
		}
		return user;

	}

	public Usuario obtenerUsuario(String id) {

		Usuario userFound = null;
		if (id != null) {
			for (Usuario usuario : listaUsuarios) {
				if (usuario.getCedula().equals(id))
					userFound = usuario;
			}

		}
		return userFound;

	}

	public void updateUer(Usuario usuario) {

		Usuario userFound = obtenerUsuario(usuario.getCedula());
		if (userFound != null) {
			userFound.setCedula(usuario.getCedula());
			userFound.setEmail(usuario.getEmail());
			userFound.setTelefono(usuario.getTelefono());
			userFound.setListaReservas(usuario.getListaReservas());
		}

	}

	public void deleteUser(String id) {

		if (id != null) {
			for (Usuario usuario : listaUsuarios) {
				if (usuario.getCedula().equals(id))
					listaHabitaciones.remove(usuario);
				System.out.println("habitacion eliminada correctamente");
			}

		}

	}

	public boolean verificarUsuarioExiste(String id) {
		for (Usuario user : getListaUsuarios()) {
			if (user.getCedula().equals(id)) {
				return true;
			}
		}

		return false;
	}

//	-------metodos------

	public Usuario getUser(String cedula) {

		Usuario usuario = null;

		try {

			for (Usuario user : listaUsuarios) {

				if (user.getCedula().equals(cedula))
					usuario = user;
			}
		} catch (Exception e) {

		}

		return usuario;
	}
}
