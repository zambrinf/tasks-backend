package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        try {
            Task todo = new Task();
            todo.setDueDate(LocalDate.now().plusDays(2));
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        try {
            Task todo = new Task();
            todo.setTask("Descrição teste");
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaEmDataPassada() {
        try {
            Task todo = new Task();
            todo.setTask("Descrição teste");
            todo.setDueDate(LocalDate.now().minusDays(2));
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição teste");
        todo.setDueDate(LocalDate.now().plusDays(2));
        controller.save(todo);
        Mockito.verify(taskRepo).save(todo);
    }
}
