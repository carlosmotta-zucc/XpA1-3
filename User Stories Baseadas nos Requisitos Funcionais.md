# User Stories Baseadas nos Requisitos Funcionais

## 1. Cadastro de Tarefa (RF01 e RF07)

**História:** Como usuário, quero cadastrar uma nova tarefa informando título, descrição, prioridade (alta/média/baixa) e prazo (deadline), para organizar minhas atividades.

**Critérios de Aceitação:**

- O sistema deve registrar a tarefa contendo título e descrição obrigatoriamente.
- O sistema deve permitir a seleção exclusiva entre prioridades alta, média ou baixa.
- O sistema deve aceitar o registro de uma data limite para a tarefa.

## 2. Listagem e Filtros (RF02 e RF05)

**História:** Como usuário, quero listar todas as tarefas e poder filtrá-las por status (pendente/concluída) ou prioridade, para visualizar rapidamente o que precisa ser feito.

**Critérios de Aceitação:**

- A aplicação deve exibir em tela todas as tarefas salvas anteriormente.
- O usuário deve poder aplicar um filtro que exiba apenas tarefas pendentes ou apenas concluídas.
- O usuário deve poder aplicar um filtro para visualizar tarefas baseado na sua prioridade.

## 3. Conclusão de Tarefas (RF03)

**História:** Como usuário, quero marcar uma tarefa como concluída, para acompanhar o progresso real do meu trabalho.

**Critérios de Aceitação:**

- O sistema deve fornecer uma opção para alterar o estado atual de uma tarefa de "pendente" para "concluída".
- O novo status da tarefa deve ser salvo no mecanismo de persistência de dados (arquivo ou banco).

## 4. Atribuição de Responsáveis (RF06)

**História:** Como usuário, quero atribuir a tarefa a um membro da equipe, para deixar claro quem é o responsável por sua execução.

**Critérios de Aceitação:**

- O sistema deve permitir a vinculação do nome de um membro da equipe a uma tarefa específica.

## 5. Remoção de Tarefas (RF04)

**História:** Como usuário, quero remover uma tarefa, para limpar da lista os itens que foram cadastrados por engano ou que foram cancelados.

**Critérios de Aceitação:**

- O sistema deve apagar completamente a tarefa selecionada da listagem e do banco/arquivo de persistência.
