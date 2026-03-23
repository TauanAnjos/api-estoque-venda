# 📦 API Estoque & Venda

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de **usuários, produtos, estoque e vendas**.

O sistema permite controlar entradas e saídas de produtos, realizar vendas com múltiplos itens e garantir consistência do estoque.

> ⚠️ Projeto em desenvolvimento — algumas regras ainda estão sendo refinadas e parte da lógica está sendo migrada para a camada de service.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.2.5
- Spring Security
- JWT (Auth0 - java-jwt)
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI (springdoc 2.5.0)
- Maven

---

## 🧠 Regras de negócio

- ✔️ Controle automático de estoque ao realizar venda
- ✔️ Bloqueio de venda com estoque insuficiente
- ✔️ Usuário só pode acessar/modificar seus próprios dados
- ✔️ Soft delete de usuários (`ativo = false`)
- ✔️ Senhas criptografadas com BCrypt
- ✔️ Autenticação via JWT

---

## 🗄️ Modelagem do banco

Entidades principais:

- `usuario`
- `permissao`
- `produto`
- `estoque`
- `venda`
- `item_venda`

### 🔗 Relacionamentos

- Usuário → possui uma Permissão
- Venda → pertence a um Usuário
- Venda → possui vários Itens
- ItemVenda → referencia Produto
- Produto → possui controle em Estoque

---

## 🔐 Autenticação

A API utiliza autenticação via **JWT (Bearer Token)**.

### 🔑 Login

Após autenticar:


---

## 📡 Endpoints

### 👤 Usuário

| Método | Endpoint | Descrição |
|--------|--------|----------|
| POST | `/usuario` | Cadastrar usuário |
| GET | `/usuario/{id}` | Buscar usuário |
| GET | `/usuario/ativos` | Listar ativos |
| PUT | `/usuario/{id}` | Atualizar |
| DELETE | `/usuario/{id}` | Desativar |

---

### 📦 Produto

| Método | Endpoint |
|--------|--------|
| POST | `/produto` |

---

### 📊 Estoque

| Método | Endpoint |
|--------|--------|
| POST | `/estoque` |

---

### 🛒 Venda

| Método | Endpoint |
|--------|--------|
| POST | `/venda` |

---

### 🧾 Item Venda

| Método | Endpoint |
|--------|--------|
| POST | `/itemVenda` |

---

### 🔑 Permissão

| Método | Endpoint |
|--------|--------|
| POST | `/permissao` |

---

## 🧪 Exemplo de venda

```json
{
  "usuarioId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 2,
      "quantidade": 1
    }
  ]
}

## ⚙️ Como rodar o projeto

### 📌 Pré-requisitos

* Java 21
* PostgreSQL
* Maven

---

### 🔧 Passos

**1. Clone o projeto:**

```bash
git clone https://github.com/TauanAnjos/api-estoque-venda.git
```

**2. Configure o banco no `application.properties`:**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

**3. Rode a aplicação:**

```bash
mvn spring-boot:run
```

---

## 📄 Swagger

Acesse a documentação interativa:

```
http://localhost:8080/swagger-ui.html
```

---

## 📁 Estrutura do projeto

```
src/main/java/com.tauan_estoque_venda
├── config
├── controller
├── dtos
├── entity
├── exception
├── repository
├── service
```

---

## ⚠️ Observações

* Projeto em evolução
* Parte da lógica ainda está sendo movida para camada de service

### 🔮 Melhorias futuras:

* Paginação
* Testes automatizados
* Logs
* Melhor controle de permissões

---

## 👨‍💻 Autor

**Tauan Anjos**

GitHub:
https://github.com/TauanAnjos
