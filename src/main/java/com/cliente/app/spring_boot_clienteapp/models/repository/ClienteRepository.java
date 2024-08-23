package com.cliente.app.spring_boot_clienteapp.models.repository;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}

