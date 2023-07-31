CREATE TABLE T_CLIENTE(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100)
);

CREATE TABLE T_PRODUTO(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    VALOR_UNIT NUMERIC(20,2)
);

CREATE TABLE T_PEDIDO(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE_ID INTEGER REFERENCES T_CLIENTE(ID),
    DATA_PEDIDO TIMESTAMP,
    VALORPEDIDO NUMERIC(20,2)
);

CREATE TABLE T_ITENS_PEDIDO(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PEDIDO_ID INTEGER REFERENCES T_PEDIDO(ID),
    PRODUTO_ID INTEGER REFERENCES T_PRODUTO(ID),
    QUANTIDADE INTEGER
);

