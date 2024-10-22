package com.umax.ms_security.config;

import com.umax.ms_security.model.entities.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;



@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

 public String createToken(UserEntity user){
     Map<String, Object> claims= Jwts.claims().setSubject(user.getUsername());
     claims.put("id",user.getId());

     Date now=new Date();
     Date exp=new Date(now.getTime()+3600*1000);

     return Jwts.builder()
             .setClaims(claims)
             .setIssuedAt(now)
             .setExpiration(exp)
             .signWith(SignatureAlgorithm.HS256,secret)
             .compact();
 }

    public void validate(String token){

     try {
         Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);

     }
     catch (Exception ex){
         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
     }

 }

 public String getUsernameToken(String token) {
     try {
         return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token)
                 .getBody()
                 .getSubject();
     } catch (Exception e) {
        throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid token");
     }
 }
}
