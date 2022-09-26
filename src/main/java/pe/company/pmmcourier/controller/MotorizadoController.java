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

import pe.company.pmmcourier.model.Motorizado;
import pe.company.pmmcourier.service.MotorizadoService;

@RestController
@RequestMapping("/Motorizado")
public class MotorizadoController {
	
	@Autowired
	private MotorizadoService service;

	public MotorizadoController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Motorizado> moto = service.findAll();
		return new ResponseEntity<>(moto, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer motoID){
		Motorizado moto = service.findById(motoID);
		if(moto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro un motorizado con ese id");
		}
		return new ResponseEntity<>(moto, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Motorizado moto){
		try {
			
			System.out.println("entro!!!");
		
			service.insert(moto);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo agrego correctamente al motorizado");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Hubo un error: " + e );
			
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/editar/{motoID}")
	public ResponseEntity<?> editar(@PathVariable Integer motoID, @RequestBody Motorizado moto){
		
		Motorizado motoActual = service.findById(motoID);
		
		if(motoActual != null) {
			
			motoActual.setCodigoSoat(moto.getCodigoSoat());
			motoActual.setDisponibilidad(moto.getDisponibilidad());
			motoActual.setNroMatricula(moto.getNroMatricula());
			motoActual.setUltimaRT(moto.getUltimaRT());
			motoActual.setZona(moto.getZona());
			
			service.update(motoActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el motorizado");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{motoID}")
	public ResponseEntity<?> borrar(@PathVariable Integer motoID){
		Motorizado moto= service.findById(motoID);
		
		if(moto!=null) {
			
			service.delete(motoID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente el motorizado");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
