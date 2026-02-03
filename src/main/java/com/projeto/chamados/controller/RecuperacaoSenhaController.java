/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.chamados.service.RecuperacaoSenhaService;

/**
 *
 * @author heitor
 */

@RestController
@RequestMapping("/auth")
public class RecuperacaoSenhaController {
    
     private final RecuperacaoSenhaService service;

    public RecuperacaoSenhaController(RecuperacaoSenhaService service) {
        this.service = service;
    }

    @PostMapping("/esqueci")
    public ResponseEntity<?> solicitar(@RequestParam String email) {
        service.solicitarReset(email);
        return ResponseEntity.ok("Se o e-mail existir, um link foi enviado.");
    }

    @PostMapping("/resetar")
    public ResponseEntity<?> resetar(
            @RequestParam String token,
            @RequestParam String novaSenha
    ) {
        service.redefinirSenha(token, novaSenha);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
}
