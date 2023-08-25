package model;

import java.util.ArrayList;

public class Reserva {

	private String id;
	private Usuario usuario;
	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private Factura factura;
	private String fechaEntrada;
	private String fechaSalida;
	
	

	public Reserva(String id, Usuario usuario, ArrayList<Habitacion> listaHabitaciones, Factura factura, String fechaEntrada,
			String fechaSalida) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.listaHabitaciones = listaHabitaciones;
		this.factura = factura;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public String getId() {
		return id;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public ArrayList<Habitacion> getListaHabitaciones() {
		return listaHabitaciones;
	}

	public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Reserva() {
		super();
	}
	
	private void calcularSubValorFactura(Factura factura) {
		
		double subValor = 0;
		
		for (Habitacion habitacion : listaHabitaciones) {
			if(habitacion.getTipoHabticacion() == TipoHabitacion.DOBLE) {
				subValor+=120000;
			}else {
				subValor+=80000;
			}
 
			
		}
		
		
		
	}
	
	private void calcularValorTotal() {
		
	}
	
	

}
