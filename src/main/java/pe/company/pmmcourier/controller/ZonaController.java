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

import pe.company.pmmcourier.model.Zona;
import pe.company.pmmcourier.service.ZonaService;

@RestController
@RequestMapping("/Zona")
public class ZonaController {
	
	@Autowired
	private ZonaService service;

	public ZonaController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Zona> zona = service.findAll();
		return new ResponseEntity<>(zona, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer zonaId){
			Zona zona = service.findById(zonaId);
		if(zona == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro una zona con ese id");
		}
		return new ResponseEntity<>(zona, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Zona zona){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(zona);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la zona");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{zonaID}")
	public ResponseEntity<?> editar(@PathVariable Integer zonaID, @RequestBody Zona zona){
		
		Zona zonaActual = service.findById(zonaID);
		
		if(zonaActual != null) {
			
			zonaActual.setZona(zona.getZona());
			
			service.update(zonaActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la zona");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{zonaID}")
	public ResponseEntity<?> borrar(@PathVariable Integer zonaID){
		Zona zona= service.findById(zonaID);
		
		if(zona!=null) {
			
			service.delete(zonaID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente la zona");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
