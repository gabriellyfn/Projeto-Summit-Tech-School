# 🎓 Summit Tech School

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-6DB33F?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

# 🏫 Sistema de Gestão de Ocorrências Escolares

Sistema de Gestão de Ocorrências Escolares desenvolvido com foco na criação de uma plataforma web para registro, acompanhamento e gerenciamento de ocorrências acadêmicas em instituições de ensino. A aplicação tem como objetivo otimizar os processos pedagógicos e administrativos, promovendo maior organização, transparência e eficiência no monitoramento de ocorrências. O sistema auxilia coordenadores, professores e equipes administrativas na tomada de decisões, oferecendo um ambiente centralizado, seguro e de fácil utilização.

---

## ⚙️ Funcionalidades

### 🔐 Autenticação e Controle de Acesso
- Login com autenticação por perfil
- Controle de permissões por usuário:
  - Professor
  - Analista de Qualidade
  - Coordenador
  - Professor Administrativo

### 👨‍🎓 Gerenciamento de Alunos e Turmas
- Cadastro de alunos
- Cadastro de turmas
- Vinculação de alunos às turmas
- Organização por semestre, turno e ano letivo

### 📋 Registro de Ocorrências
- Registro de ocorrências escolares
- Categorias e tipos de ocorrência
- Descrição detalhada do ocorrido
- Associação da ocorrência ao aluno e turma

### 📊 Consultas e Dashboard
- Histórico completo por aluno
- Filtros por período, categoria e turma
- Dashboard gerencial para coordenação
- Indicadores gráficos e relatórios

---

## 🛠️ Tecnologias Utilizadas

- Java 25
- Lombok
- Spring Boot
- Spring Security
- MySQL
- Maven
- Git
- GitHub

---

## 🏗️ Arquitetura do Projeto

O sistema será desenvolvido utilizando a arquitetura **MVC (Model-View-Controller)**, promovendo melhor organização, manutenção e escalabilidade da aplicação.

---

# Rotas da API

## 🔐 Login

### Gerar Token JWT

```bash
curl -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{
  "login": "admin",
  "senha": "admin"
}'
```

## 👤 Usuários

Listar Usuários
```bash
curl -X GET http://localhost:8080/usuarios \
-H "Authorization: Bearer SEU_TOKEN"
```

Cadastrar Usuários
```bash
curl -X POST http://localhost:8080/usuarios \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN" \
-d '{
  "nome": "Gabrielly",
  "email": "gaby@email.com",
  "login": "gaby",
  "senha": "senha123",
  "telefone": "11999999999",
  "foto": "foto.png",
  "dataNascimento": "2001-05-21",
  "perfil": {
    "id"_perfil: 1
  }
}'
```

Atualizar Usuários
```bash
curl -X PUT http://localhost:8080/usuarios/1 \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN" \
-d '{
  "nome": "Novo Nome",
  "email": "novo@email.com",
  "telefone": "11999999999",
  "foto": "nova-foto.png"
}'
```

Deletar Usuários
```bash
curl -X DELETE http://localhost:8080/usuarios/1 \
-H "Authorization: Bearer SEU_TOKEN"
```

## 🎓 Alunos

Listar Alunos

```bash
curl -X GET http://localhost:8080/alunos \
-H "Authorization: Bearer SEU_TOKEN"
```

Cadastrar Aluno

```bash
curl -X POST http://localhost:8080/alunos \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN" \
-d '{
  "nome": "Mariana Dias",
  "foto": null,
  "dataDeNascimento": "2006-05-10",
  "email": "mari@email.com",
  "telefone": "11994449073",
}'
```

Listar Turma
```bash
curl -X GET http://localhost:8080/turmas \
-H "Authorization: Bearer SEU_TOKEN"
```

Cadastrar Turma

```bash
curl -X POST http://localhost:8080/turmas \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN" \
-d '{
        "nome": "3D - Ensino Médio",
        "turno": "MANHA",
        "semestre": "PRIMEIRO_SEMESTRE"
    }'
```

Cadastrar Vinculo de Turma - Aluno

```bash
curl -X POST http://localhost:8080/turmas/{id}/vincular-alunos \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN" \
-d '{
      "alunosIds": [1, 2, 3]
    }'
```

# 🔑 Como obter o Token JWT

1. Faça login na aplicação utilizando a rota `/login`:

```bash
curl -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{
  "login": "admin",
  "senha": "admin"
}'
```

2. A API retornará um token JWT:

```bash
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

3. Copie o token retornado.
   
4. Utilize o token nas rotas protegidas usando o header:

```bash
-H "Authorization: Bearer SEU_TOKEN"
```

Exemplo:

```bash
curl -X GET http://localhost:8080/usuarios \
-H "Authorization: Bearer SEU_TOKEN"
```

## 👨‍💻 Equipe de Desenvolvimento

Este projeto está sendo desenvolvido por:

- Gabrielly Nascimento
- Lyncoln Santiago
- Michael Paulo
- Weslley Rocha
