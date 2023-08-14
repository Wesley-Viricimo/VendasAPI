package io.github.WesleyViricimo;

import io.github.WesleyViricimo.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao); //Converter o valor de expiração que é uma string em um valor long
        LocalDateTime dataHoraEpiracao = LocalDateTime.now().plusMinutes(expString);//Pegando a data e hora atual e somando com os minutos da variavel
        Instant instant = dataHoraEpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(usuario.getLogin())//Identificação do usuário que está realizando a requisição
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura) //Identificando o tipo de codificação e a chave de assinatura
                .compact();
    }
}
