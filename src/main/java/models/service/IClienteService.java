package models.service;

import models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listarTodos();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Long id);
    public void eliminar(Long id);

}
