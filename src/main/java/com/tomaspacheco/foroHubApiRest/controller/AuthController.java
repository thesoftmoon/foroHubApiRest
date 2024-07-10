package com.tomaspacheco.foroHubApiRest.controller;

import com.tomaspacheco.foroHubApiRest.infra.security.JWTtokenDataDTO;
import com.tomaspacheco.foroHubApiRest.infra.security.TokenService;
import com.tomaspacheco.foroHubApiRest.model.user.AuthUserDTO;
import com.tomaspacheco.foroHubApiRest.model.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /*En esta entidad necesitas del DTO de authUser el login y clase que seria usuario y clave*/
    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid AuthUserDTO authUserDTO){
        /*Traes el objeto Authentication y le pasas los datos del DTO*/
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.login(), authUserDTO.pass());
        /*Le pasas el token generado arriba*/
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.tokenGenerator((User) authenticatedUser.getPrincipal());
        /*Retornas el ResponseEntity solo para fines didacticos*/
        return ResponseEntity.ok(new JWTtokenDataDTO(JWTtoken));
    }

}
