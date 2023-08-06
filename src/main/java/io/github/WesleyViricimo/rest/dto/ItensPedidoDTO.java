package io.github.WesleyViricimo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
public class ItensPedidoDTO {
    private Integer produto;
    private Integer quantidade;

}
