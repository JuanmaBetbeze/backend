package com.example.demo.security.jwt;

import com.example.demo.models.Usuario.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger logger= LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal= (UsuarioPrincipal) authentication.getPrincipal();

        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setExpiration(new Date(new Date().getTime()+ expiration* 10000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
      try {
          Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
          return true;

      }catch (MalformedJwtException c){
          logger.error("token mal formado");
      }catch (UnsupportedJwtException c){
          logger.error("token no soportado");
      }catch (ExpiredJwtException c){
          logger.error("token expirado");
      }catch (IllegalArgumentException c){
          logger.error("token vacio");
      }catch (SignatureException c){
          logger.error("fallo en la firma");
      }
      return false;
    }
}
