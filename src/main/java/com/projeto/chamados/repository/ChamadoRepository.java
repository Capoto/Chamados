/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.repository;
import com.projeto.chamados.data.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author heitor
 */
@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity,Long> {
    
    ChamadoEntity findByEmail(String email);
    ChamadoEntity findByCategoria(String categoria);
    ChamadoEntity findByStatus(String Status);
}
