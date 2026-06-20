package com.grupo.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Testes unitarios minimos (XP exige >= 3).
 * Escreva os asserts dentro de cada metodo.
 *
 * ----------------------------------------------------------------
 * Responsavel sugerido: quem terminar primeiro / Coach.
 * Dica: use um TarefaRepository de verdade ou um simples em memoria.
 * ----------------------------------------------------------------
 */
public class TarefaServiceTest {

    @TempDir
    Path pastaTemp;

    private TarefaService novoService() throws IOException {
        Path arquivo = Files.createTempFile(pastaTemp, "tarefas", ".txt");
        return new TarefaService(new TarefaRepository(arquivo.toString()));
    }

    @Test
    void deveCadastrarTarefaComStatusPendente() throws IOException {
        TarefaService service = novoService();

        Tarefa tarefa = service.cadastrar("Estudar XP", "Ler sobre pair programming",
                Prioridade.ALTA, LocalDate.of(2026, 6, 30), "Ana");

        assertEquals(Status.PENDENTE, tarefa.getStatus());
        assertEquals("Estudar XP", tarefa.getTitulo());
        assertTrue(tarefa.getId() > 0, "o repositorio deve atribuir um id");
    }

    @Test
    void naoDeveCadastrarTarefaSemTitulo() throws IOException {
        TarefaService service = novoService();

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar("  ", "sem titulo", Prioridade.BAIXA, null, null));
    }

    @Test
    void deveFiltrarTarefasPorPrioridade() throws IOException {
        TarefaService service = novoService();
        service.cadastrar("Tarefa urgente", "", Prioridade.ALTA, null, null);
        service.cadastrar("Tarefa tranquila", "", Prioridade.BAIXA, null, null);

        List<Tarefa> resultado = service.filtrarPorPrioridade(Prioridade.ALTA);

        assertEquals(1, resultado.size());
        assertEquals(Prioridade.ALTA, resultado.get(0).getPrioridade());
    }

    @Test
    void deveMarcarTarefaComoConcluida() throws IOException {
        Path arquivo = Files.createTempFile(pastaTemp, "tarefas", ".txt");
        TarefaService service = new TarefaService(new TarefaRepository(arquivo.toString()));

        Tarefa tarefa = service.cadastrar("Finalizar relatorio", "Concluir a entrega",
                Prioridade.MEDIA, LocalDate.of(2026, 7, 5), "Bruno");

        service.concluir(tarefa.getId());

        assertEquals(Status.CONCLUIDA, tarefa.getStatus());

        // Recarrega de um repositorio novo para garantir que o status foi persistido (RF08).
        TarefaRepository repositorioRecarregado = new TarefaRepository(arquivo.toString());
        repositorioRecarregado.carregarDoArquivo();
        assertEquals(Status.CONCLUIDA,
                repositorioRecarregado.buscarPorId(tarefa.getId()).getStatus());
    }
}
