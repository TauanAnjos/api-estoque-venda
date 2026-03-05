package com.tauan_estoque_venda.service;

import com.tauan_estoque_venda.dtos.UsuarioRequest;
import com.tauan_estoque_venda.dtos.UsuarioResponse;
import com.tauan_estoque_venda.entity.Permissao;
import com.tauan_estoque_venda.entity.Usuario;
import com.tauan_estoque_venda.exception.PermissaoNotFoundException;
import com.tauan_estoque_venda.exception.UserNotFoundException;
import com.tauan_estoque_venda.repository.PermissaoRepository;
import com.tauan_estoque_venda.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissaoRepository permissaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, PermissaoRepository permissaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.permissaoRepository = permissaoRepository;
    }
    public UsuarioResponse cadastrarUsuario(UsuarioRequest request){
        Usuario newUser = new Usuario();
        newUser.setNome(request.nome());
        newUser.setEmail(request.email());
        Permissao perm = permissaoRepository.findById(request.permissao_id()).orElseThrow(()-> new PermissaoNotFoundException("Permissão não encontrada"));
        newUser.setPermissao(perm);
        newUser.setSenha(passwordEncoder.encode(request.senha()));
        newUser.setAtivo(true);
        usuarioRepository.save(newUser);
        return new UsuarioResponse(newUser.getNome(), newUser.getEmail(), perm.getId(), newUser.isAtivo());
    }

    public List<UsuarioResponse> listarUsuariosAtivos(){
        var users = usuarioRepository.findAll();
        return users.stream().filter(Usuario::isAtivo).map(user -> new UsuarioResponse(user.getNome(), user.getEmail(), user.getPermissao().getId(), user.isAtivo())).toList();
    }
    public void deletarUsuario(Integer userId){
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        user.setAtivo(false);
        usuarioRepository.save(user);
    }
}
