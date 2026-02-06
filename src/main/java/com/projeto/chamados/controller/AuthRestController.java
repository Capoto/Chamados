/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.controller;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.service.AuthService;
import com.projeto.chamados.dto.CadastroUsuarioDTO;
import com.projeto.chamados.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author heitor
 */

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    
    @Autowired
    
    private AuthService authService;

    @CrossOrigin("*")
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioEntity> criarusuario(@RequestBody CadastroUsuarioDTO dto){
    
        var user =  authService.registrarUsuario(dto);
        return new  ResponseEntity<>(user,HttpStatus.OK);
    }
    
    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto){
    
        var token = authService.login(dto);
        
        return new ResponseEntity<>(token,HttpStatus.OK);
    
    }
    
    
}
