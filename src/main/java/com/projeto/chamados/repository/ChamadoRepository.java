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
import com.projeto.chamados.interfaces.MetricasChamado;
import com.projeto.chamados.interfaces.ChamadoId;
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


   @Query("""
    SELECT c FROM ChamadoEntity c
    WHERE 
        ((:campo = 'id' AND CAST(c.id AS string) LIKE %:valor%)
        OR (:campo = 'titulo' AND LOWER(c.titulo) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'nomeouempresa' AND LOWER(c.nomeouempresa) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'email' AND LOWER(c.email) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'categoria' AND LOWER(c.categoria) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'prioridade' AND LOWER(c.prioridade) LIKE LOWER(CONCAT('%', :valor, '%')))
        OR (:campo = 'ativo' AND LOWER(c.ativo) LIKE LOWER(CONCAT('%', :valor, '%')))
                  )
        AND c.user.id = :id
""")
Page<ChamadoEntity> buscarPorCampoId(
        @Param("campo") String campo,
        @Param("valor") String valor,
        @Param("id") long id,
        Pageable pageable);
    

       @Query(value = """
    SELECT 
        COUNT(*) AS quantidadeChamados,
        SUM(CASE WHEN status = 'Aberto' THEN 1 ELSE 0 END) AS aberto,
        SUM(CASE WHEN prioridade = 'Crítica' THEN 1 ELSE 0 END) AS critico,
        SUM(CASE WHEN prioridade = 'Alta' THEN 1 ELSE 0 END) AS alta,
        SUM(CASE WHEN prioridade = 'Baixa' THEN 1 ELSE 0 END) AS baixa,
        SUM(CASE WHEN prioridade = 'Média' THEN 1 ELSE 0 END) AS media,
        SUM(CASE WHEN categoria = 'Hardware' THEN 1 ELSE 0 END) AS hardware,
        SUM(CASE WHEN categoria = 'Software' THEN 1 ELSE 0 END) AS software,
        SUM(CASE WHEN categoria = 'Switch' THEN 1 ELSE 0 END) AS switchCategoria,
        SUM(CASE WHEN categoria = 'Rede' THEN 1 ELSE 0 END) AS rede,
        SUM(CASE WHEN categoria = 'Licitacao' THEN 1 ELSE 0 END) AS licitacao,
        SUM(CASE WHEN categoria = 'Financeiros' THEN 1 ELSE 0 END) AS financeiros,
        SUM(CASE WHEN categoria = 'Outros' THEN 1 ELSE 0 END) AS outros
    FROM chamados
    WHERE user_id = :id
""", nativeQuery = true)
MetricasChamado metricas(long id);
}
