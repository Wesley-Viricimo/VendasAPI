package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //Definindo que a classe será uma classe de controle de requisições rest
@RequestMapping("/api/clientes")//Quando o usuário acessar este endpoint irá acessar os métodos desta classe
public class ClienteController {

    @RequestMapping(value = {"/hello/{nome}", "/api/hello"},
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"}//Consumes permite que o usuário envie um json ou xml no corpo da requisição
            )
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente /*Recebendo o json ou xml do corpo da requisição e transformando em objeto*/) {
        return String.format("Hello %s ", nomeCliente);
    }
}
