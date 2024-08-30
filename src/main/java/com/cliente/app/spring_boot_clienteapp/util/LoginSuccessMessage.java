package com.cliente.app.spring_boot_clienteapp.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

@Component
public class LoginSuccessMessage extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SessionFlashMapManager fManager = new SessionFlashMapManager();
        FlashMap fMap = new FlashMap();

        fMap.put("success", "Inicio de Sesion Exitoso!!");
        fManager.saveOutputFlashMap(fMap,request,response);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
