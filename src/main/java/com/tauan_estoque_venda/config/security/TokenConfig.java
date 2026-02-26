package com.tauan_estoque_venda.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tauan_estoque_venda.dtos.JWTUserData;
import com.tauan_estoque_venda.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {
    private final Algorithm algorithm;

    public TokenConfig(@Value("${spring.jwt.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }
    public String generateToken(Usuario user){

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withExpiresAt(Instant.now().plusSeconds(100000))
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token){
        try{
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return Optional.of(new JWTUserData(
                    decode.getClaim("userId").asLong(), decode.getSubject()
            ));
        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }
}
