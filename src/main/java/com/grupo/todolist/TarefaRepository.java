package com.grupo.todolist;

import java.util.List;

/**
 * Camada de persistencia (RF08).
 * Guarda as tarefas em memoria e grava/le de um arquivo (.txt ou .json).
 *
 * Mantem tambem o controle do proximo id.
 *
 * ----------------------------------------------------------------
 * Responsavel sugerido: INTEGRANTE A
 * Contrato (assinaturas dos metodos publicos): combine com o grupo.
 * O TarefaService so chama estes metodos, nao acessa o arquivo direto.
 * ----------------------------------------------------------------
 */
public class TarefaRepository {

    // TODO(A): estrutura em memoria (ex.: List<Tarefa>) e contador de id.
    // TODO(A): caminho do arquivo de persistencia (ex.: "tarefas.txt").

    /** Adiciona uma tarefa nova, atribui o id e persiste. (RF01) */
    public Tarefa salvar(Tarefa tarefa) {
        throw new UnsupportedOperationException("TODO(A): atribuir id, adicionar na lista e gravar no arquivo");
    }

    /** Retorna todas as tarefas. (RF02) */
    public List<Tarefa> listarTodas() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    /** Busca uma tarefa pelo id; retorna null se nao existir. */
    public Tarefa buscarPorId(int id) {
        throw new UnsupportedOperationException("TODO(A)");
    }

    /** Remove a tarefa pelo id e persiste. (RF04) */
    public void remover(int id) {
        throw new UnsupportedOperationException("TODO(A)");
    }

    /** Persiste o estado atual no arquivo. (RF08) */
    public void salvarEmArquivo() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    /** Carrega as tarefas do arquivo para a memoria (chamar no inicio). (RF08) */
    public void carregarDoArquivo() {
        throw new UnsupportedOperationException("TODO(A)");
    }
}
