package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderhouse.entidades.Producto;

public interface ProductRepository extends JpaRepository<Producto, Integer> {
	// Puedes agregar métodos personalizados de consulta si los necesitas
}
