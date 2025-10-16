package cl.sdc.taskflow.dto;

import cl.sdc.taskflow.model.enums.TaskStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskUpdateRequest {

    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    private String title;
    @Size(max = 500, message = "La descripción no pude exceder los 500 caracteres")
    private String description;
    private TaskStatus status;
}
