package cl.sdc.taskflow.model.entity;

import cl.sdc.taskflow.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Representa la entidad principal para la gestión de tareas en la base de datos.
 * Define la estructura de la tabla 'task' y sus propiedades.
 *
 * @author Sebastián Durán
 * @version 1.0
 */
@Data
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
