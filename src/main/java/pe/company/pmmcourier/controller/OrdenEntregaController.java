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

import pe.company.pmmcourier.model.OrdenEntrega;
import pe.company.pmmcourier.service.OrdenEntregaService;

@RestController
@RequestMapping("/OrdenEntrega")
public class OrdenEntregaController {

	@Autowired
	private OrdenEntregaService service;
	
	public OrdenEntregaController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<OrdenEntrega> OE = service.findAll();
		return new ResponseEntity<>(OE, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer oeID){
		OrdenEntrega OE = service.findById(oeID);
		if(OE == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro una Orden de entrega con ese id");
		}
		return new ResponseEntity<>(OE, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody OrdenEntrega OE){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(OE);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la Orden de entrega");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{oeID}")
	public ResponseEntity<?> editar(@PathVariable Integer oeID, @RequestBody OrdenEntrega OE){
		
		OrdenEntrega OEActual = service.findById(oeID);
		
		if(OEActual != null) {
			
			OEActual.setDireccionE(OE.getDireccionE());
			OEActual.setFechaE(OE.getFechaE());
			OEActual.setMotorizadoentrega(OE.getMotorizadoentrega());
			
			service.update(OEActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la Orden de entrega");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{oeID}")
	public ResponseEntity<?> borrar(@PathVariable Integer oeID){
		OrdenEntrega OE= service.findById(oeID);
		
		if(OE!=null) {
			
			service.delete(oeID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente la Orden de entrega");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
