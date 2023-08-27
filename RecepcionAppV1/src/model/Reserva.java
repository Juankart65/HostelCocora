package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import application.App;

public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private Usuario usuario;
	private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
	private Factura factura;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	

	public Reserva(Usuario usuario, ArrayList<Habitacion> listaHabitaciones, Factura factura) {
		super();
		this.id =  App.generateRandomId();
		this.usuario = usuario;
		this.listaHabitaciones = listaHabitaciones;
		this.factura = factura;
		
		
		calcularSubValorFactura();
		calcularValorTotal();
		
	}

	public String getId() {
		
		return id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
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
	
	
	private void calcularSubValorFactura() {
		
		double subValor = 0;
		
		//una cama extra la doble puede tener dos individuales o u
		
		for (Habitacion habitacion : listaHabitaciones) {
			if(habitacion.getTipoHabticacion() == TipoHabitacion.DOBLE) {
				
				if(habitacion.getListaCamas().size() == 1 ) {
					subValor+=120000;
				}else {
					
					subValor+=80000;
					subValor += habitacion.getListaCamas().stream()
							.mapToInt(cama -> cama.getTipoCama() == TipoCama.INDIVIDUAL ? 20000 : 0)
							.sum();	
				}
			}else {
				subValor += 40000; 
				subValor += habitacion.getListaCamas().stream()
	                    .mapToInt(cama -> cama.getTipoCama() == TipoCama.INDIVIDUAL ? 20000 : 0)
	                    .sum();
			}
		
		}
		factura.setSubValor(subValor);
	}
	
	
	private void calcularValorTotal() {
		double valor = 0;
		
		int cantidadDias = calcularDiferenciaEnDias(fechaEntrada, fechaSalida);
		valor = factura.getSubValor() * cantidadDias;
		
		factura.setValorTotal(valor);
	}
	
	public int calcularDiferenciaEnDias(LocalDate fechaInicial, LocalDate fechaFinal) {
	    long diferenciaDias = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
	    return (int) diferenciaDias;
	}
	
	
	
	

}
