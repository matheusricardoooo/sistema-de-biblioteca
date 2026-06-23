# 📚 Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em **Java** com persistência de dados em **PostgreSQL**. O projeto foi criado com o objetivo de praticar conceitos fundamentais do desenvolvimento Back-End, como programação orientada a objetos, integração com banco de dados e organização em camadas utilizando o padrão DAO (Data Access Object).

## 🚀 Tecnologias Utilizadas

* Java
* PostgreSQL
* JDBC (Java Database Connectivity)
* IntelliJ IDEA
* Git e GitHub

## 📂 Estrutura do Projeto

```text
src
├── aplicacao
│   └── Main.java
├── dao
│   └── LivroDao.java
├── database
│   ├── Conexao.java
│   └── config.properties (ignorado pelo Git)
└── model
    └── Livro.java
```

## 🗄️ Modelagem do Banco de Dados

### Tabela: livros

| Campo           | Tipo        |
| --------------- | ----------- |
| id_livro        | SERIAL (PK) |
| nome_livro      | VARCHAR     |
| nome_autor      | VARCHAR     |
| ano_publicado   | INTEGER     |
| disponibilidade | BOOLEAN     |

### Tabela: usuarios

| Campo            | Tipo        |
| ---------------- | ----------- |
| id_usuario       | SERIAL (PK) |
| nome_usuario     | VARCHAR     |
| telefone_usuario | VARCHAR     |

### Tabela: emprestimos

| Campo           | Tipo         |
| --------------- | ------------ |
| id_emprestimo   | SERIAL (PK)  |
| id_livro        | INTEGER (FK) |
| id_usuario      | INTEGER (FK) |
| data_emprestimo | DATE         |
| data_devolucao  | DATE         |

## ✅ Funcionalidades Implementadas

### Livros

* Cadastro de livros
* Listagem de livros cadastrados
* Busca de livros
* Exclusão de livros
* Controle de disponibilidade

## 🔄 Funcionalidades em Desenvolvimento

### Usuários

* Cadastro de usuários
* Listagem de usuários
* Busca de usuários
* Exclusão de usuários

### Empréstimos

* Realização de empréstimos
* Registro de devoluções
* Atualização automática da disponibilidade dos livros
* Consulta de empréstimos ativos

## ⚙️ Configuração do Projeto

Criei um arquivo `config.properties` na pasta indicada pelo projeto:

```properties
url=jdbc:postgresql://localhost:5432/biblioteca
usuario=seu_usuario
senha=sua_senha
```

**Observação:** o arquivo `config.properties` está incluído no `.gitignore` para evitar o compartilhamento de credenciais de acesso ao banco de dados.

## 🎯 Objetivos de Aprendizado

Este projeto tem como foco o estudo e a prática dos seguintes conceitos:

* Programação Orientada a Objetos (POO)
* CRUD utilizando Java e PostgreSQL
* JDBC e manipulação de banco de dados
* Organização de código em camadas
* Padrão DAO
* Tratamento de exceções
* Versionamento de código com Git e GitHub

## 📈 Melhorias Futuras

* Interface gráfica
* Validações mais robustas
* Relatórios e consultas avançadas
* Sistema de autenticação
* Testes automatizados
* Refatoração e otimizações de código

---

Desenvolvido por **Matheus** como projeto de estudos para aprimoramento em desenvolvimento Back-End com Java e Banco de Dados.
