/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.projeto.chamados;

import com.projeto.chamados.data.ChamadoEntity;
import com.projeto.chamados.repository.UsuarioRepository;
import com.projeto.chamados.service.AuthService;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.dto.LoginDTO;
import com.projeto.chamados.dto.CadastroUsuarioDTO;
import com.projeto.chamados.security.JwtService;
import com.projeto.chamados.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.time.LocalDateTime;
import org.springframework.security.core.Authentication;
/**
 *
 * @author heitor
 */
@ExtendWith(MockitoExtension.class)
public class AuthJUnitTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private AuthService authService;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private JwtService jwtService;
    
    @Mock
    private Authentication auth;
    
    public AuthJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testlogin() {
    
    }
    
    
    @Test
    public void testRegistraUsuario(){
    
        LocalDateTime time = LocalDateTime.now();
        CadastroUsuarioDTO dto = new CadastroUsuarioDTO("Heitor","Soluções Tech","Rua José quadra 44 Lote 20","heitor@gmail.com","12345",UserRole.CLIENTE,"",time);
        
        // Usuário encontrado no banco com senha HASH
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setEmpresa(dto.empresa());
        usuarioEntity.setEndereco(dto.endereco());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setRole(dto.role());
        usuarioEntity.setSenhahash(passwordEncoder.encode(dto.senha()));
        
        // Mocks obrigatórios conforme o método login()
        when(usuarioRepository.existsByEmail("heitor@gmail.com"))
            .thenReturn(false);
        
        var resultado = authService.registrarUsuario(dto);
        
        // Validações
        assertNotNull(resultado);
        assertEquals(usuarioEntity, resultado);
        
        
        verify(usuarioRepository).existsByEmail("heitor@gmail.com");
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    
    }
    
    @Test
    public void testLoginComSucesso() {

    // DTO enviado pelo usuário
    LoginDTO login = new LoginDTO("heitor@gmail.com", "12345");

    // Usuário encontrado no banco com senha HASH
    UsuarioEntity user = new UsuarioEntity();
    user.setEmail("heitor@gmail.com");
    user.setSenhahash("$2a$10$HASHFALSOAPENASPARATESTES");

    // Mocks obrigatórios conforme o método login()
    when(usuarioRepository.existsByEmail("heitor@gmail.com"))
            .thenReturn(true);

    when(usuarioRepository.findByEmail("heitor@gmail.com"))
            .thenReturn(user);

    when(passwordEncoder.matches("12345", "$2a$10$HASHFALSOAPENASPARATESTES"))
            .thenReturn(true);

    when(jwtService.gerarToken(user))
            .thenReturn("TOKEN_DE_TESTE");

    // Chamar o método
    String resultado = authService.login(login);

    // Validações
    assertNotNull(resultado);
    assertEquals("TOKEN_DE_TESTE", resultado);

    // Verificar se tudo foi chamado
    verify(usuarioRepository).existsByEmail("heitor@gmail.com");
    verify(usuarioRepository).findByEmail("heitor@gmail.com");
    verify(passwordEncoder).matches("12345", "$2a$10$HASHFALSOAPENASPARATESTES");
    verify(jwtService).gerarToken(user);

    }
    
    
    @Test
    public void testLoginSenhaErrada(){
    
        // DTO enviado pelo usuário
        LoginDTO login = new LoginDTO("heitor@gmail.com", "12345");

        // Usuário encontrado no banco com senha HASH
        UsuarioEntity user = new UsuarioEntity();
        user.setEmail("heitor@gmail.com");
        user.setSenhahash("$2a$10$HASHFALSOAPENASPARATESTES");

        // Mocks obrigatórios conforme o método login()
        when(usuarioRepository.existsByEmail("heitor@gmail.com"))
            .thenReturn(true);

        when(usuarioRepository.findByEmail("heitor@gmail.com"))
            .thenReturn(user);

        when(passwordEncoder.matches("12345", "$2a$10$HASHFALSOAPENASPARATESTES"))
            .thenReturn(false);
    
   
    RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(login));
    
    assertEquals("A senha está errada",exception.getMessage());

    // Verificar se tudo foi chamado
    verify(usuarioRepository).existsByEmail("heitor@gmail.com");
    verify(usuarioRepository).findByEmail("heitor@gmail.com");
    verify(passwordEncoder).matches("12345", "$2a$10$HASHFALSOAPENASPARATESTES");
   
        
        
    }
    
    
    
    @Test
    public void testLoginEmailnãoexiste(){
    
        // DTO enviado pelo usuário
        LoginDTO login = new LoginDTO("heitor@gmail.com", "12345");

        // Usuário encontrado no banco com senha HASH
        UsuarioEntity user = new UsuarioEntity();
        user.setEmail("heitor@gmail.com");
        user.setSenhahash("$2a$10$HASHFALSOAPENASPARATESTES");

        // Mocks obrigatórios conforme o método login()
        when(usuarioRepository.existsByEmail("heitor@gmail.com"))
            .thenReturn(false);

   
    RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(login));
    
    assertEquals("Usuário não encontrado!",exception.getMessage());

    // Verificar se tudo foi chamado
    verify(usuarioRepository).existsByEmail("heitor@gmail.com");
    
   
        
        
    }
        
    
    }

