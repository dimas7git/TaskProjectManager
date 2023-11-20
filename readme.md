# Trabalho Prático - Desenvolvimento Web III

Este é o segundo trabalho prático para a disciplina de Desenvolvimento Web III do Instituto Federal de Educação, Ciência e Tecnologia de São Paulo - Campus Votuporanga. O objetivo é implementar um sistema Cliente-Servidor utilizando a arquitetura aprendida em sala de aula.

## Grupo

- **Aluno:** Dimas Ferreira
- **Aluno:** Matheus Sass

## Descrição do Projeto

Este projeto consiste na implementação de um sistema ERP, onde o grupo deverá criar um conjunto de APIs e uma interface gráfica para realizar o CRUD referente a duas tabelas relacionadas no formato 1:N. A escolha do módulo fica a cargo do grupo, e é necessário seguir as instruções fornecidas.

### Tabelas

Para cada tabela, é necessário implementar 5 operações de CRUD no formato de APIs:

- `GetAllXXX`: Retorna todos os campos da tabela, trazendo apenas os registros que não foram apagados (campo removido igual a false).
- `GetXXXByID`: Retorna todos os campos da tabela de acordo com o ID informado, trazendo somente o registro não apagado (campo removido igual a false).
- `InsertXXX`: Insere um novo registro na tabela.
- `UpdateXXX`: Atualiza um registro na tabela de acordo com o ID informado.
- `DeleteXXX`: Efetua um soft delete em um registro na tabela de acordo com o ID informado, passando o campo removido para true.

# Visão Geral

O sistema de cadastro de projetos e tarefas é uma aplicação destinada a gerenciar e organizar informações relacionadas a projetos, fornecendo uma estrutura eficiente para a criação, atualização e acompanhamento de tarefas específicas associadas a esses projetos.

## Funcionalidades Principais

1. **Cadastro de Projetos:**
   - Os usuários podem criar novos projetos, fornecendo informações como nome do projeto, data de início e custo estimado.

2. **Listagem de Projetos Ativos:**
   - Exibição de uma lista de projetos ativos, permitindo aos usuários visualizar rapidamente os projetos em andamento.

3. **Detalhes do Projeto:**
   - Visualização detalhada de informações sobre um projeto específico, incluindo suas tarefas associadas.

4. **Cadastro de Tarefas Específicas:**
   - Para cada projeto, os usuários podem adicionar tarefas específicas, atribuindo-lhes informações como descrição e status.

5. **Atualização de Status de Tarefas:**
   - Os usuários podem atualizar o status das tarefas (por exemplo, "Em Andamento", "Concluída", "Pendente") para refletir o progresso real.

6. **Autenticação e Autorização:**
   - Implementação de um sistema de autenticação seguro para garantir que apenas usuários autorizados possam acessar e manipular informações dos projetos.

7. **Segurança com Token JWT:**
   - Utilização de tokens JWT para autenticação segura, permitindo que os usuários realizem operações autenticadas sem a necessidade de reautenticação frequente.

8. **Remoção Lógica de Projetos:**
   - Implementação de remoção lógica (soft delete) de projetos, permitindo que projetos sejam marcados como removidos sem excluir permanentemente os dados.

## Benefícios

- **Organização Eficiente:**
  - Facilita a organização de projetos e tarefas, proporcionando uma visão clara do estado atual de cada projeto.

- **Acompanhamento de Progresso:**
  - Permite o acompanhamento em tempo real do progresso das tarefas, facilitando a identificação de possíveis atrasos.

- **Histórico de Atividades:**
  - Mantém um histórico de atividades, incluindo criação, atualização e remoção de projetos e tarefas, para referência futura.

- **Segurança dos Dados:**
  - Implementa práticas seguras de autenticação e autorização para proteger os dados sensíveis dos usuários.

- **Usabilidade:**
  - Oferece uma interface de usuário intuitiva e amigável para garantir uma experiência agradável ao usuário.



### Back-End

O backend foi implementado em Spring Boot. Para garantir a segurança das APIs, foi utilizado o Spring Security em conjunto com autenticação JWT (JSON Web Token). Essa abordagem oferece uma camada adicional de proteção, garantindo que apenas usuários autenticados e autorizados tenham acesso às operações do sistema.

### Front-End

O frontend foi desenvolvido em JavaScript puro, proporcionando uma experiência de usuário interativa e eficiente. A biblioteca Bootstrap foi utilizada para facilitar o desenvolvimento do layout, garantindo um design responsivo e moderno. Cada API do backend possui uma função correspondente no frontend, e há um controle de sessão para garantir que apenas usuários logados tenham acesso ao sistema.


### Tecnologias Utilizadas:

  - **Java**
  - **Spring Boot**
  - **Spring Security**
  - **JWT (JSON Web Tokens)**
  - **Banco de Dados - PostgreSQL**
  - **Front-end - JavaScript e Boostrap**    
