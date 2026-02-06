/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.service;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.dto.LoginDTO;
import com.projeto.chamados.dto.CadastroUsuarioDTO;
import com.projeto.chamados.repository.UsuarioRepository;
import com.projeto.chamados.exception.EmailJaExisteException;
import com.projeto.chamados.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author heitor
 */

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UsuarioEntity registrarUsuario(CadastroUsuarioDTO dto){
    
        if(usuarioRepository.existsByEmail(dto.email())){
        
           throw new EmailJaExisteException("Email já registrado!");
        }
    
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setEmpresa(dto.empresa());
        usuarioEntity.setEndereco(dto.endereco());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setRole(dto.role());
        usuarioEntity.setSenhahash(passwordEncoder.encode(dto.senha()));
        
        usuarioRepository.save(usuarioEntity);
        
        return usuarioEntity;
    }
    
    
    public String login(LoginDTO dto){
    
        
        if(!usuarioRepository.existsByEmail(dto.email())){
        
            throw new RuntimeException("Usuário não encontrado!");
        }
        else{
        
            UsuarioEntity user = usuarioRepository.findByEmail(dto.email());
            
            if(!passwordEncoder.matches(dto.senha(), user.getSenhahash())){
                throw new RuntimeException("A senha está errada");
            }
        
            return jwtService.gerarToken(user);
        }
        
    
    }
    
    
}
