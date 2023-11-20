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

### Back-End

O backend foi implementado em Spring Boot. Para garantir a segurança das APIs, foi utilizado o Spring Security em conjunto com autenticação JWT (JSON Web Token). Essa abordagem oferece uma camada adicional de proteção, garantindo que apenas usuários autenticados e autorizados tenham acesso às operações do sistema.

### Front-End

O frontend foi desenvolvido em JavaScript puro, proporcionando uma experiência de usuário interativa e eficiente. A biblioteca Bootstrap foi utilizada para facilitar o desenvolvimento do layout, garantindo um design responsivo e moderno. Cada API do backend possui uma função correspondente no frontend, e há um controle de sessão para garantir que apenas usuários logados tenham acesso ao sistema.
