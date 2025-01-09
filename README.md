# Aplicação Back-end
Este projeto é uma API RESTful projetada para integrar-se a um [**Front-end**](https://github.com/FabioLuizz/locaweb-frontend-challenge) desenvolvido em Kotlin. Sua principal função é gerenciar a comunicação entre serviços, permitindo o envio de requisições e o processamento de dados de forma eficiente.



O projeto se trata de um aplicativo de e-mail responsável por gerenciar mensagens de usuário, bem como oferecer funcionalidades de organização de pastas, filtros inteligentes e com o objetivo de melhorar a produtividade e facilitar a comunicação dos usuários de forma prática e segura.



O Desenvolvimento desse projeto é para fins de conhecimentos, com objetivo de aprimorar habilidades em desenvolvimento. Foram empregadas ferramentas do Spring framework, Java como tecnologia principal, Oracle SQL Development como base de dados e Docker como gerenciador de container.



### Tecnologias Utilizadas:



- **Spring Framework**: Framework para desenvolvimento de aplicativos Java, utilizado para construir API Restful.
- **Java**: Utilizado para o desenvolvimento do Back-end para implementar a lógica de negócios.



### Arquitetura de MicrosServiços:

- **Microserviços**: Organizamos a aplicação em serviços independentes, cada um responsável por uma funcionalidade específica.

- **Comunicação entre Microserviços**: Utilizamos comunicação RESTful e troca de mensagens para a integração entre os microserviços.

  

### Outras Tecnologias:

- **Lombok**: Utilizado para reduzir a verbosidade do código, gerando automaticamente getters, setters e outros métodos.
- **Oracle Database**: Banco de dados utilizado para armazenar e gerenciar os dados da aplicação.
- **Data Transfer Objects (DTOs)**: Utilizados para consultas e persistência de dados, garantindo a integridade e consistência das informações.
- **Validação e Tratamento de Exceções Avançados**: Implementados para garantir a confiabilidade e robustez da aplicação em cenários complexos.
- **JPQL (Java Persistence Query Language)**: Utilizado para consultas personalizadas, paginação e ordenação de resultados.
- **Autenticação JWT (JSON Web Token)**: Implementada para garantir a segurança dos serviços e dados da aplicação.
- **Docker**: Gerenciamento e execução de aplicações em containers.



### Como Testar:

1. Faça o clone do repositório do projeto e instale as dependências necessárias para execução do projeto utilizando o seguinte comando.

   ```
   mvn install
   ```

3. Quando você estiver com o projeto clonado na sua maquina, através da sua IDE com o projeto aberto você pode executar a aplicação utilizando docker com o seguinte comando:

   ```
   docker compose up --build
   ```

   - **Porta**: A aplicação será exposta na porta 8080 (acesse em http://localhost:8080) com o container já em execução.
   
5. Clone também o repositório do [**Front-end**](https://github.com/FabioLuizz/locaweb-frontend-challenge) para consumir o projeto.

4. Comece pelo gerenciamento de usuários, realizando o registro e login conforme descrito na documentação.

   
