package cl.sdc.taskflow.model.entity;

import cl.sdc.taskflow.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
