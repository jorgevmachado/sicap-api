package br.com.jorgevmachado.sicapapi.repositories;

import br.com.jorgevmachado.sicapapi.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
