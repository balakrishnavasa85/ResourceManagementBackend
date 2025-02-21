package com.application.hrms.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtUtil {
	   private static final  String secret = "HRMSNEWApplication";

    public String extractUsername(String token){
        return extractClamis(token , Claims::getSubject);

//    	return extractClamis(token, new Function<Claims, String>() {
//			public String apply(Claims claims) {
//				return claims.getSubject();
//			}
//		});
    	}

    public String extractUserId(String token){
        return extractClamis(token , Claims::getSubject);
//    	return extractClamis(token, new Function<Claims, String>() {
//			public String apply(Claims claims) {
//				return claims.getSubject();
//			}
//		});
    	}
//    public static  JSONObject extractUserIdNew(String token) {
//        try {
//        	   if (token.startsWith("Bearer ")) {
//        		   token = token.substring(7);
//               }
//
//               // Split the token into its parts: header, payload, signature
//               String[] parts = token.split("\\.");
//               if (parts.length != 3) {
//                   throw new IllegalArgumentException("Invalid JWT token format.");
//               }
//
//               // Decode the payload (the middle part of the JWT)
//               String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
//        	 
//            JSONObject jsonObjectv = new JSONObject(payload);
//            return jsonObjectv;
//            // Extract user details
////            String userId = jsonObjectv.getString("id");
////            String name = jsonObjectv.getString("name");
////            String role = jsonObjectv.getString("role");
////            String email = jsonObjectv.getString("email");
//        } catch (Exception e) {
//            System.out.println("Invalid token: " + e.getMessage());
//            return null;
//        }
//    }

    public Date extractExpiration(String token){
        return extractClamis(token , Claims::getExpiration);
    }

    private <T> T extractClamis(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email , String role , String Username,Integer id){
        Map<String , Object> claims = new HashMap<String, Object>();
        claims.put("role" , role);
        claims.put("id" , id);
        claims.put("name" , Username);
        claims.put("email" , email);
        return createtoken(claims, email);
    }
    private String createtoken(Map<String , Object> claims , String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    public Boolean validatetoken(String token , UserDetails userDetails){
        final String Username = extractUsername(token);
        return (Username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }

}
