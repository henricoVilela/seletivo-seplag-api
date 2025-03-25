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

## :heavy_check_mark: Pré-requisitos
- Docker instalado
- Docker Compose versão 2.20.0 ou superior
- DBeaver para acessar o postgres (opcional)
- Adicionar em `/etc/hosts` o dns `127.0.0.1 minio`. Isso possibilitará o acesso direto dos links temporarios do **MinIO**
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
## :mag_right: Testando a Aplicação
O projeto inclui um :page_facing_up: [arquivo de exportação](./postman) do Postman com todas as requisições disponíveis para teste. Importe este arquivo no Postman, ou em outro programa que suportar. <br>
### Dados de conexão com o PostgreSQL
- URL: `jdbc:postgresql://localhost:5432/seplag`
- USUARIO: `postgres`
- SENHA: `seplag#2025`
### Dados de Acesso do MinIO
Use o console para acompanhar os uploads. Certifique-se de [adicionar o dns](#heavy_check_mark-pr%C3%A9-requisitos) para o container do **MinIO**, para acessar diretamente os links temporários.
- URL Console: ` http://localhost:9001`
- USUARIO: `minioadmin`
- SENHA: `minioadmin`
#### WSL 2
Certifique-se de [adicionar o dns](#heavy_check_mark-pr%C3%A9-requisitos) para o container do **MinIO**. <br>
Ao usar wsl para subir a api no docker, acesse o terminal no wsl e pode baixar a foto usando o **curl**:
```
curl -k "SEU_LINK_TEMPORARIO" -o /var/tmp/foto.jpg
```
### Dados de Acesso Api
O usuário e senha da aplicação são armazenado em memória.
- USUARIO: `admin`
- SENHA: `senha_admin`
- Para logar na aplicação:
```
curl -X POST http://localhost:8081/seplag/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"admin", "password":"senha_admin"}'
```
- O token emitido é um `jwt` ao qual deverá ser utilizado no header `Authorization: Bearer <SEU_TOKEN_JWT>`, o mesmo foi configurado para expirar em 5 minutos.
- Quando faltar menos de um minuto para o token expirar, na próxima requisição feita a API será adicionado um header `X-Token-Expiring: true`, que pode ser utilizado pelo cliente para solicitar um novo token.
- Para gerar um novo token, a partir de token a vencer:
```
curl -X POST http://localhost:8081/seplag/api/auth/refresh-token \
     -H "Content-Type: application/json" \
     -d '{"token": <SEU_TOKEN_JWT>}'
```
#### Fotos
- Ao inciar a aplicação não existe nenhuma foto, será necessário fazer o upload conforme :page_facing_up: [arquivo de exportação](./postman) do postman.
