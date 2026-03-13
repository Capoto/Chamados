/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.service;
import com.projeto.chamados.dto.CadastroChamadoDTO;
import com.projeto.chamados.dto.ChamadoResponseDTO;
import com.projeto.chamados.interfaces.MetricasChamado;
import com.projeto.chamados.data.ChamadoEntity;
import com.projeto.chamados.data.UsuarioEntity;
import com.projeto.chamados.repository.ChamadoRepository;
import com.projeto.chamados.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.Clock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author heitor
 */

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    private final Clock clock;

    public ChamadoService(UsuarioRepository usuarioRepository,
                          ChamadoRepository chamadoRepository,
                          Clock clock) {
        this.usuarioRepository = usuarioRepository;
        this.chamadoRepository = chamadoRepository;
        this.clock = clock;
    }
    
    
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

    public ChamadoResponseDTO salvarCadastro(CadastroChamadoDTO dto){
    
        ChamadoEntity chamado = new ChamadoEntity();
        
       
        UsuarioEntity user = usuarioRepository.findById(dto.userId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        chamado.setAtivo(dto.ativo());
        chamado.setCategoria(dto.categoria());
        chamado.setDescricao(dto.descricao());
        chamado.setNomeouempresa(dto.nomeouempresa());
        chamado.setPrioridade(dto.prioridade());
        chamado.setEmail(dto.email());
        chamado.setTitulo(dto.titulo());
        chamado.setStatus(dto.status());
        chamado.setDatachamado(LocalDateTime.now(clock));
        chamado.setUser(user);
        
       chamadoRepository.save(chamado);
       
        return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getUser().getLoginid()
    );
    }
    
    
    public ChamadoResponseDTO atualizaCadastro(Long id,CadastroChamadoDTO dto){
    
    ChamadoEntity chamado = pesquisarChamadoId(id);
     
    
    chamado.setCategoria(dto.categoria());
    chamado.setDescricao(dto.descricao());
    chamado.setNomeouempresa(dto.nomeouempresa());
    chamado.setPrioridade(dto.prioridade());
    chamado.setEmail(dto.email());
    chamado.setTitulo(dto.titulo());
    chamado.setStatus(dto.status());
    chamado.setDatachamado(LocalDateTime.now(clock));
    chamadoRepository.save(chamado);
    
    return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
        chamado.getUser().getLoginid()
    );
    }
    
    public Page<ChamadoResponseDTO> listarChamados(String busca, int filtro, int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    String campo = switch (filtro) {
        case 1 -> "id";
        case 2 -> "titulo";
        case 3 -> "nomeouempresa";
        case 4 -> "email";
        case 5 -> "categoria";
        case 6 -> "prioridade";
        case 7 -> "ativo";
        default -> "titulo";
    };

    Page<ChamadoEntity> pagina = chamadoRepository.buscarPorCampo(campo, busca, pageable);

    return pagina.map(this::toDTO);
}
    
    
    public ChamadoEntity pesquisarChamadoId(Long id){
    
        return chamadoRepository.findById(id).orElse(null);
    }
    
    public ChamadoResponseDTO pesquisarChamadoIdDTO(Long id){
    
        var chamado  = chamadoRepository.findById(id).orElse(null);
        
        if(chamado==null){
        
            return null;
        }
        
        else{
        
            return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getId()
    );
        
        }
    }
    
    public ChamadoResponseDTO pesquisarChamadoEmail(String email){
    
        var chamado = chamadoRepository.findByEmail(email);
                
       if(chamado==null){
        
            return null;
        }
        
        else{
        
            return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getId()
    );
    }}
    
    public ChamadoResponseDTO pesquisarChamadoCategoria(String categoria){
    
        var chamado = chamadoRepository.findByCategoria(categoria);
        
        
        if(chamado==null){
        
            return null;
        }
        
        else{
        
            return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getId()
    );
    }
    
    }
    
    public ChamadoResponseDTO pesquisarChamadoStatus(String status){
    
        var chamado = chamadoRepository.findByStatus(status);
        
        if(chamado==null){
        
            return null;
        }
        
        else{
        
            return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getId()
    );
    }
    }
    
    
    public ChamadoResponseDTO pesquisarChamadoPrioridade(String prioridade){
    
        var chamado = chamadoRepository.findByStatus(prioridade);
        
        if(chamado==null){
        
            return null;
        }
        
        else{
        
            return new ChamadoResponseDTO(
        chamado.getId(),
       chamado.getTitulo(),
        chamado.getDescricao(),
        chamado.getAtivo(),
        chamado.getCategoria(),
        chamado.getPrioridade(),
        chamado.getStatus(),
        chamado.getEmail(),
        chamado.getNomeouempresa(),
        chamado.getDatachamado(),
       chamado.getId()
    );
    }
    }

    public void deletaChamado(Long id){
    
         ChamadoEntity chamado = pesquisarChamadoId(id);
         chamadoRepository.delete(chamado);
    }
    
    
    public MetricasChamado metricas(long id){
    
        return chamadoRepository.metricas(id);
    }
}
