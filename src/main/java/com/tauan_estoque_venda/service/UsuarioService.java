package com.tauan_estoque_venda.service;

import com.tauan_estoque_venda.config.security.AuthenticatedUser;
import com.tauan_estoque_venda.dtos.JWTUserData;
import com.tauan_estoque_venda.dtos.UsuarioRequest;
import com.tauan_estoque_venda.dtos.UsuarioResponse;
import com.tauan_estoque_venda.dtos.UsuarioUpdatedDTO;
import com.tauan_estoque_venda.entity.Permissao;
import com.tauan_estoque_venda.entity.Usuario;
import com.tauan_estoque_venda.exception.PermissaoNotFoundException;
import com.tauan_estoque_venda.exception.UserNotFoundException;
import com.tauan_estoque_venda.repository.PermissaoRepository;
import com.tauan_estoque_venda.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public UsuarioResponse buscarUsuarioId(Integer userId){
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        Permissao perm = permissaoRepository.findById(user.getPermissao().getId()).orElseThrow(() -> new PermissaoNotFoundException("Permissão não encontrada"));
        return new UsuarioResponse(user.getNome(), user.getEmail(), perm.getId(), user.isAtivo());
    }
    public List<UsuarioResponse> listarUsuariosAtivos(){
        var users = usuarioRepository.findAll();
        return users.stream().filter(Usuario::isAtivo).map(user -> new UsuarioResponse(user.getNome(), user.getEmail(), user.getPermissao().getId(), user.isAtivo())).toList();
    }
    public void deletarUsuario(Integer userId){
        JWTUserData userLogged = AuthenticatedUser.get();
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        if (!userLogged.userId().equals(user.getId().longValue())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para deletar esse usuário");
        }
        user.setAtivo(false);
        usuarioRepository.save(user);
    }
    public UsuarioResponse atualizarUsuario(Integer id, UsuarioUpdatedDTO updated){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        if (!passwordEncoder.matches(updated.senhaAntiga(), usuarioExistente.getSenha())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
        }
        Permissao perm = permissaoRepository.findById(updated.permissao_id()).orElseThrow(()-> new PermissaoNotFoundException("Permissão não encontrada"));
        String novaSenha = passwordEncoder.encode(updated.novaSenha());
        usuarioExistente.setNome(updated.nome());
        usuarioExistente.setSenha(novaSenha);
        usuarioExistente.setPermissao(perm);
        usuarioRepository.save(usuarioExistente);
        return new UsuarioResponse(usuarioExistente.getNome(), usuarioExistente.getEmail(), perm.getId(), usuarioExistente.isAtivo());
    }
}
