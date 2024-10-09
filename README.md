<!-- title -->
<h1 align="center">
    <span>Projeto ViaCEP</span>
    <img src="src/main/resources/static/img/viacep_logo.svg" alt="clientes project image" width="400px" align="center"> <!-- alterar imagem src-->
</h1>

<br>

<!-- badges -->
<div align="left">
    <img src="https://img.shields.io/badge/license-MIT-yellow" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/version-1.0-green" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/repo size-ALTERAR DEPOISSSS MB-orange" alt="badge icon"></img>
</div>

<br>

<!-- About -->
## <img src="https://cdn2.iconfinder.com/data/icons/flat-pack-1/64/Computer-512.png" alt="todo list image icon" width="40px" align="center"> Sobre o Projeto

O projeto consiste em uma API para gerenciar clientes. O endereço completo do cliente é obtido a partir do campo "CEP", onde fazemos uma requisição à [API externa ViaCEP](https://viacep.com.br/) para buscar as informações, como cidade, logradouro, bairro, etc...

Além disso, a cada tentativa de criar ou atualizar um cliente, são gerados dois relatórios em formato .txt:

- **CEPs Válidos**: Lista de todos os CEPs válidos usados.
- **CEPs Inválidos**: Lista de todos os CEPs que foram rejeitados por serem inválidos.

Os relatórios são gerados com os dados separados por ponto e vírgula (`;`), permitindo que sejam facilmente abertos no Excel como se fossem arquivos CSV, com as colunas organizadas corretamente.

<br>

<!-- relatorio imags -->
### <img src="https://cdn2.iconfinder.com/data/icons/education-and-learning-set-1-2/256/21-256.png" alt ="image icon" width="40px" align="center"> Relatório CEPs válidos
<img src="src/main/resources/static/img/relatorio_valido_example.png" width="1500px">

<br>

<img src="src/main/resources/static/img/excel_relatorio_valido_example.png" width="1500px">

<br>
<br>

### <img src="https://cdn2.iconfinder.com/data/icons/education-and-learning-set-1-2/256/21-256.png" alt ="image icon" width="40px" align="center"> Relatório CEPs inválidos
<img src="src/main/resources/static/img/relatorio_invalido_example.png" width="280px">

<br>

<img src="src/main/resources/static/img/excel_relatorio_invalido_example.png" width="280px">

*Endereços usados somente a fim de exemplo.*

<br>

💡 Esses relatórios ficam armazenados no diretório local: `/var/lib/docker/volumes/_data`

<hr>
<br>

<!-- Functionalities -->
## <img src="https://cdn2.iconfinder.com/data/icons/75-market-research-wildberry-vol-1/256/Guideline-256.png" alt="todo list image icon" width="40px" align="center"> Funcionalidades

- [x] Relacionamento entre Entidades
- [x] Create, Read, Update e Delete (CRUD) de clientes
- [x] Validação dos campos inputados
- [x] Lançamento de exceções customizadas
- [x] Leitura e escrita de arquivos, utilizando `BufferedReader` and `BufferedWriter`
- [x] Testes unitários
- [x] Documentação no Swagger

<hr>
<br>

<!-- Technologies -->
## <img src="https://cdn4.iconfinder.com/data/icons/general-office/91/General_Office_48-256.png" alt="todo list image icon" width="40px" align="center"> Tecnologias
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Spring Web
- OpenAPI (Swagger)
- PostgreSQL
- Junit, Mockito and AssertJ
- Docker 27.0.3

<hr>
<br>

<!-- Diagram -->
### <img src="https://cdn3.iconfinder.com/data/icons/web-design-development-flat-colors/48/flow_chart-512.png" alt ="image icon" width="40px" align="center"> Diagrama de relacionamento

A aplicação trabalha com 2 entidades:

- Cliente
- Endereco

<br>

![diagrama das entidades](src/main/resources/static/img/clientes_diagram.png)

<hr>
<br>

## <img src="https://cdn1.iconfinder.com/data/icons/internet-45/64/http-link-internet-domain-1024.png" alt ="image icon" width="40px" align="center"> Endpoints


| Método Http | URI | Descrição | Status Code esperado |                  
| :---:       | :--- |  :---    | :---:                |
| DELETE | `http://localhost:8080/api/clientes/{id}` | Deleta um cliente através do #ID      | 200 |
| GET    | `http://localhost:8080/api/clientes`      | Retorna todos os clientes cadastrados | 200 |
| GET    | `http://localhost:8080/api/clientes/{id}` | Retorna um cliente através do #ID     | 200 |
| POST   | `http://localhost:8080/api/clientes`      | Cadastra um cliente                   | 201 |
| PUT    | `http://localhost:8080/api/clientes/{id}` | Atualiza um cliente através do #ID    | 200 |


<hr>
<br>

<!-- Validations -->
## <img src="https://cdn4.iconfinder.com/data/icons/rating-validation-3/128/validation_stamp_approval_approve_check-512.png" alt ="image icon" width="40px" align="center"> Validações
Para evitar confusões ao cadastrar ou atualizar um cliente, todos os atributos foram validados.

<br>

### CEP

- Não pode ser NULL.
- Deve ter exatamente 8 caracteres. Nem mais, nem menos.
- Deve conter apenas numbers.
- Deve existir --> É aqui que vamos consultar a API externa viaCEP

<br>
<br>

### Cliente

- **nome**
    - Não pode ser NULL.
    - Deve ter até 100 caracteres.

<br>

- **dataDeNascimento**
    - Não pode ser NULL.
    - Deve ser maior de 18 anos.

<br>

- **cpf**
    - Não pode ser NULL.
    - Deve ter até 11 caracteres.
    - Deve ter apenas números.
    - Deve ser unico.
    - Verificar se o CPF existe *(ainda nao foi implementado)*

<br>

- **email**
    - Não pode ser NULL.
    - Deve possuir `@` e `.com`
    - Deve ter até 100 caracteres.
    - Deve ter apenas números.
    - Deve ser unico.
    - Verificar se o email existe *(ainda nao foi implementado)*

<br>

- **telefone**
    - Não pode ser NULL.
    - Deve ter até 9 caracteres.
    - Deve ter apenas números.
    - O primeiro dígito deve ser o número *9*
    - Deve ser unico.
    - Verificar se o telefone existe *(ainda nao foi implementado)*

<br>

- **numeroCasa**
    - Não pode ser NULL.
    - Deve ser um Integer e NÃO uma String

<hr>
<br>

<!-- Custom Exception Handler -->
## <img src="https://cdn4.iconfinder.com/data/icons/common-app-symbols-round-colored/1024/caveat_proviso_disclaimer_exception_app_round_colored-512.png" alt ="image icon" width="40px" align="center"> Lancamento de exceções customizadas

Todas as exceções foram personalizadas para um melhor entendimento do usuário

**Exemplo 1**

![exception example](src/main/resources/static/img/exception_handler_example1.png)	
	
<br>

**Exemplo 2**

![exception example](src/main/resources/static/img/exception_handler_example2.png)	

<hr>
<br>

## Documentação no Swagger

![swagger image example](src/main/resources/static/img/swagger_image.png)	

<br>

<hr>
<br>

<!-- Build and run -->
## <img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/rocket-256.png" alt="todo list image icon" width="40px" align="center"> Rodando a aplicação

### Requisitos
- [git](https://git-scm.com/downloads)
- [Docker](https://docs.docker.com/desktop/wsl/)

<br>

### Passo a passo

1. Clone esse repositório
    ```bash
    git clone hhttps://github.com/lGabrielDev/projeto_viaCEP
    ```
<br>

2. Vá ao diretorio

    ```bash
    cd projeto_viaCEP
    ```

<br>

3. Atribua os valores nas variáveis de ambiente

    <img alt="environment variables image example" src="src/main/resources/static/img/env_image.png" width="350px">

<br>

4.  Suba os containers

    ```bash
    docker compose up -d;
    ```

<br>

5. Acesse o swagger: `http://localhost:8080/swagger.html`

<hr>
<br>

<!-- Credits -->
<h2>
    <img src="https://cdn4.iconfinder.com/data/icons/thank-you/256/Artboard_4_copy-512.png" alt="thumbs up icon image" width="40px" align="center">
    <span>Créditos</span>
</h2>

<p>As imagens usadas nesse projeto foram retiradas dos seguintes sites:</p>

- [shields.io](https://shields.io/)
- [iconfinder](https://www.iconfinder.com/)
- [storyset](https://storyset.com/)
- [vecteezy](https://www.vecteezy.com)

<span>Thanks!</span>

<hr>
<br>


<!-- License -->
## <img src="https://cdn4.iconfinder.com/data/icons/jetflat-2-multimedia-vol-3/60/0042_049_license_agreement_file_document_paper_page_sheet-512.png" alt="todo list image icon" width="40px" align="center"> Licença --> MIT

O projeto está sob a licença do [MIT](../../../../LICENSE.txt).

<hr>
<br>

<!-- Author -->
## <img src="https://cdn1.iconfinder.com/data/icons/office-work-3/200/copywriting-512.png" alt="todo list image icon" width="40px" align="center"> Autor

<br>

<div align="center">
    <img src="src/main/resources/static/img/avatar_circular.png" alt="profile avatar" width="150px">
    <p> <a href="https://github.com/lGabrielDev">Gabriel Freitas</a> 😎 </p>
</div>