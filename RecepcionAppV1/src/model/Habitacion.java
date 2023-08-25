package model;

import java.util.ArrayList;

public class Habitacion {

	private String id;
	private ArrayList<Cama> listaCamas = new ArrayList<Cama>();
	private Estado estado;
	private Disponibilidad disponibilidad;
	private TipoHabitacion tipoHabticacion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Cama> getListaCamas() {
		return listaCamas;
	}

	public void setListaCamas(ArrayList<Cama> listaCamas) {
		this.listaCamas = listaCamas;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public TipoHabitacion getTipoHabticacion() {
		return tipoHabticacion;
	}

	public void setTipoHabticacion(TipoHabitacion tipoHabticacion) {
		this.tipoHabticacion = tipoHabticacion;
	}

	public Habitacion() {
		super();
	}

	public Habitacion(String id, ArrayList<Cama> listaCamas, Estado estado, Disponibilidad disponibilidad,
			TipoHabitacion tipoHabticacion) {
		super();
		this.id = id;
		this.listaCamas = listaCamas;
		this.estado = estado;
		this.disponibilidad = disponibilidad;
		this.tipoHabticacion = tipoHabticacion;
	}
	
	
	
	

}
