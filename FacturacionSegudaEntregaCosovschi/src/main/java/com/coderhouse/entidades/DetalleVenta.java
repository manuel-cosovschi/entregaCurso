package com.coderhouse.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "venta_id", nullable = false)
	private Venta venta;

	@ManyToOne
	@JoinColumn(name = "comprobante_id", nullable = true)
	private Comprobante comprobante;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	@Column(name = "cantidad")
	private int cantidad;

	// Constructor
	public DetalleVenta() {
	}

	// Constructor con Venta, Producto y cantidad
	public DetalleVenta(Venta venta, Comprobante comprobante, Producto producto, int cantidad) {
		this.venta = venta;
		this.comprobante = comprobante;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// Constructor con Producto y cantidad
	public DetalleVenta(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return producto.getPrecio() * cantidad;
	}
}