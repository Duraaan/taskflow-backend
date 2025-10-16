package cl.sdc.taskflow.dto;

import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) utilizado para la respuesta de las operaciones de lectura (GET).
 * Este DTO define el contrato de la API, exponiendo solo los campos necesarios al cliente.
 *
 * @author Sebastián Durán
 */
@Data
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
    }
}
