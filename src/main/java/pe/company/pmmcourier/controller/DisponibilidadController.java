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

import pe.company.pmmcourier.model.Disponibilidad;
import pe.company.pmmcourier.service.DisponibilidadService;


@RestController
@RequestMapping("/Disponibilidad")
public class DisponibilidadController {

	@Autowired
	private DisponibilidadService service;
	
	public DisponibilidadController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Disponibilidad> dispo = service.findAll();
		return new ResponseEntity<>(dispo, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer dispoId){
		Disponibilidad dispo = service.findById(dispoId);
		if(dispo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro una disponibilidad con ese id");
		}
		return new ResponseEntity<>(dispo, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Disponibilidad dispo){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(dispo);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la Disponibilidad");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{dispoId}")
	public ResponseEntity<?> editar(@PathVariable Integer dispoId, @RequestBody Disponibilidad dispo){
		
		Disponibilidad dispoActual = service.findById(dispoId);
		
		if(dispoActual != null) {
			
			dispoActual.setDisponibilidad(dispo.getDisponibilidad());
			
			service.update(dispoActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la Disponibilidad");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{dispoId}")
	public ResponseEntity<?> borrar(@PathVariable Integer dispoId){
		Disponibilidad dispo = service.findById(dispoId);
		
		if(dispo!=null) {
			
			service.delete(dispoId);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente la Disponibilidad");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
}
