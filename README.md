# 🚀 REST API - Processo Seletivo SEPLAG
  
👤 **Nome:** Henrico Tadeu Ribeiro Alves Vilela  
📞 **Telefone:** (65) 99605-6020  
📧 **E-mail:** henricovilela@gmail.com 
  
---

## 📋 Descrição do Projeto
API REST desenvolvida em Spring Boot (Java 21) para o processo seletivo da SEPLAG. A aplicação utiliza Docker para conteinerização dos serviços dependentes (PostgreSQL e MinIO).

## 🛠️ Tecnologias Utilizadas
- **Java 21**
  - **flyway** (migração)
  - **jarkata-validation** (validação dos dados das requisições)
- **Spring Boot 3**
- **PostgreSQL** (banco de dados)
- **MinIO** (armazenamento de objetos)
- **Docker** (conteinerização)

## :heavy_check_mark: Pré-requisitos
- Docker instalado
- Docker Compose versão 2.20.0 ou superior
- DBeaver para acessar o postgres (opcional)
- Adicionar em `/etc/hosts` o dns `127.0.0.1 minio`. (Isso possibilitará o acesso direto dos links temporarios do **MinIO**, caso não queria acompanhar pelo console)
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
3. Caso queira para os containers e revomer os dados salvos, na raiz do projeto execute
```bash
docker compose down -v
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
O projeto inclui um 🌐 [arquivo de exportação](./postman) do Postman com ***todas as requisições disponíveis para teste***. Atualize o **Postman** e importe o arquivo, ou importe em outro programa que suportar. <br>
### Dados de conexão com o PostgreSQL
- URL: `jdbc:postgresql://localhost:5432/seplag`
- USUARIO: `postgres`
- SENHA: `seplag#2025`
### Dados de Acesso do MinIO
Use o console para acompanhar os uploads. Certifique-se de [adicionar o dns](#heavy_check_mark-pr%C3%A9-requisitos) 🔨 para o container do **MinIO**, para acessar diretamente os links temporários.
- URL Console: ` http://localhost:9001`
- USUARIO: `minioadmin`
- SENHA: `minioadmin`
#### WSL 2
Acesse o terminal do wsl, certifique-se de [adicionar o dns](#heavy_check_mark-pr%C3%A9-requisitos) 🔨 para o container do **MinIO**. <br>
Ao usar wsl para subir a api no docker, pode baixar a foto usando o **curl**:
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
- Ao inciar a aplicação não existe nenhuma foto, será necessário fazer o upload conforme 🌐 [arquivo de exportação](./postman) para o postman, com todas as requisições.
# SEPLAG API Endpoints
- Detalhes de como deve ser o corpo das requisições pode ser acessado importando 🌐 [arquivo de exportação](./postman)
## Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/seplag/api/auth/login` | Realiza login no sistema |
| POST | `/seplag/api/auth/refresh-token` | Atualiza o token de autenticação |

## Unidades

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/seplag/api/unidade` | Lista todas as unidades (paginada) |
| GET | `/seplag/api/unidade/{id}` | Obtém uma unidade pelo ID |
| POST | `/seplag/api/unidade` | Cadastra uma nova unidade |
| PUT | `/seplag/api/unidade/{id}` | Atualiza uma unidade existente |
| DELETE | `/seplag/api/unidade/{id}` | Exclui uma unidade |

## Servidor Efetivo

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/seplag/api/servidor-efetivo` | Lista todos os servidores efetivos (paginada) |
| GET | `/seplag/api/servidor-efetivo/{id}` | Obtém um servidor efetivo pelo ID |
| GET | `/seplag/api/servidor-efetivo/unidade/{id}` | Lista servidores efetivos por unidade |
| POST | `/seplag/api/servidor-efetivo` | Cadastra um novo servidor efetivo |
| PUT | `/seplag/api/servidor-efetivo/{id}` | Atualiza um servidor efetivo existente |
| DELETE | `/seplag/api/servidor-efetivo/{id}` | Exclui um servidor efetivo |

## Servidor Temporário

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/seplag/api/servidor-temporario` | Lista todos os servidores temporários (paginada) |
| GET | `/seplag/api/servidor-temporario/{id}` | Obtém um servidor temporário pelo ID |
| POST | `/seplag/api/servidor-temporario` | Cadastra um novo servidor temporário |
| PUT | `/seplag/api/servidor-temporario/{id}` | Atualiza um servidor temporário existente |
| DELETE | `/seplag/api/servidor-temporario/{id}` | Exclui um servidor temporário |

## Lotação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/seplag/api/lotacao` | Lista todas as lotações (paginada) |
| GET | `/seplag/api/lotacao/{id}` | Obtém uma lotação pelo ID |
| GET | `/seplag/api/lotacao/pessoa/{id}` | Lista lotações por pessoa |
| GET | `/seplag/api/lotacao/unidade/{id}` | Lista lotações por unidade |
| POST | `/seplag/api/lotacao` | Cadastra uma nova lotação |
| PUT | `/seplag/api/lotacao/{id}` | Atualiza uma lotação existente |
| DELETE | `/seplag/api/lotacao/{id}` | Exclui uma lotação |

## Fotos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/seplag/api/fotos/upload/{id}` | Upload de uma foto para pessoa |
| POST | `/seplag/api/fotos/upload-multiplos/{id}` | Upload de múltiplas fotos para pessoa |
| GET | `/seplag/api/fotos/pessoa/{id}/links` | Lista links de fotos por pessoa |
| GET | `/seplag/api/fotos/pessoa/{id}` | Lista dados de fotos por pessoa |
| GET | `/seplag/api/fotos/link/{id}` | Obtém link de uma foto pelo ID |

## Endereços

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/seplag/api/enderecos` | Lista todos os endereços (paginada) |
| GET | `/seplag/api/enderecos/pessoa` | Busca endereços por nome de pessoa |
| POST | `/seplag/api/enderecos` | Cadastra um novo endereço |
| PUT | `/seplag/api/enderecos/{id}` | Atualiza um endereço existente |

## Parâmetros de Paginação e Ordenação

A maioria dos endpoints GET que retornam listas suportam os seguintes parâmetros:

- `pagina`: Número da página (começando em 0)
- `tamanho`: Quantidade de itens por página
- `ordenacao`: Campo para ordenação
- `direcao`: Direção da ordenação (ASC ou DESC)

Exemplo: `/seplag/api/unidade?pagina=0&tamanho=5&ordenacao=id&direcao=ASC`
