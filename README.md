
# Controle de Orçamento

> Projeto desenvolvido para o challenge backend da Alura.

## História

Após alguns testes com protótipos feitos pelo time de UX de uma empresa, foi requisitada a primeira versão de uma aplicação para controle de orçamento familiar. A aplicação deve permitir que uma pessoa cadastre suas receitas e despesas do mês, bem como gerar um relatório mensal.

Para o back-end, as principais funcionalidades a serem implementadas são:

- API com rotas implementadas seguindo as boas práticas do modelo REST;
- Validações feitas conforme as regras de negócio;
- Implementação de base de dados para persistência das informações;
- Serviço de autenticação/autorização para restringir acesso às informações.

### Primeira semana
Para primeira semana, o desafio proposto foi de criar um **CRUD** com rotas implementadas seguindo as boas práticas do modelo REST.

Validações de regra de negócio para inserir e atualizar os dados e um banco de dados para gerenciamento das informações.

# **Observação**
> Devido algumas limitações da minha máquina no momento, estou criando o banco em um perfil de test usando o H2 e um arquivo de seed dos dados, assim que normalizar, criarei um perfil prod ou dev com o banco postgresql

## Documentação da API

### Receita
#### Retorna uma lista paginada das receitas cadastradas.

```http
  GET /receitas
```

| Parâmetros (Opcionais)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `page` | `Integer` | **Opcional**. Define qual página será exibida. |
| `linesPerPage` | `Integer` | **Opcional**. Define quantos registros serão exibidos. |
| `direction` | `String` | **Opcional**. Define se teremos uma ordenação ASC ou DESC. |
| `orderBy` | `String` | **Opcional**. Define campo usado para ordenação. |

#
#### Retorna uma receita de acordo com o ID informado.

```http
  GET /receitas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer. |

#
#### Adiciona uma nova receita

```http
  POST /receitas
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da receita. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da receita. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da receita.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pix aniversário",
    "valor" : 170.0
}
```
**Return - Status 201 Created**
```json
{
    "id": 3,
    "descricao": "Pix aniversário",
    "valor": 170.0,
    "data": "2022-07-10"
}
```
#
```http
  PUT /receitas/${id}
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da receita. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da receita. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da receita.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pix aniversário",
    "valor" : 200.0
}
```
**Return - Status 200 OK**
```json
{
    "id": 3,
    "descricao": "Pix aniversário",
    "valor": 200.0,
    "data": "2022-07-10"
}
```
#
```http
  DELETE /receitas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você deseja deletar. 

**Return - Status 204 No Content**
#

### Despesa
#### Retorna uma lista paginada das despesas cadastradas.

```http
  GET /despesas
```

| Parâmetros (Opcionais)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `page` | `Integer` | **Opcional**. Define qual página será exibida. |
| `linesPerPage` | `Integer` | **Opcional**. Define quantos registros serão exibidos. |
| `direction` | `String` | **Opcional**. Define se teremos uma ordenação ASC ou DESC. |
| `orderBy` | `String` | **Opcional**. Define campo usado para ordenação. |

#
#### Retorna uma despesa de acordo com o ID informado.

```http
  GET /despesas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer. |

#
#### Adiciona uma nova despesa

```http
  POST /despeas
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da despesa. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da despesa. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da despesa.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pizza",
    "valor" : 60.0
}
```
**Return - Status 201 Created**
```json
{
    "id": 5,
    "descricao": "Pizza",
    "valor": 60.0,
    "data": "2022-07-10"
}
```
#
```http
  PUT /despesas/${id}
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da despesa. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da despesa. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da despesa.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pizza",
    "valor" : 70.0
}
```
**Return - Status 200 OK**
```json
{
    "id": 5,
    "descricao": "Pizza",
    "valor": 70.0,
    "data": "2022-07-10"
}
```
#
```http
  DELETE /despesas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você deseja deletar. 

**Return - Status 204 No Content**

#
Abaixo vou deixar o arquivo de importação com as requests para o Postman
https://drive.google.com/file/d/1mWciqUPfzR_b1Iz71VGniJeBmJBoIgdC/view?usp=sharing
