package model;

import java.util.ArrayList;

public class Hotel {

	private String nombre;

	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private ArrayList<Reserva> listaReservas = new ArrayList<>();
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private static Usuario usuarioActual;
	

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
	
	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public static void setUsuarioActual(Usuario usuarioActual) {
		Hotel.usuarioActual = usuarioActual;
	}

	public Hotel() {
		super();
	}

	public boolean verificarCuenta(String usuario, String contrasenia) {
		for (Usuario usuario1 : getListaUsuarios()) {
			System.out.println(usuario1);
			if (usuario1.getUsuario().equals(usuario) && usuario1.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		return false;
	}

	public Usuario getUsuario(String usuario, String contrasenia) {
		for (Usuario usuario1 : getListaUsuarios()) {
			if (usuario1.getUsuario() == usuario && usuario1.getContrasenia().equals(contrasenia)) {
				return usuario1;
			}
		}
		return null;
	}





}
