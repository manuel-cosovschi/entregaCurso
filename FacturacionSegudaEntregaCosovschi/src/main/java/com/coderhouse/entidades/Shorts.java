package com.coderhouse.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "shorts")
public class Shorts extends Producto {

	@Column(name = "talla")
	private String talla;

	@Column(name = "color")
	private String color;

	public Shorts() {
		super();
	}

	public Shorts(String nombre, double precio, int stockDisponible, String talla, String color) {
		super(nombre, precio, stockDisponible);
		this.talla = talla;
		this.color = color;
	}

	// Getters y setters específicos para los shorts
	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public double calcularPrecioTotal(int cantidad) {
		// Implementación del método para calcular el precio total de los shorts
		return getPrecio() * cantidad;
	}
}
