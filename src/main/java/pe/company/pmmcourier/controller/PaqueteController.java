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

import pe.company.pmmcourier.model.Paquete;
import pe.company.pmmcourier.service.PaqueteService;


@RestController
@RequestMapping("/Paquete")
public class PaqueteController {
	
	@Autowired
	private PaqueteService service;

	public PaqueteController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Paquete> pqt = service.findAll();
		return new ResponseEntity<>(pqt, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer pqtId){
		
		Paquete pqt = service.findById(pqtId);
		
		if(pqt == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro un paquete con ese id");
		}
		return new ResponseEntity<>(pqt, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Paquete pqt){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(pqt);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente el paquete con el codigo: " +  pqt.getPaquete_id().toString());
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{pqtID}")
	public ResponseEntity<?> editar(@PathVariable Integer pqtID, @RequestBody Paquete pqt){
		
		Paquete pqtActual = service.findById(pqtID);
		
		if(pqtActual != null) {
			
			pqtActual.setDescripcionpqt(pqt.getDescripcionpqt());
			pqtActual.setCantidadpqt(pqt.getCantidadpqt());
			
			service.update(pqtActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el paquete");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{pqtID}")
	public ResponseEntity<?> borrar(@PathVariable Integer pqtID){
		Paquete pqt= service.findById(pqtID);
		
		if(pqt!=null) {
			
			service.delete(pqtID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente el paquete");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
