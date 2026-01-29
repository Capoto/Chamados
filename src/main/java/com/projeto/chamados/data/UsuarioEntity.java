/* * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template */
package com.projeto.chamados.data;
import com.projeto.chamados.enums.UserRole;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "usuario") // nome mais claro para tabela
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginid;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Senha obrigatória")
    private String senhahash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    private String resetTokenHash;

    private LocalDateTime resetTokenExpira;
    
    @OneToMany(mappedBy = "user")
    private List<ChamadoEntity> chamados;

}
