/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.security;
import com.projeto.chamados.data.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import java.util.Date;

/**
 *
 * @author heitor
 */


@Service
public class JwtService {

    // Chave com 32+ bytes obrigatória para HS256
    private static final String SECRET_KEY =
            "minha_chave_super_secreta_de_no_minimo_32_bytes";

    // -----------------------------------------------------------------------------------
    // GERAR TOKEN
    // -----------------------------------------------------------------------------------
    public String gerarToken(UsuarioEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("userId", user.getLoginid())   // ID no token!
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // -----------------------------------------------------------------------------------
    // EXTRAIR EMAIL
    // -----------------------------------------------------------------------------------
    public String extrairEmail(String token) {
        return getClaims(token).getSubject();
    }

    // -----------------------------------------------------------------------------------
    // VALIDAR TOKEN
    // -----------------------------------------------------------------------------------
    public String validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject(); // e-mail
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado!");
        } catch (JwtException e) {
            System.out.println("Token inválido!");
        } catch (Exception e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
        }
        return null;
    }

    // -----------------------------------------------------------------------------------
    // PEGAR CLAIMS
    // -----------------------------------------------------------------------------------
    private Claims getClaims(String token) {
        // remove "Bearer "
        token = token.replace("Bearer ", "");

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // -----------------------------------------------------------------------------------
    // PEGAR ID DO USUÁRIO AUTENTICADO
    // -----------------------------------------------------------------------------------
     public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", Long.class);
    }

}