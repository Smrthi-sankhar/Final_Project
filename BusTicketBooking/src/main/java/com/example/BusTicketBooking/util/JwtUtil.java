package com.example.BusTicketBooking.util;


import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {

	/* Here we are going to create 2 method
	 * 1, for create JWT token
	 * 2,for verify that
	 * */
	 
	 private static final String secretKey ="LMS_HEX_MAY_7867486909090909090909";
	 private static final  long expirationTimeInMills=43200000; //12 hrs
	 private Key getSigningKey(){
		 return Keys.hmacShaKeyFor(secretKey.getBytes());
	 }
	 
	 
	 
	
	 public String createToken(String email){
		 return Jwts.builder()
		 	  .setSubject(email)//store email in token
		 	  .setIssuedAt(new Date(System.currentTimeMillis()))
		 	  .setExpiration(new Date(System.currentTimeMillis()+expirationTimeInMills))
		 	  .signWith(getSigningKey(),SignatureAlgorithm.HS256) // for sign securely
		 	  .compact(); // return it as a string
		 
	 }
	 public boolean verifyToken(String token,String email) {
		 String extractedEmail = Jwts.parserBuilder()
				 					 .setSigningKey(getSigningKey())
				 					 .build()
				 					 .parseClaimsJws(token)
				 					 .getBody()
				 					 .getSubject();
		 
		 Date expirationDate = Jwts.parserBuilder()
									 .setSigningKey(getSigningKey())
									 .build()
									 .parseClaimsJws(token)
									 .getBody()
									 .getExpiration();
		 return extractedEmail.equals(email) && new Date().before(expirationDate);
				 					 
		 
	 }


	 public String extractUsername(String token) {
		 
			return  Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					 .build()
					 .parseClaimsJws(token)
					 .getBody()
					 .getSubject(); 
		}
	
}