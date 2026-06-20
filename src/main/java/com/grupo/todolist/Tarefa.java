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

    /**
     * Cria uma tarefa nova. O id permanece 0 ate o repositorio atribuir o valor real.
     * Toda tarefa nasce com status PENDENTE (RF01).
     */
    public Tarefa(String titulo, String descricao, Prioridade prioridade,
                  LocalDate prazo, String responsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.prazo = prazo;
        this.responsavel = responsavel;
        this.status = Status.PENDENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /** Linha amigavel para a listagem no console (RF02). */
    @Override
    public String toString() {
        String prazoTexto = (prazo != null) ? prazo.toString() : "sem prazo";
        String responsavelTexto = (responsavel != null && !responsavel.isBlank())
                ? responsavel : "sem responsavel";
        return String.format("[%d] %s | %s | prioridade: %s | prazo: %s | responsavel: %s",
                id, titulo, status, prioridade, prazoTexto, responsavelTexto);
    }
}
