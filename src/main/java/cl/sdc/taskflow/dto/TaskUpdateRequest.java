package cl.sdc.taskflow.dto;

import cl.sdc.taskflow.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskUpdateRequest {

    @NotBlank(message = "El título es obligatorio para la actualización.")
    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    private String title;
    @Size(max = 500, message = "La descripción no pude exceder los 500 caracteres")
    private String description;
    @NotNull(message = "El estado de la tarea es obligatorio.")
    private TaskStatus status;
}
