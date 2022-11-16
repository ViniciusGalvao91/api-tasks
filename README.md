# Task API - IMPA

API para gestão de tarefas.

  - Java 8+
  - Spring Boot
  - MySql

# Instalação
Clone repository

$ git clone https://github.com/ViniciusGalvao91/api-tasks.git

# Criação do BD MySql

CREATE DATABASE api_tasks;

# Criação de novas tarefas
Envie uma solicitação POST com 'title', 'description', 'date' e 'time' para o seguinte endpoint:

/tasks/create

# Alteração de tarefas
Envie uma solicitação PUT com 'title', 'description', 'date', 'time' e 'state' para o seguinte endpoint:

/tasks/update/{id}

# Exclusão de tarefas
Envie uma solicitação DELETE com 'id' para o seguinte endpoint:

/tasks/delete/{id}

# Listando tarefas
Envie uma solicitação GET para o seguinte endpoint:

/tasks

# Pesquisando tarefas
Envie uma solicitação GET com o 'id' para o seguinte endpoint:

/tasks/search/{id}

Exemplo:

/tasks/search/95461cf1-9e88-4845-a230-9450d519e8ce

{
    "id": "95461cf1-9e88-4845-a230-9450d519e8ce",
    "title": "Entrega da tarefa IMPA ",
    "description": "Projeto para o processo seletivo de Desenvolvedor Java Junior",
    "date": "15/11/2022",
    "time": "18:00",
    "state": false
}

** Desenvolvido por Vinicius Galvão de Oliveira **
