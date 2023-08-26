package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public  int calcularDiferenciaEnDias(String fechaInicial, String fechaFinal) {
        String[] partesFechaInicial = fechaInicial.split("/");
        String[] partesFechaFinal = fechaFinal.split("/");

        int diaInicial = Integer.parseInt(partesFechaInicial[0]);
        int mesInicial = Integer.parseInt(partesFechaInicial[1]);
        int anioInicial = Integer.parseInt(partesFechaInicial[2]);

        int diaFinal = Integer.parseInt(partesFechaFinal[0]);
        int mesFinal = Integer.parseInt(partesFechaFinal[1]);
        int anioFinal = Integer.parseInt(partesFechaFinal[2]);

        LocalDate fechaInicialLocal = LocalDate.of(anioInicial, mesInicial, diaInicial);
        LocalDate fechaFinalLocal = LocalDate.of(anioFinal, mesFinal, diaFinal);

        long diferenciaDias = ChronoUnit.DAYS.between(fechaInicialLocal, fechaFinalLocal);
        return (int) diferenciaDias;
    }
	
	

}
