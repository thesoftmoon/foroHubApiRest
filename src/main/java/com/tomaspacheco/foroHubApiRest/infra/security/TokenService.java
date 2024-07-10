package com.tomaspacheco.foroHubApiRest.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tomaspacheco.foroHubApiRest.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    /*Aca traes los valores de la variable de ambiente*/
    @Value("${api.security.secret}")
    private String apiSecret;


    /*Aca agregas el return del JWT, usas HMAC256 como algoritmo */
    public String tokenGenerator(User user) {
        try {
            /*Aca la pass esta hardcodeada, mala practica*/
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    /*Cambias el issuer por el string con el nombre de la organizacion o usuario*/
                    .withIssuer("voll med")
                    /*Aca el usuario esta hardcodeado, mala practica*/
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(expirationDateGenerator())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
            // Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    /*Aca validamos el token de acceso a la api*/
    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException("null token");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);//Valida la firma del token
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    /*Aca generamos la validez del token, en cuanto expirara*/
    private Instant expirationDateGenerator() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
