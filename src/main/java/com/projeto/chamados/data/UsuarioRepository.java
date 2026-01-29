/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author heitor
 */
@Repository 
public interface UsuarioRepository extends  JpaRepository<UsuarioEntity,Integer>{
    
    UsuarioEntity findByEmail(String email);
    UsuarioEntity findByResetTokenHash(String hash);
    
}
