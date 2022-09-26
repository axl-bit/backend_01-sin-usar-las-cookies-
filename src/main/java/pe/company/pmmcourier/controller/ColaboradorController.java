package pe.company.pmmcourier.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.company.pmmcourier.model.Colaborador;
import pe.company.pmmcourier.service.ColaboradorService;

@RestController
@RequestMapping("/Colaborador")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService service;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public ColaboradorController() {
		
	}
	
		
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Collection<Colaborador> colaborador = service.findAll();
		return new ResponseEntity<>(colaborador, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable(name = "id") Integer colabId){
		Colaborador colaborador = service.findById(colabId);
		if(colaborador == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro colaborador con ese id");
		}
		return new ResponseEntity<>(colaborador, HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> agregar(@RequestBody Colaborador colaborador){
		try {
		
			System.out.println("iniciando proceso de adicion de colaborador");
					
		String pwd = colaborador.getContrasena();
		
		String encodedPwd = passwordEncoder.encode(pwd);
		
		colaborador.setContrasena(encodedPwd);
		
		service.insert(colaborador);
		
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("codigo", "OK");
		respuesta.put("mensaje", "se creo correctamente el colaborador");
		
		return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		catch (Exception e) {
						
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "NO_OK");
			respuesta.put("mensaje", "No se creo correctamente el colaborador, error: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/editar/{colabId}")
	public ResponseEntity<?> editar(@PathVariable Integer colabId, @RequestBody Colaborador colaborador){
		
		Colaborador colaboradorActual = service.findById(colabId);
		
		if(colaboradorActual != null) {
			
			colaboradorActual.setDnicolaborador(colaborador.getDnicolaborador());
			colaboradorActual.setNombreC(colaborador.getNombreC());
			colaboradorActual.setUsuario(colaborador.getUsuario());
			
			colaboradorActual.setContrasena(
					passwordEncoder.encode(colaborador.getContrasena())
					);
			
			
			colaboradorActual.setTipo_colaborador(colaborador.getTipo_colaborador());
			
			service.update(colaboradorActual);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se actualizo correctamente el colaborador");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{colabId}")
	public ResponseEntity<?> borrar(@PathVariable Integer colabId){
		Colaborador colaborador= service.findById(colabId);
		
		if(colaborador!=null) {
			
			service.delete(colabId);
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("codigo", "OK");
			respuesta.put("mensaje", "se ha eliminado correctamente el colaborador");
			
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
