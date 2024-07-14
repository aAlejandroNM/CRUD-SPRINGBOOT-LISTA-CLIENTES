package com.cliente.app.spring_boot_clienteapp.controller;

import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    @GetMapping("/")
    public String listarClientes(Model model){

        List<Cliente> listadoClientes = clienteService.listarTodos();
        model.addAttribute("titulo","Lista de Clientes");
        model.addAttribute("clientes",listadoClientes);
        return "/views/clientes/listar";
    }
    @GetMapping("/create")
    public String crear(){
        return "/views/clientes/frmCrear";
    }

}
