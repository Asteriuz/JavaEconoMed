# Sprint 04 - *EconoMed*

- **Java Advanced**

## Integrantes do Grupo

- Augusto Barcelos Barros – RM: 98078
- Gabriel Souza de Queiroz – RM: 98570
- Lucas Pinheiro de Melo – RM: 97707
- Mel Maia Rodrigues – RM: 98266

## Detalhes

Esse projeto tem como objetivo, mostrar em detalhes, parte da nossa solução para o Challenge 2024 da empresa Plusoft.

Utilizamos um sistema de Banco de Dados relacional, para armazenar e manipular os dados necessários para o desenvolvimento do projeto.

A pasta `database`, contém arquivos referentes ao nosso banco de dados, que é responsável por mostrar um pouco mais sobre como ele é estruturado, possuindo nossos modelos lógicos e relacionais.

A pasta `requests`, contém arquivos referentes às requisições de nossos endpoints, podendo ser utilizado em algum programa como o Postman ou Insomnia para que seja possivel a vizualização e teste de nossas requisições.

## ⚠️ Sobre o projeto

Nosso projeto tem como objetivo, entregar aos clientes de operadoras de saúde, um atendimento que seja não apenas eficaz em termos de custo, mas também personalizado e de alta qualidade, visando a minimização dos gastos para a empresa.

A abordagem proposta se apoia de forma integral em análises avançadas de dados (analytics), as quais permitem que o software realize uma minuciosa caracterização do perfil de cada beneficiário, valendo-se tanto das informações já disponíveis sobre o cliente quanto de dados externos cuidadosamente selecionados. 

Esse perfilamento detalhado possibilita direcionar o beneficiário para o tipo de atendimento mais vantajoso em termos de custo, encaminhando-o para clínicas ou hospitais especializados na área correspondente à sua queixa de saúde, porém que pratiquem preços mais acessíveis para a operadora de saúde.

Nosso projeto então, facilitará a vida tanto de operadoras de saúde, que terão menos gastos, quanto os seus clientes, que encontrarão um sistema personalizado de recomendações de hospitais, clinicas médicas, etc, com boa avaliação e perto de sua casa.

Nosso projeto também precificará os planos de saúde, baseando nos dados pessoais do cliente, nos seus dados de endereço, histórico hospitalar e histórico de saúde, ajudando-os a conseguir o melhor plano possível, e ajudando a empresa, que conseguirá ter uma maior análise nesse momento.

## Instruções para uso

### 1. Clone o repositório do projeto ou baixe os arquivos do zip:

```bash
git clone https://github.com/FiapChallenge/JavaEconoMed
```

### 2. Inicialize o projeto Maven na sua IDE:

#### *No diretório do projeto, onde esta o arquivo pom.xml*

```bash
mvn clean install
```

### 3. Execute o projeto

```bash
mvn spring-boot:run
```

### 4. Teste usando seu API tester de preferência (Postman/Insomnia):
 

Copie nosso arquivo mais recente .json dentro da pasta requests, e importe em seu API tester, para que assim, veja as requisições possíveis.

## Requisitos
 
-   **Java**

## Dependências
 
-   **spring-boot-starter-web**
    -   Fornece recursos essenciais para desenvolver aplicativos da web no Spring Boot, como configuração do servidor incorporado e suporte a RESTful.
-   **spring-boot-devtools**
    -   Facilita o desenvolvimento ao oferecer ferramentas como reinicialização automática do aplicativo e configurações específicas de desenvolvimento.
-   **ojdbc11 & ojdbc8**
    -   Drivers JDBC para Oracle Database, permitindo a conexão e interação de aplicativos Java com o Oracle Database nas versões 11g e 12c, respectivamente.
-   **lombok**
    -   Reduz a verbosidade do código Java automatizando a geração de métodos padrão, como getters, setters e construtores, através de anotações.
-   **spring-boot-starter-test**
    -   Oferece suporte para testes em aplicativos Spring Boot, incluindo bibliotecas como JUnit e Mockito, simplificando a escrita e execução de testes.
-   **spring-boot-starter-data-jpa**
    -   Simplifica o acesso e manipulação de dados em bancos de dados através do Spring Data JPA, configurando automaticamente a camada de persistência para trabalhar com JPA em aplicativos Spring Boot.
-   **spring-security-test**
    - Facilita testes de segurança no Spring.
-   **java-jwt**
    -  Implementa a funcionalidade de geração e validação de tokens JWT em aplicativos Java.
-   **jakarta.validation-api**
    -   Fornece suporte para validação de dados em aplicativos Java, permitindo a definição de regras de validação em objetos de domínio.

# Funcionalidade

O sistema possui diversas funcionalidades CRUD para nossas entidades:

## 0. Autenticação

A Autenticação é uma funcionalidade que permite que um usuário possa se autenticar no sistema, para que assim, possa acessar as rotas protegidas. Para isso, utilizamos o JWT, que é um token que é gerado ao fazer o login, e que é necessário para acessar as rotas protegidas.

- **Login**
- **Register**

## 1. Cliente

