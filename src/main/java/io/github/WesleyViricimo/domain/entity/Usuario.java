package io.github.WesleyViricimo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "LOGIN", length = 30, unique = true)
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @Column(name = "SENHA", length = 100)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "ADMIN")
    private boolean admin;
}
