package com.desafio.attornatus.API.Services;

import com.desafio.attornatus.API.Repositories.EndereçoRepository;
import com.desafio.attornatus.API.Repositories.PessoaRepository;
import com.desafio.attornatus.API.models.Endereco;
import com.desafio.attornatus.API.models.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EndereçoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;



    public EnderecoService(EndereçoRepository enderecoRepository,  PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco createEndereco(Long pessoaId, Endereco endereco) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        if (pessoaOptional.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        Pessoa pessoa = pessoaOptional.get();
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }

    public Optional<List<Endereco>> listarEnderecos(Long pessoaId){
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoaId);
        return Optional.ofNullable(enderecos);
    }
}