# CLAUDE.md

Contexto do projeto para o Claude Code. Leia antes de editar qualquer arquivo.

## O que é

To-Do List de console em **Java 17 / Maven**. Gerenciador de tarefas para a
disciplina de Engenharia de Software (metodologia XP). O repositório nasceu como
**esqueleto**: as assinaturas públicas e os contratos já estão prontos; os corpos
estão como `TODO(...)` lançando `UnsupportedOperationException`. O trabalho é
implementar esses corpos — **sem** alterar as assinaturas.

As histórias de usuário e seus critérios de aceitação estão em
[`User Stories Baseadas nos Requisitos Funcionais.md`](./User%20Stories%20Baseadas%20nos%20Requisitos%20Funcionais.md).

## Arquitetura (camadas)

```
Main (menu console)  ->  TarefaService (regras de negócio)  ->  TarefaRepository (persistência em arquivo)
                                      |
                            Tarefa / Prioridade / Status (modelo)
```

Regras de dependência — **respeite-as**:
- `Main` só conversa com `TarefaService`. Não coloque regra de negócio no `Main`:
  só ler input, chamar o service e imprimir o resultado.
- `TarefaService` só chama métodos públicos do `TarefaRepository`. Nunca acessa
  arquivo direto.
- `TarefaRepository` é o único que lê/grava o arquivo de persistência e controla o
  próximo `id`.
- `Tarefa`, `Prioridade`, `Status` são o modelo, do qual todo o resto depende.

Arquivos em `src/main/java/com/grupo/todolist/`:
`Main.java`, `TarefaService.java`, `TarefaRepository.java`, `Tarefa.java`,
`Prioridade.java`, `Status.java`. Testes em
`src/test/java/com/grupo/todolist/TarefaServiceTest.java`.

## Mapa Requisito Funcional → código

| RF | O quê | Onde |
|----|-------|------|
| RF01 / RF07 | Cadastrar tarefa (título, descrição, prioridade, prazo) | `TarefaService.cadastrar`, `Tarefa`, `TarefaRepository.salvar` |
| RF02 | Listar todas | `TarefaService.listar`, `TarefaRepository.listarTodas`, `Tarefa.toString` |
| RF03 | Concluir tarefa | `TarefaService.concluir` |
| RF04 | Remover tarefa | `TarefaService.remover`, `TarefaRepository.remover` |
| RF05 | Filtrar por status / prioridade | `TarefaService.filtrarPorStatus`, `filtrarPorPrioridade` |
| RF06 | Atribuir responsável | `TarefaService.atribuirResponsavel`, campo `Tarefa.responsavel` |
| RF08 | Persistência em arquivo | `TarefaRepository.salvarEmArquivo`, `carregarDoArquivo` |

## Divisão de trabalho (paralela, sem conflito)

Cada dev implementa os `TODO` da sua parte. Quem mexe em quê (já marcado no código
por `TODO(A)`, `TODO(B)`, `TODO(C)`):

| Dev | Arquivos / métodos | RFs |
|-----|--------------------|-----|
| **A** | `Tarefa.java`, `TarefaRepository.java` (+ enums) | base + RF08 |
| **B** | `cadastrar`, `concluir`, `remover` em `TarefaService.java` | RF01, RF03, RF04, RF07 |
| **C** | `listar`, `filtrar*`, `atribuirResponsavel` em `TarefaService.java` + `Main.java` | RF02, RF05, RF06 |

> O Dev A entrega `Tarefa` e `TarefaRepository` primeiro, porque B e C dependem deles.
> Enquanto isso, B e C já escrevem contra as assinaturas (que não mudam).

## Fluxo de trabalho do grupo (3 devs)

1. **Juntos:** implementar a **primeira história (RF01 — Cadastro de Tarefa)**, que
   exige a base do Dev A (`Tarefa`, `TarefaRepository`) + `cadastrar`. Validar que
   compila e roda. Fazer **commit + push** dessa base na `main`.
2. **Depois:** cada dev pega uma história e trabalha **só nos seus métodos/arquivos**
   conforme a tabela acima — assim os diffs não se sobrepõem.

## Regra de ouro contra conflito

**Ninguém altera uma assinatura pública (nome do método, parâmetros, tipo de retorno)
sem combinar com o grupo.** As assinaturas são o contrato que permite trabalhar em
paralelo. Se uma assinatura precisar mudar, alinhe antes — isso afeta os outros dois.

Para o Claude: ao implementar um `TODO`, **preencha apenas o corpo**; mantenha a
assinatura existente intacta. Trabalhe somente nos arquivos/métodos da parte pedida;
não altere os `TODO` de outra parte.

## Convenções de código limpo

- **Idioma:** código e comentários em português (sem acentos em identificadores),
  seguindo o que já existe. Mensagens ao usuário podem ter acento.
- Nomes descritivos; uma responsabilidade por método. Sem regra de negócio no `Main`.
- Status inicial de toda tarefa nova é `PENDENTE`.
- `cadastrar` deve validar que o **título é obrigatório** (não vazio).
- `buscarPorId` retorna `null` quando não encontra — trate isso em quem chama.
- Toda operação que muda estado (`salvar`, `remover`, `concluir`) deve **persistir**
  via repositório (RF08).
- Sem dependências externas além do JUnit (ver `pom.xml`). Use só a biblioteca padrão.
- Remova o `throw new UnsupportedOperationException(...)` ao implementar — não deixe
  método "meio implementado".

## Comandos

```bash
# Compilar e rodar (sem Maven, mais rápido)
cd src/main/java
javac com/grupo/todolist/*.java
java com.grupo.todolist.Main

# Com Maven
mvn compile
mvn exec:java -Dexec.mainClass=com.grupo.todolist.Main
mvn test        # JUnit 5 (XP exige >= 3 testes)
```

## Testes

`TarefaServiceTest` tem 3 testes mínimos (exigência da metodologia XP):
cadastro com status PENDENTE (RF01), conclusão (RF03), filtro por prioridade (RF05).
Implemente os asserts ao concluir os métodos correspondentes.
