package com.tauan_estoque_venda.controller;

import com.tauan_estoque_venda.config.security.TokenConfig;
import com.tauan_estoque_venda.dtos.LoginRequestDTO;
import com.tauan_estoque_venda.dtos.LoginResponseDTO;
import com.tauan_estoque_venda.entity.Usuario;
import com.tauan_estoque_venda.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        var userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario)authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }
}