O Cliente é nossa entidade "principal", pois é nele que temos todos os dados pessoais que serão usados para as análises. Em nosso cliente, podemos fazer o CRUD completo pois é nele que manipulamos seus dados.

- **Create**
- **List all**
- **Search by id**
- **Update**
- **Delete**

## 2. Convênio

O Convênio é a base de todos nossos planos de saúde, armazenando os dados de todos os planos que a empresa possuir. Aqui, temos todos os dados do Convênio, por isso, temos o CRUD completo.

- **Create**
- **List all**
- **Search by id**
- **Update**
- **Delete**

## 3. Empresa

A Empresa é uma entidade responsável por armazenar os dados de todas as empresas que aceitam nossos planos de saúde, como por exemplo, hospitais e clínicas médicas. Como a Empresa possui os dados base, então nela, temos o CRUD completo.

- **Create**
- **List all**
- **Search by id**
- **Update**
- **Delete**

## 4. Unidade

Na Unidade, armazenamos os dados de todas as unidades que uma empresa tem. Nela, temos o CRUD completo, pois por mais que ela seja ligada com a empresa, podemos fazer toda manipulação dela independentemente da empresa.

- **Create**
- **List all**
- **Search by id**
- **Update**
- **Delete**

## 5. Endereço Cliente

No Endereço Cliente, temos todos os dados de endereço do cliente, então assim, podemos analisar e manipular os dados geográficos para encontrar o melhor local para sua consulta médica, por exemplo. Aqui, não possuímos o CRUD completo, pois UM cliente possui UM endereço, então não teremos list all, e também, não podemos deletar um Endereço Cliente, pois um Cliente não pode ficar sem endereço.

- **Create**
- **Search by ClientId**
- **Update**

## 6. Endereço Unidade

No Endereço Unidade, temos todos os dados de endereço de uma unidade, então assim, podemos analisar e manipular os dados geográficos para encontrar a melhor unidade para um cliente realizar sua consulta médica, por exemplo. Aqui, não possuímos o CRUD completo, pois UMA unidade possui UM endereço, então não teremos list all, e também, não podemos deletar um Endereço Unidade, pois uma Unidade não pode ficar sem endereço.

- **Create**
- **Search by UnidadeId**
- **Update**

## 7. Histórico Hospital Cliente

No Histórico Hospital Cliente, temos todos os dados de histórico hospitalar do cliente, então assim, podemos analisar e manipular seus registros e prontuários passados, ajudando assim, na melhor precificação de um convênio, por exemplo. Aqui, não possuímos o CRUD completo, pois UM cliente possui UM histórico, então não teremos list all, e também, não podemos deletar um Histórico Hospitalar Cliente, pois um Cliente não pode ficar sem histórico hospitalar.

- **Create**
- **Search by ClienteId**
- **Update**

## 8. Histórico Saúde Cliente

No Histórico Saúde Cliente, temos todos os dados de histórico de saúde do cliente, então assim, podemos analisar e manipular seus diagnósticos, como por exemplo doenças, se fuma, alergias, etc, ajudando assim, na melhor precificação de um convênio, por exemplo. Aqui, não possuímos o CRUD completo, pois UM cliente possui UM histórico, então não teremos list all, e também, não podemos deletar um Histórico Saúde Cliente, pois um Cliente não pode ficar sem histórico de saúde.

- **Create**
- **Search by ClienteId**
- **Update**

## 9. User

O User é uma entidade responsável por armazenar os dados de login de nossos usuários, podendo ser uma Empresa ou um Cliente. Para o User, temos a AutenticacaoController, que é responsável por fazer o login e retornar um token JWT para que o usuário possa acessar as rotas protegidas, assim como fazer o cadastro.

- **Login**
- **Register**

# Estrutura

O sistema está organizado seguindo a arquitetura MVC:

- **controller**: Classes que lidam com solicitações HTTP, invocando a lógica de negócios apropriada e retornando respostas HTTP.
- **dto**: Classes que transferem dados entre diferentes partes do sistema, frequentemente refletindo a estrutura dos dados das solicitações e respostas HTTP.
- **handler**: Classes que lidam com exceções específicas ou globais no aplicativo.
- **model**: Classes que representam os objetos de negócios do aplicativo.
- **repository**: Classes ou interfaces que definem operações de acesso a dados para interagir com o banco de dados.
- **service**: Classes que contêm a lógica de negócios da aplicação, intermediando entre os controllers e os repositórios, promovendo a reutilização e a testabilidade do código.
- **config**: Classes de configurações do projeto, como por exemplo, configurações de segurança.

# Próximos passos:

- ✅ Ao deletar um Cliente, deletar também seu histórico de saúde e hospitalar.
- ✅ Adicionar Casos de Uso para cada requisição.
- ✅ Evoluir o que já possuímos.
- Começar a criar as requisições para resolução de nosso programa, por exemplo, um endpoint para Precificar um convênio.
- ✅ Fazer os relacionamentos das entidade utilizando annotations.
- Fazer integração com a api de Python.
- ✅ Discutir e analisar se são necessarios mais endpoints CRUD especificos.
- Começar os testes unitários.
- ✅ Fazer autenticação com JWT.
