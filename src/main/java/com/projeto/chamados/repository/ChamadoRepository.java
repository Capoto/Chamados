/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.repository;
import com.projeto.chamados.data.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author heitor
 */
@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity,Long> {
    
    ChamadoEntity findByEmail(String email);
    ChamadoEntity findByCategoria(String categoria);
    ChamadoEntity findByStatus(String Status);
    ChamadoEntity findByPrioridade(String Prioridade);
    
    @Query("""
    SELECT c FROM ChamadoEntity c
    WHERE 
        (:campo = 'id' AND CAST(c.id AS string) LIKE %:valor%)
        OR (:campo = 'titulo' AND LOWER(c.titulo) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'nomeouempresa' AND LOWER(c.nomeouempresa) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'email' AND LOWER(c.email) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'categoria' AND LOWER(c.categoria) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'prioridade' AND LOWER(c.prioridade) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'ativo' AND LOWER(c.ativo) LIKE LOWER(CONCAT('%', :valor, '%')))
""")
Page<ChamadoEntity> buscarPorCampo(
        @Param("campo") String campo,
        @Param("valor") String valor,
        Pageable pageable);
}
