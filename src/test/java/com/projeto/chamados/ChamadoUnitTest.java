/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.projeto.chamados;

import com.projeto.chamados.repository.ChamadoRepository;
import com.projeto.chamados.repository.UsuarioRepository;
import com.projeto.chamados.service.ChamadoService;
import com.projeto.chamados.data.ChamadoEntity;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.dto.CadastroChamadoDTO;
import com.projeto.chamados.dto.ChamadoResponseDTO;
import com.projeto.chamados.enums.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author heitor
 */

@ExtendWith(MockitoExtension.class)
public class ChamadoUnitTest {
    
    @Mock
    private ChamadoRepository chamadoRepository;
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @Mock
    private Clock clock;
  
    
    @InjectMocks
    private ChamadoService chamadoService;
    
    private ChamadoResponseDTO toDTO(ChamadoEntity c) {
    return new ChamadoResponseDTO(
        c.getId(),
        c.getTitulo(),
        c.getDescricao(),
        c.getAtivo(),
        c.getCategoria(),
        c.getPrioridade(),
        c.getStatus(),
        c.getEmail(),
        c.getNomeouempresa(),
        c.getDatachamado(),
        c.getUser().getLoginid()
    );
}
    
    public ChamadoUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
     @BeforeEach
    void setup() {
        
    }
    
    

    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testCadastraChamado() {
    
        CadastroChamadoDTO dto = new CadastroChamadoDTO("Soluções TI","heitor@gmail.com","Servidor queimado","",Categoria.Rede,Prioridade.Alta,Ativo.Servidor,Status.Aguardando, 1l);
        UsuarioEntity user = new UsuarioEntity();
        user.setLoginid(dto.userId());
        ChamadoEntity chamado = new ChamadoEntity();
        chamado.setNomeouempresa(dto.nomeouempresa());
        chamado.setEmail(dto.email());
        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setCategoria(dto.categoria());
        chamado.setPrioridade(dto.prioridade());
        chamado.setAtivo(dto.ativo());
        chamado.setStatus(dto.status());
        chamado.setUser(user);
        
        when(usuarioRepository.findById(dto.userId()))
                .thenReturn(Optional.of(user));
        when(clock.instant()).thenReturn(Instant.parse("2020-01-01T10:00:00Z"));
        when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
      
        
        ChamadoResponseDTO x =  new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
     LocalDateTime.parse("2020-01-01T10:00:00"),
       chamado.getUser().getLoginid()
    );
        
        ChamadoService testchamado = new ChamadoService(usuarioRepository,chamadoRepository,clock);
        var resultado = testchamado.salvarCadastro(dto);
        assertNotNull(resultado);
        assertEquals(x, resultado);
        
