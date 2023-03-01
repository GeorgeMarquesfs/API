package com.desafio.attornatus.API.controllers;

import com.desafio.attornatus.API.Services.EnderecoService;
import com.desafio.attornatus.API.Services.PessoaService;
import com.desafio.attornatus.API.models.Endereco;
import com.desafio.attornatus.API.models.Pessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    private final EnderecoService enderecoService;


    public PessoaController(PessoaService pessoaService, EnderecoService enderecoService) {
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> createP(@Validated @RequestBody Pessoa pessoa){
        return new ResponseEntity<>(pessoaService.createP(pessoa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editPessoa(@PathVariable Long id, @RequestBody @Validated Pessoa pessoaAtt) {
        Optional<Pessoa> pessoaExist = Optional.ofNullable(pessoaService.getOneP(id));
        if (pessoaExist.isPresent()) {
            BeanUtils.copyProperties(pessoaAtt, pessoaExist.get(), "id", "enderecos");
            Pessoa pessoaAtualizada = pessoaService.editP(pessoaExist.get());
            return ResponseEntity.ok(pessoaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getOneP(@PathVariable Long id){
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaService.getOneP(id));
        if(pessoa.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllP(){
        return new ResponseEntity<>(pessoaService.getAllP(), HttpStatus.OK);
    }


    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Endereco> addEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        try {
            Pessoa pessoa = pessoaService.getOneP(id);
            if (pessoa == null) {
                throw new RuntimeException("Pessoa não encontrada");
            }
            Endereco novoEndereco = enderecoService.createEndereco(id, endereco);
            pessoa.getEnderecos().add(novoEndereco);
            pessoaService.editP(pessoa);
            return ResponseEntity.ok(novoEndereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> getAllE(@PathVariable Long id) {
        Optional<List<Endereco>> enderecos = enderecoService.listarEnderecos(id);
        if (enderecos.isPresent()) {
            return ResponseEntity.ok(enderecos.get());
        }
        return ResponseEntity.noContent().build();
    }

}

