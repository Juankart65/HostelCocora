package model;

import java.util.Objects;

public class Factura {

	private String id;
	private String fecha;
	private double subValor;
	private double valorTotal;
	
//	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getSubValor() {
		return subValor;
	}

	public void setSubValor(double subValor) {
		this.subValor = subValor;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Factura() {
		super();
	}

	public Factura(String string, String string2, String string3, String string4) {
		super();
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
		Factura other = (Factura) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", fecha=" + fecha + ", subValor=" + subValor + ", valorTotal=" + valorTotal + "]";
	}
	
	
	public void calcularValorTotal() {
		
	}

}
