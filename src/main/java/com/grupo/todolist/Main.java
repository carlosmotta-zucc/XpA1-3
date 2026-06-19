package com.grupo.todolist;

import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada + menu de console.
 * Monta as dependencias e faz o loop do menu, chamando o TarefaService.
 *
 * ----------------------------------------------------------------
 * Responsavel sugerido: INTEGRANTE C (junto com os filtros/listagem)
 * Nao colocar regra de negocio aqui: so ler input, chamar o service
 * e imprimir o resultado.
 * ----------------------------------------------------------------
 */
public class Main {

    public static void main(String[] args) {
        TarefaRepository repository = new TarefaRepository();
        repository.carregarDoArquivo();
        TarefaService service = new TarefaService(repository);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;

            while (executando) {
                exibirMenu();
                String entrada = scanner.nextLine().trim();

                switch (entrada) {
                    case "1" -> System.out.println("Opcao nao implementada ainda."); // TODO(B): cadastrar (RF01/RF07)
                    case "2" -> listarTarefas(service);
                    case "3" -> System.out.println("Opcao nao implementada ainda."); // TODO(B): concluir (RF03)
                    case "4" -> System.out.println("Opcao nao implementada ainda."); // TODO(B): remover (RF04)
                    case "5" -> filtrarTarefas(service, scanner);
                    case "6" -> System.out.println("Opcao nao implementada ainda."); // TODO(C): atribuir responsavel (RF06)
                    case "0" -> {
                        executando = false;
                        System.out.println("Encerrando o sistema.");
                    }
                    default -> System.out.println("Opcao invalida. Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== TO-DO LIST ===");
        System.out.println("1 - Cadastrar tarefa");
        System.out.println("2 - Listar todas as tarefas");
        System.out.println("3 - Concluir tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("5 - Filtrar tarefas");
        System.out.println("6 - Atribuir responsavel");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void listarTarefas(TarefaService service) {
        List<Tarefa> tarefas = service.listar();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            System.out.println("\n--- Tarefas ---");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private static void filtrarTarefas(TarefaService service, Scanner scanner) {
        System.out.println("\nFiltrar por:");
        System.out.println("1 - Status (PENDENTE / CONCLUIDA)");
        System.out.println("2 - Prioridade (ALTA / MEDIA / BAIXA)");
        System.out.print("Escolha: ");
        String opcao = scanner.nextLine().trim();

        if ("1".equals(opcao)) {
            System.out.print("Status (PENDENTE / CONCLUIDA): ");
            String valor = scanner.nextLine().trim().toUpperCase();
            Status status;
            try {
                status = Status.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Status invalido. Use PENDENTE ou CONCLUIDA.");
                return;
            }
            exibirResultado(service.filtrarPorStatus(status));

        } else if ("2".equals(opcao)) {
            System.out.print("Prioridade (ALTA / MEDIA / BAIXA): ");
            String valor = scanner.nextLine().trim().toUpperCase();
            Prioridade prioridade;
            try {
                prioridade = Prioridade.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridade invalida. Use ALTA, MEDIA ou BAIXA.");
                return;
            }
            exibirResultado(service.filtrarPorPrioridade(prioridade));

        } else {
            System.out.println("Opcao invalida.");
        }
    }

    private static void exibirResultado(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com esse filtro.");
        } else {
            System.out.println("\n--- Resultado do Filtro ---");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }
}
