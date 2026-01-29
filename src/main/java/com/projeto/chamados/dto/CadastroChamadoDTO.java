/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;


import com.projeto.chamados.enums.Ativo;
import com.projeto.chamados.enums.Categoria;
import com.projeto.chamados.enums.Prioridade;
import com.projeto.chamados.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


/**
 *
 * @author heitor
 */
public record CadastroChamadoDTO (
        
    @NotBlank(message = "Nome ou empresa obrigatório")
    String nomeouempresa,

    @NotBlank(message = "E-mail obrigatório")
    @Email
    String email,

    @NotBlank(message = "Título obrigatório")
    String titulo,

    String descricao,

    Categoria categoria,

    Prioridade prioridade,

    Ativo ativo,

    Status status,

    Long userId 
    
        
     ){
    
}
