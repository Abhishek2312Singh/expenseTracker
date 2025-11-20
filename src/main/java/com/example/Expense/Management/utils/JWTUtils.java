package com.example.Expense.Management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {
    public final String SECRET = "w8nXKq4bT0sFYRzM7hHj1Nud3aPVoC9xv2U6mLfRkpJg5iObyE0WcQlAZtDSrG8M";
    public final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .subject(username)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token){
        Claims body = Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println("getUSername");
        return body.getSubject();
    }
    public Boolean validateToken(UserDetails userDetails, String token){
        Claims body = Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println("Validate Token");
        return (userDetails.getUsername().equals(body.getSubject()) && body.getExpiration().after(new Date()));
    }
}
