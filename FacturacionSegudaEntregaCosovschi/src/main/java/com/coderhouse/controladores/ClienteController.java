package com.coderhouse.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.entidades.Cliente;
import com.coderhouse.servicios.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public Cliente getCliente(@PathVariable int id) {
		return clienteService.getCliente(id);
	}

	@PostMapping
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		return clienteService.crearCliente(cliente);
	}

	@PutMapping("/{id}")
	public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
		return clienteService.actualizarCliente(id, cliente);
	}

	@DeleteMapping("/{id}")
	public void eliminarCliente(@PathVariable int id) {
		clienteService.eliminarCliente(id);
	}
}
