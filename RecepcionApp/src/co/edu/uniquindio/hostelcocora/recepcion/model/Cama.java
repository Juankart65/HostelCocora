package co.edu.uniquindio.hostelcocora.recepcion.model;

import java.util.Objects;

public class Cama {

	private String id;
	private Estado estado;
	private TipoCama tipoCama;
	private String idHabitacion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public TipoCama getTipoCama() {
		return tipoCama;
	}

	public void setTipoCama(TipoCama tipoCama) {
		this.tipoCama = tipoCama;
	}

	public String getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public Cama() {
		super();
	}

	@Override
	public String toString() {
		return "Cama [id=" + id + ", estado=" + estado + ", tipoCama=" + tipoCama + ", idHabitacion=" + idHabitacion
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cama other = (Cama) obj;
		return Objects.equals(id, other.id);
	}

}
