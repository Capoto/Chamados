/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.dto;

/**
 *
 * @author heitor
 */
public record MetricasChamadoDTO(
    Long quantidadeChamados,
    Long aberto,
    Long critico,
    Long alta,
    Long baixa,
    Long media,
    Long hardware,
    Long software,
    Long switchCategoria,
    Long rede,
    Long licitacao,
    Long financeiros,
    Long outros
) {}
