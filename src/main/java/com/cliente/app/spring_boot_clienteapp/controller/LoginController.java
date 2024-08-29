package com.cliente.app.spring_boot_clienteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value ="error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes attributes){

        if (error != null){
            model.addAttribute("error", "ERROR: Usuario y/o contraseña invalidos");
        }

        if (principal != null){
            attributes.addFlashAttribute("warning","ATENCION: Ya inicio sesion previamente");
            return "redirect:/home";
        }
        if (logout != null){
            model.addAttribute("success", "ATENCION: Cierre de sesion exitoso !!");
        }



        return "login";
    }
}
