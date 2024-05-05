package com.coderhouse.servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.ComprobanteRequest;
import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Comprobante;
import com.coderhouse.entidades.DetalleVenta;
import com.coderhouse.entidades.Producto;
import com.coderhouse.repositorios.ClienteRepository;
import com.coderhouse.repositorios.ComprobanteRepository;
import com.coderhouse.repositorios.ProductRepository;
import com.coderhouse.validaciones.ValidacionesComprobante;
import com.coderhouse.dto.ClienteRequest;
import com.coderhouse.dto.LineaVentaRequest;

@Service
public class ComprobanteService {

	@Autowired
	private ComprobanteRepository comprobanteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProductRepository productoRepository;

	@Autowired
	private ValidacionesComprobante validacionesComprobante;

	public Comprobante crearComprobante(ComprobanteRequest request) {
		String errores = validacionesComprobante.validarComprobante(request);
		if (!errores.isEmpty()) {
			return null;
		}

		ClienteRequest clienteRequest = request.getCliente();
		Cliente cliente = clienteRepository.findById(clienteRequest.getClienteid()).orElse(null);
		if (cliente == null) {
			return null;
		}

		Comprobante comprobante = new Comprobante();
		comprobante.setCliente(cliente);

		Date fecha = obtenerFechaComprobante();
		comprobante.setFecha(fecha);

		List<LineaVentaRequest> lineas = request.getLineas();
		for (LineaVentaRequest linea : lineas) {
			Producto producto = obtenerProducto(linea.getProducto().getProductoid());
			if (producto == null) {
				return null;
			}

			if (linea.getCantidad() > producto.getStockDisponible()) {
				return null;
			}

			DetalleVenta detalleVenta = new DetalleVenta(producto, linea.getCantidad());
			comprobante.agregarDetalle(detalleVenta);

			producto.actualizarStock(linea.getCantidad());
		}

		double precioTotal = comprobante.calcularPrecioTotal();
		comprobante.setPrecioTotal(precioTotal);

		comprobante = comprobanteRepository.save(comprobante);

		return comprobante;
	}

	private Date obtenerFechaComprobante() {
		try {
			URI uri = URI.create("http://worldclockapi.com/api/json/utc/now");
			URL url = uri.toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder response = new StringBuilder();

			while ((output = br.readLine()) != null) {
				response.append(output);
			}

			conn.disconnect();

			// Parsear el JSON para obtener la fecha
			String jsonResponse = response.toString();
			String dateTimeString = jsonResponse.substring(jsonResponse.indexOf("currentDateTime\":\"") + 18,
					jsonResponse.indexOf("\",\""));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date fecha = formatter.parse(dateTimeString);

			return fecha;
		} catch (IOException | RuntimeException e) {
			// Manejar cualquier excepción que pueda ocurrir al obtener la fecha del
			// comprobante
			e.printStackTrace();
			return new Date(); // Retorna la fecha del sistema en caso de error
		} catch (Exception e) {
			e.printStackTrace();
			return new Date(); // Retorna la fecha del sistema en caso de error
		}
	}

	private Producto obtenerProducto(int productId) {
		// Lógica para obtener el producto por su ID desde la base de datos
		return productoRepository.findById(productId).orElse(null);
	}
}
