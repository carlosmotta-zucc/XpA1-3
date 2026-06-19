package com.grupo.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
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
    @Disabled("TODO(B): concluir uma tarefa e verificar que o status virou CONCLUIDA (RF03)")
    void deveMarcarTarefaComoConcluida() {
        throw new UnsupportedOperationException("TODO");
    }

    @Test
    @Disabled("TODO(C): cadastrar tarefas com prioridades diferentes e verificar o filtro (RF05)")
    void deveFiltrarTarefasPorPrioridade() {
        throw new UnsupportedOperationException("TODO");
    }
}
