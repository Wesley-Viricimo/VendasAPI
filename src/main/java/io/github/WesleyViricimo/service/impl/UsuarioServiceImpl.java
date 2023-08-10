package io.github.WesleyViricimo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UserDetailsService { //Classe será responsável por carregar um usuário

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("jessica")){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return User
                .builder()
                .username("jessica")
                .password(encoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }
}
