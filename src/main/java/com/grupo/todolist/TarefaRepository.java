package com.grupo.todolist;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Camada de persistencia (RF08).
 * Guarda as tarefas em memoria e grava/le de um arquivo texto.
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

    private static final String ARQUIVO_PADRAO = "tarefas.txt";
    /** Separador de campos no arquivo. O input de console e uma linha, entao nao colide. */
    private static final String SEPARADOR = "\t";

    private final List<Tarefa> tarefas = new ArrayList<>();
    private final Path arquivo;
    private int proximoId = 1;

    public TarefaRepository() {
        this(ARQUIVO_PADRAO);
    }

    /** Construtor com caminho customizado (util para testes). */
    public TarefaRepository(String arquivo) {
        this.arquivo = Path.of(arquivo);
    }

    /** Adiciona uma tarefa nova, atribui o id e persiste. (RF01) */
    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setId(proximoId++);
        tarefas.add(tarefa);
        salvarEmArquivo();
        return tarefa;
    }

    /** Retorna todas as tarefas. (RF02) */
    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas);
    }

    /** Busca uma tarefa pelo id; retorna null se nao existir. */
    public Tarefa buscarPorId(int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        return null;
    }

    /** Remove a tarefa pelo id e persiste. (RF04) */
    public void remover(int id) {
        tarefas.removeIf(tarefa -> tarefa.getId() == id);
        salvarEmArquivo();
    }

    /** Persiste o estado atual no arquivo. (RF08) */
    public void salvarEmArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Tarefa tarefa : tarefas) {
            linhas.add(serializar(tarefa));
        }
        try {
            Files.write(arquivo, linhas);
        } catch (IOException e) {
            throw new UncheckedIOException("Falha ao gravar " + arquivo, e);
        }
    }

    /** Carrega as tarefas do arquivo para a memoria (chamar no inicio). (RF08) */
    public void carregarDoArquivo() {
        tarefas.clear();
        proximoId = 1;
        if (!Files.exists(arquivo)) {
            return;
        }
        try {
            for (String linha : Files.readAllLines(arquivo)) {
                if (linha.isBlank()) {
                    continue;
                }
                Tarefa tarefa = desserializar(linha);
                tarefas.add(tarefa);
                proximoId = Math.max(proximoId, tarefa.getId() + 1);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Falha ao ler " + arquivo, e);
        }
    }

    private String serializar(Tarefa tarefa) {
        return String.join(SEPARADOR,
                String.valueOf(tarefa.getId()),
                vazioSeNulo(tarefa.getTitulo()),
                vazioSeNulo(tarefa.getDescricao()),
                tarefa.getPrioridade() != null ? tarefa.getPrioridade().name() : "",
                tarefa.getStatus().name(),
                tarefa.getPrazo() != null ? tarefa.getPrazo().toString() : "",
                vazioSeNulo(tarefa.getResponsavel()));
    }

    private Tarefa desserializar(String linha) {
        // limit -1 para preservar campos vazios no fim da linha.
        String[] campos = linha.split(SEPARADOR, -1);
        String titulo = campos[1];
        String descricao = nuloSeVazio(campos[2]);
        Prioridade prioridade = campos[3].isEmpty() ? null : Prioridade.valueOf(campos[3]);
        Status status = Status.valueOf(campos[4]);
        LocalDate prazo = campos[5].isEmpty() ? null : LocalDate.parse(campos[5]);
        String responsavel = nuloSeVazio(campos[6]);

        Tarefa tarefa = new Tarefa(titulo, descricao, prioridade, prazo, responsavel);
        tarefa.setId(Integer.parseInt(campos[0]));
        tarefa.setStatus(status);
        return tarefa;
    }

    private static String vazioSeNulo(String valor) {
        return valor != null ? valor : "";
    }

    private static String nuloSeVazio(String valor) {
        return valor.isEmpty() ? null : valor;
    }
}
