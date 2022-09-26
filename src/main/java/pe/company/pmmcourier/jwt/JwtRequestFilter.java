package pe.company.pmmcourier.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

import pe.company.pmmcourier.service.UsuarioService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsuarioService usuarioDetailService;

	public JwtRequestFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Filtro:JwtRequestFilter intercepto Request");
		System.out.println("Request interceptado : ");
		System.out.println(request);
		String uri = request.getRequestURI();
		System.out.println("Uri invocada por cliente : " + uri);

		if (!uri.contains("/authenticate") 
				&& !uri.contains("/TipoPQT/listar")
				
				&& !uri.contains("/Paquete/agregar") 
				&& !uri.contains("/Paquete/buscar/")
				
				&& !uri.contains("/Distrito/listar") 
				
				&& !uri.contains("/OrdenRecojo/agregar")
				&& !uri.contains("/OrdenRecojo/buscar/")
				
				&& !uri.contains("/OrdenEntrega/agregar")
				&& !uri.contains("/OrdenEntrega/buscar/")
				
				&& !uri.contains("/DetalleServicio/agregar")
				) {
			
			//Proceso de validacion de token JWT
			final String requestTokenHeader = request.getHeader("Authorization");
			String userName = null;
			String jwtToken = null;

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				
				System.out.println("llego token");
				
				jwtToken = requestTokenHeader.substring(7);// Cortamos para obtener el contenido del tokenn jwt
				
				System.out.println("token : " + jwtToken);
				
				userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
				if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = usuarioDetailService.loadUserByUsername(userName);
					System.out.println("Usuario obtenido de BD:");
					System.out.println(userDetails);
					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
						UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
						userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(userAuth);
					}
					else
					{
						System.out.println("Token jwt inválido..");
						throw new ExpiredJwtException(null, null, jwtToken);
					}
				}
			} else {
				throw new IllegalArgumentException("Petición inválida..No hay token");
			}
		}
		else {
			System.out.println("no filtrado!!");
		}
		filterChain.doFilter(request, response); //Deja pasar la peticion hacia el controlador respectivo!!.
	}

}
