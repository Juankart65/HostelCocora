package co.edu.uniquindio.hostelcocora.recepcion.model;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {

	private String cedula;
	private String telefono;
	private String email;
	private ArrayList<Reserva> listaReservas;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", telefono=" + telefono + ", email=" + email + ", listaReservas="
				+ listaReservas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cedula, other.cedula);
	}

}
