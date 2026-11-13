# ⚙️ Planeja API — Back-end de Gestão Financeira

API REST desenvolvida em **Java** com **Spring Boot** para gerenciamento financeiro pessoal, fornecendo endpoints para controle de cartões de crédito, categorias e lançamentos.

> 🔗 **Repositório Front-end:** Esta API alimenta uma interface web desenvolvida em Angular. Confira o código do front-end aqui: **[Planeja App (Angular)](https://github.com/sarvioalves/planeja-app)**

---

## 🚀 Funcionalidades da API

- **Cartões:** Cadastro e listagem de cartões de crédito/débito.
- **Categorias:** Gerenciamento de categorias para receitas e despesas.
- **Lançamentos:** Registro e consulta de movimentações financeiras.
- **Arquitetura REST:** Padronização de respostas HTTP e persistência relacional.

---

## 🛠️ Tecnologias Utilizadas

* **[Java](https://oracle.com)** — Linguagem principal
* **[Spring Boot](https://spring.io)** — Criação da API REST
* **[Spring Data JPA](https://spring.io)** — Persistência e acesso a dados
* **[Maven](https://apache.org)** — Gerenciamento de dependências
* **Banco de Dados:** *[Informe aqui o banco usado, ex: PostgreSQL / MySQL / H2]*

---

## ⚙️ Como Executar Localmente (IntelliJ ou Terminal)

### Pré-requisitos
* **Java JDK 17+** instalado
* **Maven** (ou utilizar o Maven Wrapper incluso no projeto)

### Passo a passo
1. **Clone o repositório:**
   ```bash
   git clone https://github.com
   cd planeja-api
   ```
2. **Configure o banco de dados:**
   Verifique as credenciais e a URL de conexão no arquivo `src/main/resources/application.yaml`.
3. **Execute a aplicação:**
   * **Pelo IntelliJ IDEA:** Abra o projeto, aguarde o Maven indexar as dependências e execute a classe `PlanejaApiApplication.java`.
   * **Pelo Terminal:**
     ```bash
     ./mvnw spring-boot:run
     ```
4. A API estará rodando em `http://localhost:8080`.

---

## 👨‍💻 Autor

Desenvolvido por **Sárvio Alves** — [GitHub](https://github.com/sarvioalves)
