/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author heitor
 */
public record LoginDTO (
    
    @Email
    @Size(max = 255, message = "E-mail até 255 caracteres")
    String email,

    @NotBlank(message = "Senha obrigatória")
    @Size(max = 255, message = "Senha até 255 caracteres")
    String senhahash
){}
