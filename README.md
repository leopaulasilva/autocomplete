# API de Autocomplete em Java com Spring Boot

Este repositório contém uma API RESTful desenvolvida em Java utilizando o framework Spring Boot, projetada para oferecer funcionalidades de autocomplete sobre uma lista de pessoas.

## Busca Binária (Binary Search) e Suas Vantagens

A busca binária é um algoritmo eficiente para encontrar um elemento em um conjunto ordenado de dados. Funciona dividindo repetidamente pela metade o espaço de busca até que o elemento desejado seja encontrado ou não exista mais espaço para busca. Aqui estão algumas vantagens da busca binária:

- **Eficiência**: Comparada à busca linear, que tem complexidade O(n), a busca binária tem complexidade O(log n), onde n é o número de elementos no conjunto. Isso significa que é muito mais rápida para grandes volumes de dados.

- **Ordenação Requerida**: Uma das principais condições para aplicar a busca binária é que os dados estejam ordenados. Isso permite dividir o espaço de busca de maneira eficiente.

- **Aplicação em Diversos Contextos**: Além de buscas em estruturas de dados como árvores binárias de busca, a busca binária é fundamental em muitos algoritmos de ordenação e em outras aplicações onde a ordenação é um pré-requisito.

A implementação da busca binária em uma árvore de busca binária (BST) na API de autocomplete permite resultados rápidos ao buscar pessoas por nome, garantindo uma experiência eficiente para os usuários finais.

## Funcionalidades Principais

1. **Listar Todas as Pessoas**
    - **Endpoint:** `GET /api/linearSearch`
    - **Descrição:** Retorna todas as pessoas cadastradas.
    - **Uso:** Utiliza uma busca linear para recuperar os dados.

2. **Listar Todas as Pessoas Utilizando Binary Search Tree (BST)**
    - **Endpoint:** `GET /api/binarySearchTree`
    - **Descrição:** Retorna todas as pessoas utilizando uma estrutura de árvore de busca binária.
    - **Uso:** Mais eficiente para grandes volumes de dados.

3. **Buscar Pessoas por Nome (Autocomplete)**
    - **Endpoint:** `GET /api/pessoas/{nome}`
    - **Descrição:** Retorna pessoas cujos nomes começam com o nome fornecido.
    - **Uso:** Utiliza uma árvore de busca automática para resultados rápidos.

4. **Criar Nova Pessoa**
    - **Endpoint:** `POST /api/pessoas`
    - **Descrição:** Cria uma nova entrada de pessoa.
    - **Uso:** Envie os dados da pessoa no corpo da requisição.

5. **Atualizar Pessoa Existente**
    - **Endpoint:** `PUT /api/pessoas/{id}`
    - **Descrição:** Atualiza os dados de uma pessoa existente pelo seu ID.
    - **Uso:** Envie os dados atualizados da pessoa no corpo da requisição.

6. **Excluir Pessoa**
    - **Endpoint:** `DELETE /api/pessoas/{id}`
    - **Descrição:** Remove uma pessoa pelo seu ID.
    - **Uso:** Especifique o ID da pessoa a ser excluída na URL.

## Configurações Importantes

- **Cache com Caffeine**
    - Utilizado para armazenar em cache a árvore de busca automática para consultas rápidas.
    - Configurado com uma capacidade inicial de 100 entradas e um limite máximo de 500 entradas, expirando após 10 minutos de inatividade.

- **Configuração do Banco de Dados H2**
    - Utilizado um banco de dados em memória H2 para armazenamento temporário de dados.
    - Configurações padrão:
        - URL: jdbc:h2:mem:testdb
        - Usuário: sa
        - Senha: password

- **Configuração do Log4j2**
    - Utilizado para logging dentro da aplicação, com arquivos de configuração localizados em `classpath:log4j2.xml`.

## Tecnologias Utilizadas

    - Java 11
    - Spring Boot 2.5.x
    - Maven para gerenciamento de dependências
    - H2 Database para armazenamento temporário
    - Caffeine para cache de dados em memória
    - Log4j2 para logging

### Documentação de Referência
Para mais informações, considere as seguintes seções:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring Cache Abstraction](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#io.caching)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guias
Os guias a seguir ilustram como utilizar algumas funcionalidades concretamente:
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)



## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone git@github.com:leopaulasilva/autocomplete.git
   cd autocomplete

2. Execute:
    ```bash
   mvn spring-boot:run
