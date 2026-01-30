🏨 Hotel Reserva API

API REST desenvolvida em Spring Boot para gerenciamento de hotéis, quartos, hóspedes e reservas, utilizando MySQL como banco de dados e Hibernate/JPA para persistência.

Este projeto foi criado com foco em aprendizado prático de arquitetura REST, JPA, relacionamentos entre entidades, DTOs, validações e boas práticas no desenvolvimento back-end com Java.

🚀 Tecnologias Utilizadas

Java 17

Spring Boot 4

Spring Web (REST)

Spring Data JPA

Hibernate

MySQL

Maven

Lombok

Bean Validation (Jakarta Validation)

📌 Funcionalidades
🏨 Hotel

Cadastrar hotel

Listar hotéis

Buscar hotel por ID

Atualizar hotel

Remover hotel

🚪 Quarto

Cadastrar quarto

Listar quartos

Buscar quarto por ID

Atualizar quarto

Remover quarto

Relacionamento com hotel

👤 Hóspede

Cadastrar hóspede

Listar hóspedes

Buscar hóspede por ID

Atualizar hóspede

Remover hóspede

📅 Reserva

Criar reserva vinculando hóspede e quarto

Validação de datas (check-in / check-out)

Listar reservas

Buscar reserva por ID

Remover reserva

Uso de DTOs para entrada e saída de dados

🧱 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

controller  →  service  →  repository  →  database
               ↓
              DTOs
               ↓
            entities

📂 Estrutura de Pastas
src/main/java/com/example/hotel_reserva
│
├── controller        # Controllers REST
├── service           # Regras de negócio
├── repository        # Repositórios JPA
├── entity             # Entidades JPA
├── dto                # DTOs (Request e Response)
├── exception          # Tratamento global de exceções
└── HotelReservaApplication.java

🔁 DTOs (Data Transfer Objects)

Para evitar exposição direta das entidades, o projeto utiliza DTOs, principalmente no fluxo de reservas.

📥 ReservaRequestDTO

Usado para criar uma reserva:

{
  "dataCheckIn": "2025-01-10",
  "dataCheckOut": "2025-01-15",
  "hospedeId": 1,
  "quartoId": 2
}

📤 ReservaResponseDTO

Retorno da API ao consultar reservas:

{
  "id": 1,
  "dataCheckIn": "2025-01-10",
  "dataCheckOut": "2025-01-15",
  "nomeHospede": "João Silva",
  "emailHospede": "joao@email.com",
  "numeroQuarto": "101",
  "tipoQuarto": "Luxo"
}

⚙️ Configuração do Banco de Dados
application.properties
spring.application.name=hotel-reserva

spring.datasource.url=jdbc:mysql://localhost:3306/hotel_reserva?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

server.port=8035


⚠️ Certifique-se de criar o banco de dados hotel_reserva no MySQL antes de rodar a aplicação.

▶️ Como Executar o Projeto
Pré-requisitos

Java 17+

MySQL

Maven

Passos
# Clonar o repositório
git clone https://github.com/Lcvianasz/Hotel-Reserva-API.git

# Entrar na pasta do projeto
cd Hotel-Reserva-API

# Rodar a aplicação
mvn spring-boot:run


A API ficará disponível em:

http://localhost:8035

🧪 Testes da API

Você pode testar a API usando:

Postman

Insomnia

curl

Exemplo de endpoint:
POST /api/reservas
GET  /api/reservas
GET  /api/hoteis
GET  /api/quartos
GET  /api/hospedes

🛠️ Principais Aprendizados

Relacionamentos JPA (@ManyToOne, @OneToMany)

Uso correto de DTOs

Validações com Bean Validation

Tratamento de erros

Integração Spring Boot + MySQL

Organização de projeto profissional

📌 Próximas Melhorias (Futuro)

Autenticação com Spring Security

Paginação e ordenação

Status da reserva

Cancelamento de reserva

Testes automatizados

Documentação com Swagger/OpenAPI


🆕 Atualizações Recentes

Nesta versão, a aplicação passou por uma evolução significativa, focando em boas práticas, qualidade de código e experiência de uso da API.

✨ Principais melhorias
📦 Uso de DTOs

Implementação de DTOs de Request e Response para a entidade Reserva

Separação clara entre:

Modelo de domínio (Entities)

Contratos da API (DTOs)

Maior segurança e controle sobre os dados expostos

🔄 Atualização de Status da Reserva

Criação de endpoint específico para atualizar o status da reserva

Uso de Enum para garantir valores válidos

Facilita o controle do ciclo de vida da reserva (CRIADA, CONFIRMADA, CANCELADA, etc.)

⚠️ Tratamento Global de Exceções

Implementação de um GlobalExceptionHandler

Padronização das respostas de erro da API

Tratamento para:

Recurso não encontrado

Regras de negócio

Erros de validação

Erros inesperados

Retorno de erros com estrutura clara e consistente

📄 Paginação

Implementação de paginação no endpoint de listagem de reservas

Suporte aos parâmetros:

page

size

Melhora desempenho e escalabilidade da API

📚 Documentação com Swagger (OpenAPI)

Integração com Springdoc OpenAPI

Interface gráfica para testar os endpoints

Facilita o entendimento e consumo da API

🔐 Boas Práticas de Configuração

Remoção de credenciais sensíveis do repositório

Uso de application.properties seguro para versionamento

Suporte a configurações locais via application-local.properties

🏗️ Estrutura do Projeto

Organização em camadas:

Controller

Service

Repository

DTO

Exception

Código mais limpo, legível e manutenível

📌 Essas melhorias tornam a aplicação mais robusta, segura e preparada para ambientes reais de produção.

👨‍💻 Autor

Lucas Viana Souza
Estudante de Análise e Desenvolvimento de Sistemas
Projeto desenvolvido para fins educacionais e práticos.

## 🧪 Testes Automatizados

O projeto possui testes unitários utilizando **JUnit 5** e **Mockito**, focados na camada de serviço.

### Tipos de testes implementados:
- Validação de regras de negócio da Reserva
- Testes isolados sem dependência de banco de dados
- Simulação de repositórios com Mockito

### Executar os testes:
```bash
mvn test

