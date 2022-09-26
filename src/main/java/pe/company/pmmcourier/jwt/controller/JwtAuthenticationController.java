package pe.company.pmmcourier.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.company.pmmcourier.jwt.JwtRequest;
import pe.company.pmmcourier.jwt.JwtResponse;
import pe.company.pmmcourier.jwt.JwtTokenUtil;
//import pe.company.pmmcourier.service.UsuarioService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	//@Autowired
	//private UsuarioService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
	{
		this.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		//nuevo
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		//final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
		
	}
	
	private void authenticate(String username,String password) throws Exception
	{
			
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));;
        }catch(BadCredentialsException ex) {
            throw new Exception("Check Username / password again ! ", ex);
        }
	}
	
	
	
}
