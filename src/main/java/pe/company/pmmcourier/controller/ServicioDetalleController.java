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
import pe.company.pmmcourier.model.ServicioDetalle;
import pe.company.pmmcourier.service.EstadoService;
import pe.company.pmmcourier.service.ServicioDetalleService;

@RestController
@RequestMapping("/DetalleServicio")
public class ServicioDetalleController {
	
	@Autowired
	private ServicioDetalleService service;
	
	@Autowired
	private EstadoService estadoService;

	public ServicioDetalleController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<ServicioDetalle> SD = service.findAll();
		return new ResponseEntity<>(SD, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer sdID){
		ServicioDetalle SD = service.findById(sdID);
		if(SD == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro una Orden de servicio con ese id");
		}
		return new ResponseEntity<>(SD, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody ServicioDetalle SD){
		try {
			
			System.out.println("entro!!!");
		
			Estado estado = new Estado();
			
			estado = estadoService.findById(1);
			
			SD.setEstado(estado);
			
			service.insert(SD);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente la Orden de servicio");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			//respuesta.put("recojo", SD.getOrdenrecojo().getRecojo_id().toString());
			//respuesta.put("entrega","entrega:  " +  SD.getOrdenentrega().getEntrega_id().toString());
			//respuesta.put("estado", SD.getEstado().getEntrega_id().toString());
			respuesta.put("mensaje", "Hubo un error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/editar/{sdID}")
	public ResponseEntity<?> editar(@PathVariable Integer sdID, @RequestBody ServicioDetalle SD){
		
		ServicioDetalle SDActual = service.findById(sdID);
		
		if(SDActual != null) {
			
			SDActual.setEstado(SD.getEstado());
			
			service.update(SDActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente la Orden de Servicio");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{oeID}")
	public ResponseEntity<?> borrar(@PathVariable Integer sdID){
		ServicioDetalle SD= service.findById(sdID);
		
		if(SD!=null) {
			
			service.delete(sdID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente la Orden de Servicio");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
