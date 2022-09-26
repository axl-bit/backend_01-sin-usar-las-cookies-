package pe.company.pmmcourier.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.company.pmmcourier.model.Estado;
import pe.company.pmmcourier.service.EstadoService;

@RestController
@RequestMapping("/Estado")
public class EstadoController {
	
	@Autowired
	private EstadoService service;
	
	public EstadoController() {
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Estado> estado = service.findAll();
		return new ResponseEntity<>(estado, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer estadoId){
		Estado estado = service.findById(estadoId);
		if(estado == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro distrito con ese id");
		}
		return new ResponseEntity<>(estado, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Estado estado){
		service.insert(estado);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("codigo", "OK");
		respuesta.put("mensaje", "se creo correctamente el estado");
		return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{estadoId}")
	public ResponseEntity<?> editar(@PathVariable Integer estadoId, @RequestBody Estado estado){
		
		Estado estadoActual = service.findById(estadoId);
		if(estadoActual != null) {
			estadoActual.setEstado(estado.getEstado());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{estadoId}")
	public ResponseEntity<?> borrar(@PathVariable Integer estadoId){
		Estado estado= service.findById(estadoId);
		
		if(estado!=null) {
			service.delete(estadoId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

}
