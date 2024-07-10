package com.tomaspacheco.foroHubApiRest.infra.security;

import com.tomaspacheco.foroHubApiRest.model.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /*Este es el filtro que atrapa la solicitud a la api, si no pasa el filtro, retorna no autorizado*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro esta siendo llamado");
        /*Aca agarramos el toquen del header, reemplazamos el prefijo que es el tipo de token por un string vacio, para devolver solo el token*/
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var userName = tokenService.getSubject(token); //Extraemos el userName
            if (userName != null) {
                //token valido
                var user = userRepository.findByLogin(userName);
                //Forzamso inicio de sesion
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                //Le decimos a spring que el usuario ya esta autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        /*Esta es la unica forma de llamar al filtro, si no se llama al siguiente, no pasa la solicitud*/
        filterChain.doFilter(request, response);

    }
}
