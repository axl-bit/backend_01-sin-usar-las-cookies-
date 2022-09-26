package pe.company.pmmcourier.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 horas expresado en segundos

	@Value("${jwt.secret}") // Inyectando valor de propiedad jwt.secret desde el archivo application.properties.
	private String secret;
	
	@Value("${jwt.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	
	
	
	public JwtTokenUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	//se obtiene el nombre del usuario
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//se obtiene la fecha de expiracion
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	} 
	
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); //Si token fue alterado no se podra abrir con la llave!!
	}

	//Verifica si el token esta expirado
	private Boolean isTokenExpired(String token) {
			final Date expiration = getExpirationDateFromToken(token);
			return expiration.before(new Date());
	}
	
	/*
	//se genera el toke
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	*/
	
	//se genera el toke
	public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

	//Generacion del token
	private String doGenerateToken(Map<String, Object> claims, String username) {
		//TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
				
	}
	
	//se valida el token
	public Boolean validateToken(String token,UserDetails userDetails)
	{
		final String username = getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
}
