package com.grupo.todolist;

import java.time.LocalDate;

/**
 * Entidade central do sistema.
 * Campos cobrem: RF01 (titulo, descricao, prioridade), RF06 (responsavel), RF07 (prazo).
 *
 * ----------------------------------------------------------------
 * CONTRATO COMPARTILHADO: combine com o grupo antes de mexer aqui.
 * Todas as outras classes dependem desta.
 * Responsavel sugerido: INTEGRANTE A
 * ----------------------------------------------------------------
 */
public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;
    private LocalDate prazo;       // RF07
    private String responsavel;    // RF06

    // TODO(A): construtor(es). Sugestao: receber titulo, descricao, prioridade, prazo, responsavel
    //          e iniciar status como PENDENTE. O id pode ser definido pelo repositorio.

    // TODO(A): getters e setters de cada campo abaixo.

    public int getId() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public void setId(int id) {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public String getTitulo() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public String getDescricao() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public Prioridade getPrioridade() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public Status getStatus() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public void setStatus(Status status) {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public LocalDate getPrazo() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    public String getResponsavel() {
        throw new UnsupportedOperationException("TODO(A)");
    }

    // TODO(A): toString() amigavel para a listagem no console (RF02).
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO(A)");
    }
}
