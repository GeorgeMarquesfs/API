package com.desafio.attornatus.API.Repositories;

import com.desafio.attornatus.API.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
