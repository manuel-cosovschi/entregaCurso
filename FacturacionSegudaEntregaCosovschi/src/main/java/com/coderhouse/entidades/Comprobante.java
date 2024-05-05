// Comprobante.java
package com.coderhouse.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "comprobantes")
public class Comprobante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL)
	private List<DetalleVenta> detallesVenta = new ArrayList<>();

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "precio_total")
	private double precioTotal;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVenta> getDetallesVenta() {
		return detallesVenta;
	}

	public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	// Método para agregar un detalle de venta al comprobante
	public void agregarDetalle(DetalleVenta detalleVenta) {
		detallesVenta.add(detalleVenta);
		detalleVenta.setComprobante(this); // Utiliza el método setComprobante en lugar de setVenta
	}

	// Método para calcular el precio total del comprobante
	public double calcularPrecioTotal() {
		double total = 0.0;
		for (DetalleVenta detalleVenta : detallesVenta) {
			total += detalleVenta.getSubtotal();
		}
		return total;
	}
}
