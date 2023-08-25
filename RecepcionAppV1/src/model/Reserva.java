package model;

public class Reserva {

	private String id;
	private Usuario usuario;
	private Habitacion habitacion;
	private Factura factura;
	private String fechaEntrada;
	private String fechaSalida;
	
	

	public Reserva(String id, Usuario usuario, Habitacion habitacion, Factura factura, String fechaEntrada,
			String fechaSalida) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.habitacion = habitacion;
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

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
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

}
