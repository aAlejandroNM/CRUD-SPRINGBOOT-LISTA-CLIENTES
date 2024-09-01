package com.cliente.app.spring_boot_clienteapp.models.repository;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long> {

}

