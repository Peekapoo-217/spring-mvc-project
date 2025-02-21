package com.dduongdev.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dduongdev.configs.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	private JwtConfig jwtConfig;
	
	private SecretKey secretKey;
	
	@Autowired
	public JwtServiceImpl(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
		
		secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
	}
	
	@Override
	public String generateToken(UserDetails userDetails) {
		List<String> authorities = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		Map<String, Object> claims = new HashMap<>();
		claims.put("Authorities", authorities);
		
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
				.signWith(secretKey)
				.compact();
	}

	@Override
	public String extractUsernameFromToken(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();

		return claims.getSubject();
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Jwts
			.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token);
			return true;
		} catch (MalformedJwtException | 
				ExpiredJwtException | 
				UnsupportedJwtException | 
				IllegalArgumentException ex) {
			return false;
		}
	}

	@Override
	public List<String> extractAuthoritiesFromToken(String token) {
		Claims claims = (Claims) Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
        List<?> rawAuthorities =  claims.get("Authorities", List.class);
        return rawAuthorities.stream()
        		.map(Object::toString)
        		.collect(Collectors.toList());
	}

}