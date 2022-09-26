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

import pe.company.pmmcourier.model.Distrito;
import pe.company.pmmcourier.service.DistritoService;

@RestController
@RequestMapping("/Distrito")
public class DistritoController {
	
	@Autowired
	private DistritoService service;
	
	public DistritoController() {
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Distrito> distrito = service.findAll();
		return new ResponseEntity<>(distrito, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer distId){
		Distrito distrito = service.findById(distId);
		if(distrito == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro distrito con ese id");
		}
		return new ResponseEntity<>(distrito, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Distrito distrito){
		try {
			
			service.insert(distrito);
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente el distrito");
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
			
		}catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "Error");
			respuesta.put("mensaje", "Hubo un error al agregar distrito: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{distId}")
	public ResponseEntity<?> editar(@PathVariable Integer distId, @RequestBody Distrito distrito){
		
		Distrito distritoActual = service.findById(distId);
		
		if(distritoActual != null) {	
			
			distritoActual.setDistrito(distrito.getDistrito());
			distritoActual.setTarifa(distrito.getTarifa());
			
			service.update(distritoActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el distrito");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{distId}")
	public ResponseEntity<?> borrar(@PathVariable Integer distId){
		Distrito distrito= service.findById(distId);
		
		if(distrito!=null) {
			service.delete(distId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

}
