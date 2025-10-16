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

    private Task getTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

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
