package com.coderhouse.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fecha")
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> detallesVenta = new ArrayList<>();

	// Constructor
	public Venta() {
		this.fecha = new Date(); // La fecha de la venta será la fecha actual al momento de creación
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	// Método para agregar un detalle de venta a la venta
	public void agregarDetalle(DetalleVenta detalleVenta) {
		detallesVenta.add(detalleVenta);
		detalleVenta.setVenta(this);
	}
}