        verify(usuarioRepository).findById(1l);
        verify(chamadoRepository).save(any(ChamadoEntity.class));
    }
    
    
    
    @Test
    public void testEditaChamado() {
    
        CadastroChamadoDTO dto = new CadastroChamadoDTO("Soluções TI","heitor@gmail.com","Servidor queimado","",Categoria.Rede,Prioridade.Alta,Ativo.Servidor,Status.Aguardando, 1l);
        UsuarioEntity user = new UsuarioEntity();
        user.setLoginid(dto.userId());
        ChamadoEntity chamado = new ChamadoEntity();
        chamado.setId(1l);
        chamado.setNomeouempresa(dto.nomeouempresa());
        chamado.setEmail(dto.email());
        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setCategoria(dto.categoria());
        chamado.setPrioridade(dto.prioridade());
        chamado.setAtivo(dto.ativo());
        chamado.setStatus(dto.status());
        chamado.setUser(user);
        
        
        
        when(chamadoRepository.findById(1L))
                .thenReturn(Optional.of(chamado));
        
        when(clock.instant()).thenReturn(Instant.parse("2020-01-01T10:00:00Z"));
        when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
        
        ChamadoResponseDTO x =  new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
     LocalDateTime.parse("2020-01-01T10:00:00"),
       chamado.getUser().getLoginid()
    );
        
        // Mock do save
    when(chamadoRepository.save(any()))
            .thenAnswer(invocation -> {
                ChamadoEntity c = invocation.getArgument(0);
                return c;
            });
        
        ChamadoService testchamado = new ChamadoService(usuarioRepository,chamadoRepository,clock);
        var resultado = testchamado.atualizaCadastro(1l,dto);
        assertNotNull(resultado);
        assertEquals(1l, resultado.id());
        assertEquals("Servidor queimado", resultado.titulo());

        verify(chamadoRepository).findById(1L);
        verify(chamadoRepository).save(any());
    }
    
    
    @Test
    public void testApagaChamado() {

    CadastroChamadoDTO dto = new CadastroChamadoDTO(
            "Soluções TI",
            "heitor@gmail.com",
            "Servidor queimado",
            "",
            Categoria.Rede,
            Prioridade.Alta,
            Ativo.Servidor,
            Status.Aguardando,
            1L
    );

    UsuarioEntity user = new UsuarioEntity();
    user.setLoginid(dto.userId());

    ChamadoEntity chamado = new ChamadoEntity();
    chamado.setId(1L);
    chamado.setNomeouempresa(dto.nomeouempresa());
    chamado.setEmail(dto.email());
    chamado.setTitulo(dto.titulo());
    chamado.setDescricao(dto.descricao());
    chamado.setCategoria(dto.categoria());
    chamado.setPrioridade(dto.prioridade());
    chamado.setAtivo(dto.ativo());
    chamado.setStatus(dto.status());
    chamado.setUser(user);

    // 🔥 AQUI ESTÁ O QUE FALTAVA
    when(chamadoRepository.findById(1L)).thenReturn(Optional.of(chamado));

    // executa
    chamadoService.deletaChamado(1L);

    // verifica
    verify(chamadoRepository).delete(any(ChamadoEntity.class));
}
    
    
    
    @Test
    public void testPesquisaChamadoId(){
    
        
    CadastroChamadoDTO dto = new CadastroChamadoDTO(
            "Soluções TI",
            "heitor@gmail.com",
            "Servidor queimado",
            "",
            Categoria.Rede,
            Prioridade.Alta,
            Ativo.Servidor,
            Status.Aguardando,
            1L
    );

    UsuarioEntity user = new UsuarioEntity();
    user.setLoginid(dto.userId());

    ChamadoEntity chamado = new ChamadoEntity();
    chamado.setId(1L);
    chamado.setNomeouempresa(dto.nomeouempresa());
    chamado.setEmail(dto.email());
    chamado.setTitulo(dto.titulo());
    chamado.setDescricao(dto.descricao());
    chamado.setCategoria(dto.categoria());
    chamado.setPrioridade(dto.prioridade());
    chamado.setAtivo(dto.ativo());
    chamado.setStatus(dto.status());
    chamado.setUser(user);

    // ⬅️ MOCKA SOMENTE FINDALL()
    when(chamadoRepository.findById(1l)).thenReturn(Optional.of(chamado));

    ChamadoService service = new ChamadoService(usuarioRepository, chamadoRepository, clock);

    // EXECUTA
    ChamadoEntity resultado = service.pesquisarChamadoId(1L);

    // VERIFICA CHAMADAS
    verify(chamadoRepository).findById(1l);

    // VERIFICA CONTEÚDO
  
    ChamadoEntity dtoResp = resultado;

    assertEquals(chamado.getTitulo(), dtoResp.getTitulo());
    assertEquals(chamado.getDescricao(), dtoResp.getDescricao());
    assertEquals(chamado.getCategoria(), dtoResp.getCategoria());
    assertEquals(chamado.getPrioridade(), dtoResp.getPrioridade());
    assertEquals(chamado.getStatus(), dtoResp.getStatus());
    assertEquals(chamado.getEmail(), dtoResp.getEmail());
    assertEquals(chamado.getNomeouempresa(), dtoResp.getNomeouempresa());
            
    
    
    }
    
}
