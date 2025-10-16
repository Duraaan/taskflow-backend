package cl.sdc.taskflow.service;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import cl.sdc.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
