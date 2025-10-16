package cl.sdc.taskflow.service;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.dto.TaskUpdateRequest;
import cl.sdc.taskflow.exception.ResourceNotFoundException;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import cl.sdc.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, TaskUpdateRequest request) {
        Task task = getTaskById(id);

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
}
