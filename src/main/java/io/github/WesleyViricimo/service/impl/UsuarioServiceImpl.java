package io.github.WesleyViricimo.service.impl;

import io.github.WesleyViricimo.domain.entity.Usuario;
import io.github.WesleyViricimo.domain.repository.UsuarioRepository;
import io.github.WesleyViricimo.exception.SenhaInvalidaException;
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

    public UserDetails autenticar(Usuario usuario) {
       UserDetails user = loadUserByUsername(usuario.getLogin());//Carregando informações do usuario através do nome de usuario
       boolean senhaCorreta = encoder.matches(usuario.getSenha(), user.getPassword()); //Compara senha que o usuario digitou com a senha que está salva no banco de dados (primeiro parâmetro SEMPRE deve ser a senha digitada)
       if(senhaCorreta) {
           return user;
       }
       throw new SenhaInvalidaException();
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
