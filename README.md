# 🚀 REST API - Processo Seletivo SEPLAG
  
👤 **Nome:** Henrico Tadeu Ribeiro Alves Vilela  
📞 **Telefone:** (65) 99605-6020  
📧 **E-mail:** henricovilela@gmail.com 
  
---

## 📋 Descrição do Projeto
API REST desenvolvida em Spring Boot (Java 21) para o processo seletivo da SEPLAG. A aplicação utiliza Docker para conteinerização dos serviços dependentes (PostgreSQL e MinIO).

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3**
- **PostgreSQL** (banco de dados)
- **MinIO** (armazenamento de objetos)
- **Docker** (conteinerização)

## ⚙️ Pré-requisitos
- Docker instalado
- Docker Compose versão 2.20.0 ou superior
- DBeaver para acessar o postgres (opcional)
  ### Execução local da api
  - Java 21
  - IDE para execução de depuração da aplicação (opcional)
    
## 🚀 Executando a Aplicação
Quando a api for executada ela irá rodar as migrações, onde irar criar a estrutura e inserir dados iniciais. <br>
Os scripts .sql estão em :file_folder: [db/migration](./src/main/resources/db/migration)
1. Clone o repositório
2. Na raiz do projeto, execute:
```bash
docker compose up -d
```
- Isso irá subir todos os containers necessários: `PostgreSQL` (5432), `MinIO` (9000), `MinIO Console` (9001) e `REST API` (8081).
- Use para acessar o `MinIO Console` o usuario **minioadmin** e senha **minioadmin** .
  ### Execução Local
  Caso queira executar apenas a api de forma local através de um IDE.<br>
  
  Subir o container do PostgreSQL:
  ```bash
  docker compose -f postgres/compose.yml up -d
  ```
  Subir o container do MinIO:
  ```bash
  docker compose -f minio/compose.yml up -d
  ```
