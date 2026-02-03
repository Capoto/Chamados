/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.projeto.chamados.repository.UsuarioRepository;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.security.TokenUtil;

/**
 *
 * @author heitor
 */

@Service
public class RecuperacaoSenhaService {
    
    
    private final UsuarioRepository user;
    
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public RecuperacaoSenhaService(UsuarioRepository user){
    
        this.user = user;
    
    }
    
    // 1. Gera token e envia link
    public void solicitarReset(String email) {
        UsuarioEntity u;
                
        if(user.existsByEmail(email)){
        
         throw new RuntimeException("Email encontrado");
        }
        
        u = user.findByEmail(email);

        String token = TokenUtil.gerarToken();
        String tokenHash = encoder.encode(token);

        u.setResetTokenHash(tokenHash);
        u.setResetTokenExpira(LocalDateTime.now().plusMinutes(30));

        user.save(u);

        // Aqui você enviaria por e-mail EX: emailService.enviarReset(u.getEmail(), token);
        System.out.println("Link de reset: https://seusite.com/resetar?token=" + token);
    }

    // 2. Valida token
    public UsuarioEntity validarToken(String token) {
        return user.findAll().stream()
                .filter(u -> u.getResetTokenHash() != null
                          && u.getResetTokenExpira().isAfter(LocalDateTime.now())
                          && encoder.matches(token, u.getResetTokenHash()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado"));
    }

    // 3. Trocar senha
    public void redefinirSenha(String token, String novaSenha) {
        UsuarioEntity u = validarToken(token);

        u.setSenhahash(encoder.encode(novaSenha));
        u.setResetTokenHash(null);
        u.setResetTokenExpira(null);

        user.save(u);
    }
    
}
