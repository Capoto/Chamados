/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.exception;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author heitor
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EmailJaExisteException.class)
    public ResponseEntity<Map<String, String>> handleEmailDuplicado(EmailJaExisteException ex) {
    
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    
    
    }
    
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
    
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    
    
    }
    
    @ExceptionHandler(SenhaErradaException.class)
    public ResponseEntity<Map<String, String>> handleSenhaErrada(SenhaErradaException ex) {
    
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    
    
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>>  exception(Exception ex){
    
        Map<String,String> erro = new HashMap<>();
        erro.put("erro","Erro interno do Servidor");
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
