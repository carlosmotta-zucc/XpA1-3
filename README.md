# To-Do List — Esqueleto (Java)

Sistema de gerenciamento de tarefas em console. Este é só o **esqueleto**:
todas as assinaturas e contratos estão prontos, os corpos estão como `TODO`.

## Divisão de trabalho (paralela, sem conflito)

A regra é: ninguém mexe na assinatura pública sem combinar com o grupo.
Cada um implementa os corpos `TODO` da sua parte.

| Integrante | Arquivos | RFs |
|-----------|----------|-----|
| **A** | `Tarefa.java`, `TarefaRepository.java` (+ enums `Prioridade`, `Status`) | base + RF08 (persistência) |
| **B** | `cadastrar`, `concluir`, `remover` em `TarefaService.java` | RF01, RF03, RF04, RF07 |
| **C** | `listar`, `filtrar*`, `atribuirResponsavel` em `TarefaService.java` + `Main.java` | RF02, RF05, RF06 |

> Sugestão: o Integrante A entrega `Tarefa` e `TarefaRepository` primeiro,
> porque B e C dependem deles. Enquanto isso, B e C já escrevem contra as assinaturas.

## Fluxo das camadas

```
Main (menu)  ->  TarefaService (regras)  ->  TarefaRepository (arquivo)
                          |
                       Tarefa / Prioridade / Status
```

## Como rodar

Sem Maven (mais rápido):
```bash
cd src/main/java
javac com/grupo/todolist/*.java
java com.grupo.todolist.Main
```

Com Maven (inclui os testes JUnit):
```bash
mvn compile      # compila
mvn exec:java -Dexec.mainClass=com.grupo.todolist.Main   # roda
mvn test         # testes
```
