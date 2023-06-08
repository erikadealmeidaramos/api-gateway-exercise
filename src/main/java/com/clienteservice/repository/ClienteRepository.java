package com.clienteservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clienteservice.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Page<Cliente> findAll(Pageable pageable);

    Cliente findByCodigo(String codigo);

}
