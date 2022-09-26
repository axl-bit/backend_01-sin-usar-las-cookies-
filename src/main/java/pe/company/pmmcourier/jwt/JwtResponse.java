package pe.company.pmmcourier.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String jwttoken;

	
	public JwtResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}


	public String getJwttoken() {
		return jwttoken;
	}
		

}
