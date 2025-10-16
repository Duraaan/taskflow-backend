package cl.sdc.taskflow.controller;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.dto.TaskResponse;
import cl.sdc.taskflow.dto.TaskUpdateRequest;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import cl.sdc.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de tareas (CRUD).
 * Maneja todas las peticiones HTTP bajo el path /api/tasks.
 *
 * @author Sebastián Durán
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Crea una nueva tarea con el estado inicial PENDING.
     * @param request El DTO de solicitud con título y descripción.
     * @return La entidad Task creada.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }


    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /**
     * Obtiene una lista de todas las tareas, permitiendo el filtrado opcional por estado.
     * @param status El estado de la tarea para filtrar (PENDING, IN_PROGRESS, COMPLETED). Es opcional.
     * @return Una lista de DTO de respuesta (TaskResponse).
     */
    @GetMapping
    public List<TaskResponse> getTasks(@RequestParam(required = false) TaskStatus status) {
        return taskService.getByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
