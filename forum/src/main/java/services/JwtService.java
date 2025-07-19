package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtService {
    @Value("${Jwt.key}")
    private String key;

    public String generateToken(String name){

        return Jwts.builder().setSubject(name).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000000))
        .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    public String getName(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isExpired(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public boolean isValid(String token, String name){
        System.out.println(token);
        return getName(token).equals(name) && !isExpired(token);
    }
}
