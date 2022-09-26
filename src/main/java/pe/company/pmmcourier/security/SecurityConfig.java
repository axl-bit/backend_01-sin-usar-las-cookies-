package pe.company.pmmcourier.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;

import pe.company.pmmcourier.jwt.JwtAuthenticationEntryPoint;
import pe.company.pmmcourier.jwt.JwtRequestFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	private JwtRequestFilter filter;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.userDetailService).passwordEncoder(passwordEncoder());
		
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				//Tarifas
				.antMatchers("/Tarifa/listar").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Tarifa/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Tarifa/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Tarifa/**").hasAnyAuthority("administrador")
				
				//Distrito
				.antMatchers("/Distrito/listar").permitAll()//hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Distrito/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Distrito/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Distrito/**").hasAnyAuthority("administrador")
				
				//Colaboradores
				.antMatchers("/Colaborador/listar").hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Colaborador/**").permitAll() //hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Colaborador/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Colaborador/**").hasAnyAuthority("administrador")
				
				//tipo_Colaboradores
				.antMatchers("/TipoColaborador/listar").hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/TipoColaborador/**").permitAll() //hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/TipoColaborador/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/TipoColaborador/**").hasAnyAuthority("administrador")
				
				//Zona
				.antMatchers("/Zona/listar").hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Zona/**").hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Zona/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Zona/**").hasAnyAuthority("administrador")
				
				//Disponibilidad
				.antMatchers("/Disponibilidad/listar").hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Disponibilidad/**").hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Disponibilidad/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Disponibilidad/**").hasAnyAuthority("administrador")
				
				//Paquete
				.antMatchers("/Paquete/listar").hasAuthority("administrador")
				.antMatchers("/Paquete/buscar/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Paquete/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Paquete/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Paquete/**").hasAnyAuthority("administrador")
				
				//Tipopaquete
				.antMatchers("/TipoPQT/listar").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/TipoPQT/**").hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/TipoPQT/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/TipoPQT/**").hasAnyAuthority("administrador")
				
				//OrdenRecojo
				.antMatchers("/OrdenRecojo/listar").hasAuthority("administrador")
				.antMatchers("/OrdenRecojo/buscar/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/OrdenRecojo/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/OrdenRecojo/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/OrdenRecojo/**").hasAnyAuthority("administrador")
				
				//OrdenEntrega
				.antMatchers("/Ordenentrega/listar").hasAuthority("administrador")
				.antMatchers("/Ordenentrega/buscar/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/Ordenentrega/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/Ordenentrega/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/Ordenentrega/**").hasAnyAuthority("administrador")
				
				//DetalleServicio
				.antMatchers("/DetalleServicio/listar").hasAuthority("administrador")
				.antMatchers("/DetalleServicio/buscar/**").hasAuthority("administrador")
				.antMatchers(HttpMethod.POST, "/DetalleServicio/**").permitAll()//hasAuthority("administrador")
				.antMatchers(HttpMethod.DELETE, "/DetalleServicio/**").hasAnyAuthority("administrador")
				.antMatchers(HttpMethod.PUT, "/DetalleServicio/**").hasAnyAuthority("administrador")
				
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests().and().httpBasic();
		CorsConfiguration corsconfig = new CorsConfiguration();
		//CorsConfiguration corsconfig = new CorsConfiguration();
		corsconfig.setAllowedOrigins(List.of("*"));
		corsconfig.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
		corsconfig.setAllowedHeaders(List.of("*"));
		
		http.cors().configurationSource(request -> corsconfig );
		http.authorizeRequests().and().csrf().disable();
		
		/*
		http.cors().configurationSource(request -> {
	          var cors = new CorsConfiguration();
	          cors.setAllowedOrigins("*");
	          cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
	          cors.setAllowedHeaders(List.of("*"));
	          return cors;
	        }); //este metodo solo permite get post het
	        */
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
