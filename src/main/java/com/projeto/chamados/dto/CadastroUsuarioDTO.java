/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.projeto.chamados.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
/**
 *
 * @author heitor
 */
public record CadastroUsuarioDTO(
    
    @Size(max = 255, message = "Nome até 255 caracteres")    
    String nome,
        
    @Size(max = 255, message = "Nome até 255 caracteres")    
    String empresa,

    @Size(max = 255, message = "Nome até 255 caracteres")    
    String endereco,
        
    @Email
    @Size(max = 255, message = "E-mail até 255 caracteres")
    String email,

    @NotBlank(message = "Senha obrigatória")
    @Size(max = 255, message = "Senha até 255 caracteres")
    String senha,

    
   
    UserRole role,

    @Size(max = 255, message = "Token até 255 caracteres")
    String resetTokenHash,

    LocalDateTime resetTokenExpira
  
        ) {
    
}
