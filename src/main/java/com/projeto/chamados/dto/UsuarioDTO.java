/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;

/**
 *
 * @author heitor
 */
public record UsuarioDTO(
    Long id,
    String nome,
    String email,
    String role
) {}
