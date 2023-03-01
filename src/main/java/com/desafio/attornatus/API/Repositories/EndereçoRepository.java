package com.desafio.attornatus.API.Repositories;

import com.desafio.attornatus.API.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndereçoRepository extends JpaRepository<Endereco,Long> {

    List<Endereco> findByPessoaId(Long pessoaId);

}
