package cl.sdc.taskflow.repository;

import cl.sdc.taskflow.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
