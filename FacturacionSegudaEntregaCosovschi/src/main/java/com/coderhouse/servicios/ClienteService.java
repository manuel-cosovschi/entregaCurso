package com.coderhouse.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.entidades.Cliente;
import com.coderhouse.repositorios.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente getCliente(int id) {
		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente crearCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente actualizarCliente(int id, Cliente cliente) {
		if (clienteRepository.existsById(id)) {
			cliente.setId(id);
			return clienteRepository.save(cliente);
		}
		return null;
	}

	public void eliminarCliente(int id) {
		clienteRepository.deleteById(id);
	}
}
