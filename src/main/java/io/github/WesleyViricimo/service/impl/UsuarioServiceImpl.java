package io.github.WesleyViricimo.service.impl;

import io.github.WesleyViricimo.domain.entity.Usuario;
import io.github.WesleyViricimo.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UsuarioServiceImpl implements UserDetailsService { //Classe será responsável por carregar um usuário

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = repository.findByLogin(username)
               .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

       String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

       return User
               .builder()
               .username(usuario.getLogin())
               .password(usuario.getSenha())
               .roles(roles)
               .build();

    }
}
