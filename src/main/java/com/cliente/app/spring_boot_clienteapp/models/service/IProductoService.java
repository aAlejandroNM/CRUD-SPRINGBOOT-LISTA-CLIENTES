   package com.cliente.app.spring_boot_clienteapp.models.service;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarProductos();
    public void guardarProductos(Producto producto);
    public Producto buscarProductosPorId(Long id);
    public void eliminarProducto(Long id);

}
