package cl.sdc.taskflow.service;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.dto.TaskResponse;
import cl.sdc.taskflow.dto.TaskUpdateRequest;
import cl.sdc.taskflow.exception.ResourceNotFoundException;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import cl.sdc.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio principal encargado de la lógica de negocio para la gestión de tareas.
 * Implementa la separación de DTO y encapsula el acceso a la base de datos.
 *
 * @author Sebastián Durán
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING);

        return taskRepository.save(task);
    }

    /**
     * Metodo interno para obtener la Entidad Task.
     * Utilizado exclusivamente por las operaciones de escritura (UPDATE/DELETE)
     * para asegurar que la Entidad Task sea manipulada internamente.
     *
     * @param id El identificador único de la tarea.
     * @return La Entidad Task.
     * @throws ResourceNotFoundException Si la tarea no se encuentra.
     */
    private Task getTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

    /**
     * Obtiene una tarea por su ID y la mapea al DTO de respuesta.
     * @param id El identificador único de la tarea.
     * @return El DTO TaskResponse.
     */
    public TaskResponse getTaskById(Long id) {
        Task task = getTaskEntityById(id);
        return new TaskResponse(task);
    }

    public Task updateTask(Long id, TaskUpdateRequest request) {
        Task task = getTaskEntityById(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarea no encontrada con ID: " + id);
        }

        taskRepository.deleteById(id);
    }

    /**
     * Filtra tareas por estado o devuelve todas si el estado es nulo.
     * @param status El estado de la tarea (opcional).
     * @return Una lista de TaskResponse.
     */
    public List<TaskResponse> getByStatus(TaskStatus status) {

        List<Task> tasks;

        if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
}
