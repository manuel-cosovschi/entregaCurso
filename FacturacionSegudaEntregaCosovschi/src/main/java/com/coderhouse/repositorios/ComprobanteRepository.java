// ComprobanteRepository.java
package com.coderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.entidades.Comprobante;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
