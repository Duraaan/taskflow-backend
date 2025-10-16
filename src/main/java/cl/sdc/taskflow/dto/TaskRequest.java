package cl.sdc.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no puede exceder los 100 caracteres")
    private String title;
    @Size(max = 500, message = "La descripción no pude exceder los 500 caracteres")
    private String description;
}
