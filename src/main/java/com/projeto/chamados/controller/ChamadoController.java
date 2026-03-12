/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.controller;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.dto.CadastroUsuarioDTO;
import com.projeto.chamados.dto.LoginDTO;
import com.projeto.chamados.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
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
    
    @GetMapping("/chamado")
    public String viewChamadoPage(){
    
        return "chamado";
    }
    
    @GetMapping("/tabela")
    public String viewTabelaPage(){
    
        return "tabela";
    }
    
    @GetMapping("/registro")
    public String viewRegistro(){
    
        return "registro";
    }
    
    @PostMapping("/salvandousuario")
    public String salvandoUsuario(@RequestBody CadastroUsuarioDTO dto ){
    
        authservice.registrarUsuario(dto);
        return "index";
    }
    
    @PostMapping("/logando")
    public String logando(@RequestBody LoginDTO dto ){
    
        authservice.login(dto);
        return "index";
    }
    
    @GetMapping("/editachamado")
    public String AnaliseFilme(Model model,@RequestParam int id) {
        
        return "editachamado";
    }
}
