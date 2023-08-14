package io.github.WesleyViricimo;

import io.github.WesleyViricimo.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
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

    private Claims obterClaims(String token) throws ExpiredJwtException { //Caso token estiver expirado, será disparada uma exception
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)//Indicando qual foi a chave utilizada para gerar o token
                .parseClaimsJws(token)//Setando token que deverá ser realizada a codificação
                .getBody();//Irá retornar os claims do token
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token); //Recebendo as claims do token
            Date dataExpiracao = claims.getExpiration(); //Recebendo a data de expiração do token
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); //Convertendo a data de expiração do token que é do tipo date para localdatetime
            return !LocalDateTime.now().isAfter(data);//Retornando se a hora atual não é maior que a hora de expiração do token o que significa que o token estaria expirado
        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject(); //getSubject retornará o usuário que está realizando o a requisição do token
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        JwtService service = context.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("wesley").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println("O token está valido?" + isTokenValido);

        System.out.println(service.obterLoginUsuario(token));
    }

}
