# API-RESTful-Serratec-Music

# Marcos Felix Da Silva
# 🎵 Serratec Music API

API RESTful desenvolvida para gerenciamento de plataforma de streaming de música, permitindo o cadastro e relacionamento entre usuários, artistas, músicas e playlists.

## 📋 Sobre o Projeto

Trabalho prático do curso Serratec focado na construção de uma API utilizando Spring Boot, implementando conceitos de arquitetura em camadas, persistência de dados com JPA/Hibernate e boas práticas de desenvolvimento.

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Bean Validation**
- **Swagger/SpringDoc OpenAPI**
- **Maven**

## ✅ Funcionalidades Implementadas

- ✔️ CRUDs completos para todas as entidades
- ✔️ Validação de dados com Bean Validation
- ✔️ Tratamento centralizado de exceções (@ControllerAdvice)
- ✔️ Documentação automática com Swagger
- ✔️ Relacionamentos JPA complexos (OneToOne, OneToMany, ManyToMany)
- ✔️ Persistência em PostgreSQL

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 12+
- IDE (Eclipse, IntelliJ IDEA ou VS Code)

### 1. Configurar o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE serratec_music;
```

### 2. Configurar Credenciais

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/serratec_music
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 3. Instalar Dependências

No terminal, dentro da pasta do projeto:

```bash
mvn clean install
```

### 4. Executar a Aplicação

**Opção 1 - Via Maven:**
```bash
mvn spring-boot:run
```

**Opção 2 - Via IDE:**
- Execute a classe principal anotada com `@SpringBootApplication`

### 5. Acessar a API

- **Aplicação**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

### 6. Testar os Endpoints

Use o Swagger UI ou ferramentas como:
- **Postman**
- **Insomnia**
- **Thunder Client** (VS Code)

#### Exemplo de Requisição - Criar Usuário

```json
POST http://localhost:8080/usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "perfil": {
    "telefone": "21987654321",
    "dataNascimento": "1990-05-15"
  }
}
```
