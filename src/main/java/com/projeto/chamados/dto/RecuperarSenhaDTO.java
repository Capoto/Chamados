/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
/**
 *
 * @author heitor
 */
public record RecuperarSenhaDTO(
        
        @Email
        @Size(max = 255, message = "E-mail até 255 caracteres")
        String email,
        String resetTokenHash
        
        
        ) {
    
}
