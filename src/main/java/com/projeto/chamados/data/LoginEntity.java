/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.data;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
/**
 *
 * @author heitor
 */

@Data
@Entity
@Table(name="Login")
public class LoginEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer loginid;
    private String  email;
    private String  senha;
    private String  status;
}
