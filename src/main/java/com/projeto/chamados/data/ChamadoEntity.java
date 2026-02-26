/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.data;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.projeto.chamados.enums.Categoria;
import com.projeto.chamados.enums.Prioridade;
import com.projeto.chamados.enums.Ativo;
import com.projeto.chamados.enums.Status;
/**
 *
 * @author heitor
 */
@Data
@Entity
@Table(name="Chamados")
public class ChamadoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeouempresa;
    
    @NotBlank
    @Email
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    
    @NotBlank
    private String titulo;
    
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    
    @Enumerated(EnumType.STRING)
    private Ativo ativo;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private LocalDateTime datachamado;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsuarioEntity user;
    
}
