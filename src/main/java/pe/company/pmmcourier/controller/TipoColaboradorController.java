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

import pe.company.pmmcourier.model.TipoColaborador;
import pe.company.pmmcourier.service.TipoColaboradorService;

@RestController
@RequestMapping("/TipoColaborador")
public class TipoColaboradorController {
	
	@Autowired
	private TipoColaboradorService service;
	
	public TipoColaboradorController() {
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<TipoColaborador> tc = service.findAll();
		return new ResponseEntity<>(tc , HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer TipoCID){
		TipoColaborador tc = service.findById(TipoCID);
		if(tc == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro tarifa con ese id");
		}
		return new ResponseEntity<>(tc, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody TipoColaborador TipoColaborador){
		try {
						
			service.insert(TipoColaborador);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se creo correctamente el tipo de Colaborador");
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "Colaborador: " + TipoColaborador.getTipo_colaborador());
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{TarifaID}")
	public ResponseEntity<?> editar(@PathVariable Integer TipoCID, @RequestBody TipoColaborador TipoColaborador){
		
		TipoColaborador tcActual = service.findById(TipoCID);
		
		if(tcActual != null) {
			
			tcActual.setTipo_colaborador(TipoColaborador.getTipo_colaborador());
			
			service.update(tcActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el tipo de colaborador");
			
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{tarifaId}")
	public ResponseEntity<?> borrar(@PathVariable Integer TipoCID){
		
		TipoColaborador tc= service.findById(TipoCID);
		
		if(tc!=null) {
			
			service.delete(TipoCID);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se elimino correctamente el tipo de colaborador");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	

}
