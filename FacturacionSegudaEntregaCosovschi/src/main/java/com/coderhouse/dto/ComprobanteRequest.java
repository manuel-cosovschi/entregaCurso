// ComprobanteRequest.java
package com.coderhouse.dto;

import java.util.List;

public class ComprobanteRequest {
	private ClienteRequest cliente;
	private List<LineaVentaRequest> lineas;

	// Getters y setters
	public ClienteRequest getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRequest cliente) {
		this.cliente = cliente;
	}

	public List<LineaVentaRequest> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaVentaRequest> lineas) {
		this.lineas = lineas;
	}
}
