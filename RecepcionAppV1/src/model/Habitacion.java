package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.App;

public class Habitacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<Cama> listaCamas = new ArrayList<Cama>();
	private Estado estado;
	private Disponibilidad disponibilidad;
	private TipoHabitacion tipoHabticacion;

//	App app = new App();
	
	

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

	public Habitacion(String id, Estado estado, Disponibilidad disponibilidad, TipoHabitacion tipoHabticacion) {
		super();
		this.id = id;
		this.estado = estado;
		this.disponibilidad = disponibilidad;
		this.tipoHabticacion = tipoHabticacion;

//		repartirCamas();
	}

//	public void repartirCamas() {
//	    if (this.tipoHabticacion.equals(TipoHabitacion.SENCILLA)) {
//	        for (Cama cama : app.hotel.getListaCamas()) {
//	            if (cama.getEstado().equals(Estado.FUNCIONANDO) &&
//	                cama.getTipoCama().equals(TipoCama.INDIVIDUAL) &&
//	                listaCamas.size() < 1) {
//	                listaCamas.add(cama);
//	                
//	                cama.setEstado(Estado.MANTENIMIENTO);
//	                cama.setIdHabitacion(id);
//	            }
//	        }
//	    } else {
//	        for (Cama cama : app.hotel.getListaCamas()) {
//	            if (cama.getEstado().equals(Estado.FUNCIONANDO) &&
//	                cama.getTipoCama().equals(TipoCama.DOBLE) &&
//	                listaCamas.size() < 1) {
//	                listaCamas.add(cama);
//	                cama.setIdHabitacion(id);
//	            }
//	        }
//	    }
//	}
}
