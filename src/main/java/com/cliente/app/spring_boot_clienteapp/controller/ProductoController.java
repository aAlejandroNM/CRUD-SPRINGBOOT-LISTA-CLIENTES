package com.cliente.app.spring_boot_clienteapp.controller;

import com.cliente.app.spring_boot_clienteapp.models.entity.Ciudad;
import com.cliente.app.spring_boot_clienteapp.models.entity.Cliente;
import com.cliente.app.spring_boot_clienteapp.models.entity.Producto;
import com.cliente.app.spring_boot_clienteapp.models.service.ICiudadService;
import com.cliente.app.spring_boot_clienteapp.models.service.IClienteService;
import com.cliente.app.spring_boot_clienteapp.models.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/productos/")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;


    @Secured("ROLE_USER")
    @GetMapping("/")
    public String listarProductos(Model model){

        List<Producto> listadoProductos = iProductoService.listarProductos();
        model.addAttribute("titulo","Lista de Productos");
        model.addAttribute("productos",listadoProductos);
        return "/productos/listar";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
    public String crear(Model model){

        Producto producto = new Producto();

        model.addAttribute("titulo", "Formulario: Nuevo Producto");
        model.addAttribute("producto", producto);
        return "/productos/frmCrear";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result,
                          Model model, @RequestParam("file") MultipartFile imagen,
                          RedirectAttributes attribute) throws IOException {
        if (result.hasErrors()){
            model.addAttribute("titulo", "Formulario: Nuevo Producto");
            model.addAttribute("producto", producto);
            System.out.println("Existieron Errores en el formulario");
            return "/productos/frmCrear";
        }

        if (!imagen.isEmpty()){
            //Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbsoluta = "C://Users//USUARIO//Producto//recursos";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                producto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        iProductoService.guardarProductos(producto);
        System.out.println("Producto guardado con exito!!!");
        attribute.addFlashAttribute("success","Producto guardado con exito!!!");
        return "redirect:/productos/";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") Long idProducto, Model model, RedirectAttributes attribute){

        Producto producto = null;

        if (idProducto > 0){
            producto = iProductoService.buscarProductosPorId(idProducto);
            if (producto == null){
                System.out.println("Error:  El ID del producto no existe");
                attribute.addFlashAttribute("error","ERROR: El id del producto no existe");
                return "redirect:/productos/";
            }
        }else {
            System.out.println("Error con el id del producto");
            attribute.addFlashAttribute("error","Error con el id del producto");
            return "redirect:/productos/";
        }

        model.addAttribute("titulo", "Detalle del producto " + producto.getNombreProducto());
        model.addAttribute("producto", producto);

        return "/productos/detalleProducto";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long idProducto, Model model, RedirectAttributes attribute){

        Producto producto = null;

        if (idProducto > 0){
            producto = iProductoService.buscarProductosPorId(idProducto);
            if (producto == null){
                System.out.println("Error:  El ID del producto no existe");
                attribute.addFlashAttribute("error","ERROR: El id del producto no existe");
                return "redirect:/productos/";
            }
        }else {
            System.out.println("Error con el id del producto");
            attribute.addFlashAttribute("error","Error con el id del producto");
            return "redirect:/productos/";
        }

        model.addAttribute("titulo", "Formulario: Editar Producto");
        model.addAttribute("producto", producto);

        return "/productos/frmCrear";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idProducto,RedirectAttributes attribute){

        Producto producto = null;

        if (idProducto > 0){
            producto = iProductoService.buscarProductosPorId(idProducto);
            if (producto == null){
                System.out.println("Error:  El ID del producto no existe");
                attribute.addFlashAttribute("error", "Error el id del producto no existe");
                return "redirect:/productos/";
            }
        }else {
            System.out.println("Error con el id del producto");
            attribute.addFlashAttribute("error","Error cone el id del producto");
            return "redirect:/productos/";
        }


        iProductoService.eliminarProducto(idProducto);
        System.out.println("Registro Eliminado con exito!!!");
        attribute.addFlashAttribute("warning","Registro Eliminado con exito!!!");


        return "redirect:/productos/";
    }

}
