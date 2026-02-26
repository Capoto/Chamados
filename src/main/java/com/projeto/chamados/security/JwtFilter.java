/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.security;

/**
 *
 * @author heitor
 */



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.projeto.chamados.repository.UsuarioRepository;
import com.projeto.chamados.data.UsuarioEntity;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1 - Sem token → segue e ENCERRA
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // 2 - Token inválido → segue e ENCERRA
        String email = jwtService.validateToken(token);
        if (email == null) {
            chain.doFilter(request, response);
            return;
        }

        // 3 - Se já está autenticado → segue e ENCERRA
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(request, response);
            return;
        }

        // 4 - Busca usuário
        UsuarioEntity user = usuarioRepository.findByEmail(email);
        if (user == null) {
            chain.doFilter(request, response);
            return;
        }

        // 5 - Cria authorities
        var authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        // 6 - Autentica usuário
        UsernamePasswordAuthenticationToken auth =
    new UsernamePasswordAuthenticationToken(
            email,   // principal = email
            token,   // credentials = o token
            authorities
    );


        SecurityContextHolder.getContext().setAuthentication(auth);

        // 7 - SEGUE EXECUÇÃO
        chain.doFilter(request, response);
    }
}
