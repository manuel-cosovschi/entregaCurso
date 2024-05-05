/// ValidacionesComprobante.java
package com.coderhouse.validaciones;

import org.springframework.stereotype.Component;

import com.coderhouse.dto.ComprobanteRequest;
import com.coderhouse.dto.LineaVentaRequest;

@Component
public class ValidacionesComprobante {

	// Método para validar un comprobante
	public String validarComprobante(ComprobanteRequest request) {
		StringBuilder errores = new StringBuilder();

		// Verificar si el cliente existe
		if (request.getCliente() == null || request.getCliente().getClienteid() <= 0) {
			errores.append("El ID del cliente no es válido. ");
		}

		// Verificar si hay líneas de venta en el comprobante
		if (request.getLineas() == null || request.getLineas().isEmpty()) {
			errores.append("El comprobante debe tener al menos una línea de venta. ");
		} else {
			// Verificar cada línea de venta
			for (LineaVentaRequest linea : request.getLineas()) {
				// Verificar si la cantidad es válida
				if (linea.getCantidad() <= 0) {
					errores.append("La cantidad de productos en la línea de venta no es válida. ");
				}
				// Verificar si el producto es válido
				if (linea.getProducto() == null || linea.getProducto().getProductoid() <= 0) {
					errores.append("El producto en la línea de venta no es válido. ");
				}
			}
		}

		return errores.toString();
	}
}
