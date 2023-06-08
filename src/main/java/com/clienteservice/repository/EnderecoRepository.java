package com.clienteservice.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clienteservice.domain.Cliente;
import com.clienteservice.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    Endereco findByCodigo(String codigo);

    List<Endereco> findAllByCliente(Cliente cliente, Pageable pageable);

    Endereco findByClienteAndIndice(Cliente cliente, int indice);

    List<Endereco> findByClienteAndCidade(Cliente cliente, String cidade, Pageable pageable);

}
