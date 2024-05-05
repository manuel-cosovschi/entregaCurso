package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderhouse.entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	// Aquí puedes agregar métodos adicionales para consultas específicas si es
	// necesario
}
