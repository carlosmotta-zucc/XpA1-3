package com.grupo.todolist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada + menu de console.
 * Monta as dependencias e faz o loop do menu, chamando o TarefaService.
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
                    case "1" -> cadastrarTarefa(service, scanner);
                    case "2" -> listarTarefas(service);
                    case "3" -> concluirTarefa(service, scanner);
                    case "4" -> removerTarefa(service, scanner);
                    case "5" -> filtrarTarefas(service, scanner);
                    case "6" -> atribuirResponsavel(service, scanner);
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

    // --- MÉTODOS DE CADA OPÇÃO DO MENU ---

    private static void cadastrarTarefa(TarefaService service, Scanner scanner) {
        System.out.println("\n--- CADASTRAR TAREFA ---");
        
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Descricao: ");
        String descricao = scanner.nextLine().trim();

        System.out.print("Prioridade (ALTA, MEDIA ou BAIXA): ");
        String prioEntrada = scanner.nextLine().trim().toUpperCase();
        Prioridade prioridade;
        try {
            prioridade = Prioridade.valueOf(prioEntrada);
        } catch (IllegalArgumentException e) {
            System.out.println("Prioridade invalida. Cadastro cancelado.");
            return;
        }

        System.out.print("Prazo (Formato AAAA-MM-DD) ou deixe em branco: ");
        String prazoEntrada = scanner.nextLine().trim();
        LocalDate prazo = null;
        if (!prazoEntrada.isEmpty()) {
            try {
                prazo = LocalDate.parse(prazoEntrada);
            } catch (DateTimeParseException e) {
                System.out.println("Data no formato invalido. Cadastro cancelado.");
                return;
            }
        }

        System.out.print("Responsavel (ou deixe em branco): ");
        String responsavel = scanner.nextLine().trim();

        try {
            service.cadastrar(titulo, descricao, prioridade, prazo, responsavel);
            System.out.println("Tarefa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
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

    private static void concluirTarefa(TarefaService service, Scanner scanner) {
        System.out.println("\n--- CONCLUIR TAREFA ---");
        System.out.print("ID da tarefa a concluir: ");
        String entrada = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(entrada);
            service.concluir(id);
            System.out.println("Tarefa marcada como CONCLUIDA!");
        } catch (NumberFormatException e) {
            System.out.println("ID invalido. Informe um numero inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerTarefa(TarefaService service, Scanner scanner) {
        System.out.print("ID da tarefa a remover: ");
        String entrada = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido. Informe um numero inteiro.");
            return;
        }
        try {
            service.remover(id);
            System.out.println("Tarefa removida com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
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

    private static void atribuirResponsavel(TarefaService service, Scanner scanner) {
        System.out.println("\n--- ATRIBUIR RESPONSAVEL ---");
        System.out.print("ID da tarefa: ");
        String entrada = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido. Informe um numero inteiro.");
            return;
        }

        System.out.print("Nome do novo responsavel: ");
        String responsavel = scanner.nextLine().trim();

        try {
            service.atribuirResponsavel(id, responsavel);
            System.out.println("Responsavel atribuido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
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