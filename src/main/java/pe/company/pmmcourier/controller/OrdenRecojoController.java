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

import pe.company.pmmcourier.model.OrdenRecojo;
import pe.company.pmmcourier.service.OrdenRecojoService;

@RestController
@RequestMapping("/OrdenRecojo")
public class OrdenRecojoController {
	
	@Autowired
	private OrdenRecojoService service;

	public OrdenRecojoController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<OrdenRecojo> OR = service.findAll();
		return new ResponseEntity<>(OR, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer orID){
		OrdenRecojo OR = service.findById(orID);
		if(OR == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro una Orden de recojo con ese id");
		}
		return new ResponseEntity<>(OR, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody OrdenRecojo OR){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(OR);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la Orden de Recojo");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{orID}")
	public ResponseEntity<?> editar(@PathVariable Integer orID, @RequestBody OrdenRecojo OR){
		
		OrdenRecojo ORActual = service.findById(orID);
		
		if(ORActual != null) {
			
			ORActual.setDireccionR(OR.getDireccionR());
			ORActual.setFechaR(OR.getFechaR());
			ORActual.setMotorizadorecojo(OR.getMotorizadorecojo());
			
			service.update(ORActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la Orden de Recojo");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{orID}")
	public ResponseEntity<?> borrar(@PathVariable Integer orID){
		OrdenRecojo OR= service.findById(orID);
		
		if(OR!=null) {
			
			service.delete(orID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente la Orden de Recojo");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
}
