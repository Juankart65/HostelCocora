package model;

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
	
	
	public Habitacion crearHabitacion (String id, ArrayList<Cama>listaCamas, Estado estado, Disponibilidad disponibilidad, TipoHabitacion tipoHabitacion) throws Exception {
		
		Habitacion habitacion = null;
		boolean habitacionExiste = false;
		
		habitacionExiste = verificarHabitacionExiste(id);
		
		if(habitacionExiste) {
			throw new Exception("la habitacion "+ id + "no se ha podido crear debido a que ya existe");
		}else {
			habitacion=  new Habitacion();
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
		if(id!=null) {
			for (Habitacion habitacion : listaHabitaciones) {
				if(habitacion.getId().equals(id)) habitacionEncontada = habitacion;
			}
			
		}
		return habitacionEncontada;
		
	}
	
	public void updateHabitacion(String id, Habitacion newHabitacion) {
		
		
		Habitacion habitacionEncontada = obtenerHabitacion(id);
		if(habitacionEncontada != null) {
			habitacionEncontada.setId(newHabitacion.getId());
			habitacionEncontada.setListaCamas(newHabitacion.getListaCamas());
			habitacionEncontada.setDisponibilidad(newHabitacion.getDisponibilidad());
			habitacionEncontada.setEstado(newHabitacion.getEstado());
			habitacionEncontada.setTipoHabticacion(newHabitacion.getTipoHabticacion());
		}
		
	}
	
public void deleteHabitacion(String id) {
		
		if(id!=null) {
			for (Habitacion habitacion : listaHabitaciones) {
				if(habitacion.getId().equals(id)) listaHabitaciones.remove(habitacion);
				System.out.println("habitacion eliminada correctamente");
			}
			
		}
		
		
	}
	
	
public boolean verificarHabitacionExiste(String id) {
	for (Habitacion habitacion : listaHabitaciones) {
		if(habitacion.getId().equals(id)) {
			return true;
		}
	}
	
	return false;
	}


}
