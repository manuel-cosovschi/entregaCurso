package com.coderhouse.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "precio")
	private double precio;
	@Column(name = "stock")
	private int stockDisponible;

	public Producto() {

	}

	public Producto(int id, String nombre, double precio, int stockDisponible) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stockDisponible = stockDisponible;
	}

	public Producto(String nombre, double precio, int stockDisponible) {
		this.nombre = nombre;
		this.precio = precio;
		this.stockDisponible = stockDisponible;
	}

	// Método abstracto para calcular el precio total del producto
	public abstract double calcularPrecioTotal(int cantidad);

	// Método para actualizar el stock disponible
	public void actualizarStock(int cantidadVendida) {
		if (cantidadVendida <= stockDisponible) {
			stockDisponible -= cantidadVendida;
			System.out.println("Stock actualizado: " + stockDisponible);
		} else {
			System.out.println("No hay suficiente stock disponible");
		}
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
}
