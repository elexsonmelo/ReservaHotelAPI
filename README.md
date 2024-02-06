# :hotel: Hotel em Cancun
> Projeto Backend para realização de reserva de quarto no último hotel em Cancún. <br/>
> Este projeto foi desenvolvido com foco em POO. Foram desenvolvidos testes unitários e de integração para as camadas repository, service e controller.
> Além disso, também foram implementados testes para validar as regras de negócio, bem como as verificações de requisitos sobre estadia, consulta e realização de uma reserva.
> Há também o consumo de uma API externa, que é a API do VIACEP, que retornará consultas reais, de endereços reais consultados via requisição.

# Requisitos Funcionais
- Padrão REST para comunicação;
- Permitir cadastrar um cliente;
- Permitir criar uma reserva, mediante regras de negócio;
- Permitir consultar a disponibilidade do quarto, e retornar uma resposta caso o mesmo esteja disponível;
- Utilizaremos o Postman para testar, e inserir dados na aplicação.

# Regras de negócio
- A API será mantida pelo departamento de TI do hotel;
- Para efeitos do TESTE, assumimos que o hotel tem apenas um quarto disponível;
- Fazer integração com VIACEP para consulta de endereço quando for digitado CEP;
- Para dar oportunidade a todos de reservar o quarto, a estadia não pode ser superior a 3 dias e não pode ser reservada com mais de 30 dias de antecedência;
- A data de início da estadia tem que ser no minimo com UM dia de antencedencia do dia da reserva;
- Para simplificar o caso de uso, um “DIA” no quarto do hotel começa das 00h00 às 23h59:59;
- Cada usuário final pode verificar a disponibilidade do quarto, fazer uma reserva, cancelá-la ou modificá-la;
- Logging de Eventos: Implemente um sistema de logs para registrar eventos importantes na API, como novas reservas, cancelamentos e modificações.
  Isso facilitará a identificação de problemas, a auditoria de atividades e a análise de eventuais falhas;
- Crie uma documentação básica para a API (SWAGGER AND README), descrevendo os endpoints disponíveis;

# Rotas da Aplicação

Para as entidades Reserva, Quarto, Cliente e Endereço foram criadas as 5 rotas padrões que compõem um CRUD:

- create
- findById
- findAll
- update
- delete

# Abaixo seguem as descrições das rotas:

## :bookmark: Reserva

### [GET] /reservas
Busca que retorna todas as reservas do hotel.<br/>
Além dos parâmetros padrões,também pode-se utilizar o parâmetro "clienteId" para filtrar a reserva pelo id do cliente. <br/>

Exemplo do corpo da requisição:

```
{
  [
        {
            "id": 1,
            "valorReserva": 1200.0
            "dataEntrada": "2024-01-12T12:00:00Z",
            "dataSaida": "2024-01-15T12:00:00Z",
            "clienteId": 1,
            "quartoId": 1,
        },
        ...
    ]
}
```
### [POST] /reservas
Registra uma nova reserva no sistema do hotel. Exemplo de corpo da requisição:
``` 
{
  "dataEntrada": "2024-01-12T12:00:00Z",
  "dataSaida": "2024-01-15T12:00:00Z",
  "clienteId": 1,
  "quartoId": 1,
} 
```

## :bed: Quarto

### [GET] /quartos
Retorna todos os quartos do hotel.
### [POST] /quartos
Registra um quarto no hotel. 

Exemplo de corpo da requisição:
``` 
{
  "nome": "201",
  "estaDisponivel": false
} 
```
    
### [GET] /quartos/:id
Retorna o quarto identificado pelo id.
### [PUT] /quartos/:id
Atualiza as informações do quarto identificado pelo id.
### [DELETE] /quartos/:id
Remove o quarto do sistema, quando este não está disponível para reserva.

## :curly_haired_man: Cliente

### [GET] /clientes
Busca que retorna todas as pessoas cadastradas no hotel.
### [POST] /clientes
Registra uma pessoa no sistema do hotel. 

Exemplo de corpo da requisição:
``` 
{
  "id": "1",
  "nome": "Marcos Aurelio",
  "cpf": "12345678901",
  "email": "aurelio@email.com",
  "endereco": "02169030"
} 
```
    
### [GET] /clientes/:id
Retorna o cliente identificado pelo id.
### [PUT] /clientes/:id
Atualiza as informações do cliente identificado pelo id.
### [DELETE] /clientes/:id
Remove o cliente do sistema, quando esta não possui dependência com outros registros.

# :abacus: Tecnologias Utilizadas
- SpringBoot, Hibernate, JPA, Maven;
- Testes unitários e de Integração com JUnit 5 e Mockito;





