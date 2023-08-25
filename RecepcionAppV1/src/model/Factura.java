package model;

import java.util.Objects;

public class Factura {

	private String id;
	private String fecha;
	private String subValor;
	private String valorTotal;
	
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

	public String getSubValor() {
		return subValor;
	}

	public void setSubValor(String subValor) {
		this.subValor = subValor;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
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
