package com.grupo.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Regras de negocio do sistema. O Main so conversa com esta classe.
 * Esta classe delega a persistencia ao TarefaRepository.
 *
 * ----------------------------------------------------------------
 * Dois integrantes podem trabalhar aqui em paralelo, cada um nos
 * seus metodos (as assinaturas ja estao fixadas como contrato):
 *
 *   INTEGRANTE B -> cadastrar, concluir, remover           (RF01, RF03, RF04)
 *   INTEGRANTE C -> listar, filtrar..., atribuirResponsavel (RF02, RF05, RF06)
 * ----------------------------------------------------------------
 */
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    /** RF01 + RF07: cria e salva uma tarefa. Validar titulo obrigatorio. */
    public Tarefa cadastrar(String titulo, String descricao, Prioridade prioridade,
                            LocalDate prazo, String responsavel) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O titulo da tarefa e obrigatorio.");
        }
        Tarefa tarefa = new Tarefa(titulo, descricao, prioridade, prazo, responsavel);
        return repository.salvar(tarefa);
    }

    /** RF02: retorna todas as tarefas. */
    public List<Tarefa> listar() {
        return repository.listarTodas();
    }

    /** RF03: marca a tarefa como CONCLUIDA e persiste. */
    public void concluir(int id) {
        throw new UnsupportedOperationException("TODO(B)");
    }

    /** RF04: remove a tarefa. */
    public void remover(int id) {
        if (repository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Tarefa com id " + id + " nao encontrada.");
        }
        repository.remover(id);
    }

    /** RF05: filtra por status (PENDENTE / CONCLUIDA). */
    public List<Tarefa> filtrarPorStatus(Status status) {
        List<Tarefa> resultado = new ArrayList<>();
        for (Tarefa tarefa : repository.listarTodas()) {
            if (tarefa.getStatus() == status) {
                resultado.add(tarefa);
            }
        }
        return resultado;
    }

    /** RF05: filtra por prioridade. */
    public List<Tarefa> filtrarPorPrioridade(Prioridade prioridade) {
        List<Tarefa> resultado = new ArrayList<>();
        for (Tarefa tarefa : repository.listarTodas()) {
            if (tarefa.getPrioridade() == prioridade) {
                resultado.add(tarefa);
            }
        }
        return resultado;
    }

    /** RF06: vincula um responsavel a uma tarefa existente. */
    public void atribuirResponsavel(int id, String responsavel) {
        throw new UnsupportedOperationException("TODO(C)");
    }
}
