/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.controller;
import com.projeto.chamados.data.ChamadoEntity;
import com.projeto.chamados.dto.CadastroChamadoDTO;
import com.projeto.chamados.dto.ChamadoResponseDTO;
import com.projeto.chamados.service.ChamadoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author heitor
 */

@RestController
@RequestMapping("/funcao")
public class ChamadoRestController {
    
    @Autowired
    ChamadoService chamadoservice;
    
    @CrossOrigin("*")
    @PostMapping("/criar")
    public ResponseEntity<ChamadoResponseDTO> criarChamado(@RequestBody CadastroChamadoDTO dto){
    
        var novochamado = chamadoservice.salvarCadastro(dto);
        return new ResponseEntity<>(novochamado,HttpStatus.CREATED);
    }
    
    @CrossOrigin("*")
    @PutMapping("/editar/{id}")
    public ResponseEntity<ChamadoResponseDTO> editarChamado(@PathVariable Long id,@RequestBody CadastroChamadoDTO dto){
    
        var novochamado = chamadoservice.atualizaCadastro(id, dto);
        return new ResponseEntity<>(novochamado,HttpStatus.OK);
    }
    
    @CrossOrigin("*")
    @GetMapping("/listar")
    public ResponseEntity<List<ChamadoResponseDTO>> listarChamado(){
    
        var lista = chamadoservice.listaChamados();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @CrossOrigin("*")
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ChamadoResponseDTO> pesquisaChamadoId(@PathVariable Long id){
    
        var lista = chamadoservice.pesquisarChamadoIdDTO(id);
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @CrossOrigin("*")
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity apagarChamadoId(@PathVariable Long id){
    
        chamadoservice.deletaChamado(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
