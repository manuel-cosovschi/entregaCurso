// LineaVentaRequest.java
package com.coderhouse.dto;

public class LineaVentaRequest {
	private int cantidad;
	private ProductoRequest producto;

	// Getters y setters
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoRequest getProducto() {
		return producto;
	}

	public void setProducto(ProductoRequest producto) {
		this.producto = producto;
	}
}
