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

import pe.company.pmmcourier.model.Provincia;
import pe.company.pmmcourier.service.ProvinciaService;

@RestController
@RequestMapping("/Provincia")
public class ProvinciaController {

	@Autowired
	private ProvinciaService service;
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Provincia> tc = service.findAll();
		return new ResponseEntity<>(tc , HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer provID){
		Provincia tc = service.findById(provID);
		if(tc == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro provincia con ese id");
		}
		return new ResponseEntity<>(tc, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Provincia provincia){
		try {
						
			service.insert(provincia);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la provincia");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "provincia: " + provincia.getProvincia());
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{provID}")
	public ResponseEntity<?> editar(@PathVariable Integer TipoCID, @RequestBody Provincia provincia){
		
		Provincia provActual = service.findById(TipoCID);
		
		if(provActual != null) {
			
			provActual.setProvincia(provincia.getProvincia());
			
			service.update(provActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la provincia");
			
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{provID}")
	public ResponseEntity<?> borrar(@PathVariable Integer provID){
		
		Provincia provActual= service.findById(provID);
		
		if(provActual!=null) {
			
			service.delete(provID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se elimino correctamente la provincia");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}