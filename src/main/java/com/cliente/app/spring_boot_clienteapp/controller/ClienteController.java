package com.cliente.app.spring_boot_clienteapp.controller;

import com.cliente.app.spring_boot_clienteapp.models.entity.Ciudad;
import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.service.ICiudadService;
import com.cliente.app.spring_boot_clienteapp.models.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    @Autowired
    private ICiudadService ciudadService;
    @GetMapping("/")
    public String listarClientes(Model model){

        List<Cliente> listadoClientes = clienteService.listarTodos();
        model.addAttribute("titulo","Lista de Clientes");
        model.addAttribute("clientes",listadoClientes);
        return "/views/clientes/listar";
    }
    @GetMapping("/create")
    public String crear(Model model){

        Cliente cliente = new Cliente();

        List<Ciudad> listCiudades = ciudadService.listaCiudades();
        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);
        return "/views/clientes/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,Model model) {
        List<Ciudad> listCiudades = ciudadService.listaCiudades();
        if (result.hasErrors()){
            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("ciudades", listCiudades);
            System.out.println("Existieron Errores en el formulario");
            return "/views/clientes/frmCrear";
        }

        clienteService.guardar(cliente);
        System.out.println("Cliente guardado con exito!!!");
        return "redirect:/views/clientes/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long idCliente, Model model){

        Cliente cliente = clienteService.buscarPorId(idCliente);

        List<Ciudad> listCiudades = ciudadService.listaCiudades();
        model.addAttribute("titulo", "Formulario: Editar Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);
        return "/views/clientes/frmCrear";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idCliente){

        clienteService.eliminar(idCliente);
        System.out.println("Registro Eliminado con exito!!!");

        return "redirect:/views/clientes/";
    }

}
