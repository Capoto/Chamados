/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.interfaces;

/**
 *
 * @author heitor
 */
public interface MetricasChamado {
    Long getQuantidadeChamados();
    Long getAberto();
    Long getCritico();
    Long getAlta();
    Long getBaixa();
    Long getMedia();
    Long getHardware();
    Long getSoftware();
    Long getSwitchCategoria();
    Long getRede();
    Long getLicitacao();
    Long getFinanceiros();
    Long getOutros();
}
