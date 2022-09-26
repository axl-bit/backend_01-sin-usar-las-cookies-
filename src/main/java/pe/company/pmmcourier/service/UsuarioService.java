package pe.company.pmmcourier.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Colaborador;
import pe.company.pmmcourier.model.TipoColaborador;
import pe.company.pmmcourier.repository.UsuarioRepositorio;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepositorio repo;
	
	/*
	@Autowired 
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
				
		Optional<Colaborador> Colaborador = repo.findByUsuario(username);
		
		if (Colaborador.isEmpty()) {
			
			throw new UsernameNotFoundException("Nombre de usuario \"" + username + "\" no encontrado");
			
		}else {
			
			Colaborador userData = repo.findById(Colaborador.get().getColaboradorID()).get();
			
			TipoColaborador tc = (TipoColaborador) userData.getTipo_colaborador();
			
			System.out.println("rol: " + tc.toString());
			
			Collection<GrantedAuthority> listaPermisos = new HashSet<>();
			
			listaPermisos.add(new SimpleGrantedAuthority(tc.getTipo_colaborador()));
			
			User springSessionUser = new User(userData.getUsuario(), userData.getContrasena(), listaPermisos);
			
			return springSessionUser;
		}
	}
}
		
