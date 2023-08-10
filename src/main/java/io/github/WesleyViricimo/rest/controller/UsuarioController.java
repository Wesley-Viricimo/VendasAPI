package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Usuario;
import io.github.WesleyViricimo.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha()); //Pegando a senha que o usuário enviar na requisição e criptografar
        usuario.setSenha(senhaCriptografada);//salvando a senha já criptografada
        return usuarioService.salvar(usuario);
    }
}
