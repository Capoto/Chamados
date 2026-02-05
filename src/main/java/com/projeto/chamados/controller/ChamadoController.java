/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.controller;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
/**
 *
 * @author heitor
 */

@Controller
public class ChamadoController {
    
    @Autowired
    private AuthService authservice;
    
    @GetMapping("/")
    public String viewHomePage(){
    
        return "index";
    }
    
    @GetMapping("/registro")
    public String viewRegistro(){
    
        return "registro";
    }
    
    @PostMapping("/salvandousuario")
    public String salvandoUsuario(Model model){
    
        //authservice.registrarUsuario(model);
        return "index";
    }
}
