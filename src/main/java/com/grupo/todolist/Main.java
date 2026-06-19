package com.grupo.todolist;

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
        // TODO(C): montar dependencias:
        //   TarefaRepository repository = new TarefaRepository();
        //   repository.carregarDoArquivo();
        //   TarefaService service = new TarefaService(repository);

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenu();
            // TODO(C): ler opcao do usuario e chamar o metodo correspondente do service.
            //   1 -> cadastrar          (RF01/RF07)
            //   2 -> listar             (RF02)
            //   3 -> concluir           (RF03)
            //   4 -> remover            (RF04)
            //   5 -> filtrar            (RF05)
            //   6 -> atribuir resp.     (RF06)
            //   0 -> sair (executando = false)
            throw new UnsupportedOperationException("TODO(C): tratar opcao do menu");
        }

        scanner.close();
    }

    /** Imprime as opcoes do menu no console. */
    private static void exibirMenu() {
        throw new UnsupportedOperationException("TODO(C)");
    }
}
