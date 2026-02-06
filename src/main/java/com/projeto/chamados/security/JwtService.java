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

    // a chave precisa ter mais de 32 bytes para HS256
    private static final String SECRET_KEY =
            "minha_chave_super_secreta_de_no_minimo_32_bytes";

    // -----------------------------------------------------------------------------------
    // GERAR TOKEN
    // -----------------------------------------------------------------------------------
    public String gerarToken(UsuarioEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 h
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // -----------------------------------------------------------------------------------
    // EXTRAIR EMAIL (SUBJECT)
    // -----------------------------------------------------------------------------------
    public String extrairEmail(String token) {
        return getClaims(token).getSubject();
    }

    // -----------------------------------------------------------------------------------
    // VALIDAR TOKEN (NOVO - ESSENCIAL)
    // -----------------------------------------------------------------------------------
    public String validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject(); // retorna o e-mail
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado!");
        } catch (JwtException e) {
            System.out.println("Token inválido!");
        } catch (Exception e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
        }
        return null; // token inválido
    }

    // -----------------------------------------------------------------------------------
    // LER CLAIMS DO TOKEN
    // -----------------------------------------------------------------------------------
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
