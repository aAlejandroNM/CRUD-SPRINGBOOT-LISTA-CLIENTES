package com.cliente.app.spring_boot_clienteapp.models.service;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.entity.Producto;
import com.cliente.app.spring_boot_clienteapp.models.repository.ClienteRepository;
import com.cliente.app.spring_boot_clienteapp.models.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private IProductoRepository iProductoRepository;
    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) iProductoRepository.findAll();
    }

    @Override
    public void guardarProductos(Producto producto) {
        iProductoRepository.save(producto);
    }

    @Override
    public Producto buscarProductosPorId(Long id) {
        return iProductoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarProducto(Long id) {
        iProductoRepository.deleteById(id);
    }
}
