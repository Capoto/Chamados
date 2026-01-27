/* * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template */
package com.projeto.chamados.data;
import com.projeto.chamados.enums.UserRole;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario") // nome mais claro para tabela
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginid;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail obrigatório")
    @Size(max = 255, message = "E-mail até 255 caracteres")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Senha obrigatória")
    @Size(max = 255, message = "Senha até 255 caracteres")
    private String senhahash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Size(max = 255, message = "Token até 255 caracteres")
    private String resetTokenHash;

    private LocalDateTime resetTokenExpira;
}
