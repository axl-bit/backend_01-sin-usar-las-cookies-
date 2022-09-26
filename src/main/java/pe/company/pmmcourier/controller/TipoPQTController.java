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

import pe.company.pmmcourier.model.TipoPQT;
import pe.company.pmmcourier.service.TipoPQTService;

@RestController
@RequestMapping("/TipoPQT")
public class TipoPQTController {
	
	@Autowired
	private TipoPQTService service;
	
	public TipoPQTController() {
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<TipoPQT> tipopqt = service.findAll();
		return new ResponseEntity<>(tipopqt, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer tipopqtId){
		TipoPQT tipopqt = service.findById(tipopqtId);
		if(tipopqt == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro tipopqt con ese id");
		}
		return new ResponseEntity<>(tipopqt, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody TipoPQT tipopqt){
		service.insert(tipopqt);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("codigo", "OK");
		respuesta.put("mensaje", "se creo correctamente el tipo de paquete");
		return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{TipoPQTID}")
	public ResponseEntity<?> editar(@PathVariable Integer TipoPQTID, @RequestBody TipoPQT tipopqt){
		
		TipoPQT tipopqtoActual = service.findById(TipoPQTID);
		
		if(tipopqtoActual != null) {
			
			tipopqtoActual.setTipoPQT(tipopqt.getTipoPQT());
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el tipo de paquete");
			
			service.update(tipopqtoActual);
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{tipopqtId}")
	public ResponseEntity<?> borrar(@PathVariable Integer tipopqtId){
		TipoPQT tipopqt= service.findById(tipopqtId);
		
		if(tipopqt!=null) {
			service.delete(tipopqtId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

}
