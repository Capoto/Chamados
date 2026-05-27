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
    long getQuantidadeChamados();
    long getAberto();
    long getCritico();
    long getAlta();
    long getBaixa();
    long getMedia();
    long getHardware();
    long getSoftware();
    long getSwitchCategoria();
    long getRede();
    long getLicitacao();
    long getFinanceiros();
    long getOutros();
}
