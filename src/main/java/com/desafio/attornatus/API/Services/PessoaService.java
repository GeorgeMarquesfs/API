package com.desafio.attornatus.API.Services;

import com.desafio.attornatus.API.Repositories.PessoaRepository;
import com.desafio.attornatus.API.models.Pessoa;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    private EnderecoService enderecoService;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public Pessoa createP(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editP(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa getOneP(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não localizada"));
    }

    public List<Pessoa> getAllP() {
        return pessoaRepository.findAll();
    }

}