/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;
import com.projeto.chamados.enums.Ativo;
import com.projeto.chamados.enums.Categoria;
import com.projeto.chamados.enums.Prioridade;
import com.projeto.chamados.enums.Status;
import java.time.LocalDateTime;
/**
 *
 * @author heitor
 */
public record ChamadoResponseDTO(
    Long id,
    String titulo,
    String descricao,
    Ativo ativo,
    Categoria categoria,
    Prioridade prioridade,
    Status status,
    String email,
    String nomeouempresa,
    LocalDateTime datachamado,
    Long userId
        ){
}
