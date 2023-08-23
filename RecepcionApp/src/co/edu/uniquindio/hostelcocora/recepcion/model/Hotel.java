package co.edu.uniquindio.hostelcocora.recepcion.model;

import java.util.ArrayList;

public class Hotel {

	private String nombre;

	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private ArrayList<Habitacion> listaReservas = new ArrayList<>();
	private ArrayList<Habitacion> listaUsuarios = new ArrayList<>();

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

	public ArrayList<Habitacion> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<Habitacion> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public ArrayList<Habitacion> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Habitacion> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Hotel() {
		super();
	}

}
